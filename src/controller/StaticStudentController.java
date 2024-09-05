package controller;

import constant.LEVEL;
import model.Person;
import model.Student;
import utils.Input;
import utils.Utils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static constant.Constant.*;
import static view.ConsoleUI.studentCount;

public class StaticStudentController {
    public static void createStudentById(Student[] studentLists) {
        System.out.println("Nhap sinh vien moi: ");

        String name = Input.inputName();
        String birthDate = Input.inputBirthDate();
        String address = Input.inputAddress();
        double height = Input.inputHeight();
        double weight = Input.inputWeight();

        List<Student> studentList = new ArrayList<>(Arrays.asList(studentLists));
        String studentId = Input.inputStudentId((ArrayList<Student>) studentList);

        String school = Input.inputSchool();
        int startYear = Input.inputStartYear();
        double gpa = Input.inputGpa();

        Student student = new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                address, height, weight, studentId, school, startYear, gpa);

        if (studentCount >= MAX_STUDENT) {
            System.out.println("Khong the them sinh vien moi vao danh sach.");
            return;
        }

        studentLists[studentCount] = student;
        studentCount++;
        System.out.print("\nThem thanh cong sinh vien: \n" + student);
    }

    public static Student findStudentById(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. ");
            return null;
        }

        Long id = Input.inputID();
//        if (id > Person.getIdCounter()) {
//            System.out.println("Khong co du lieu phu hop.");
//            return null;
//        }

        for (Student student : studentLists) {
            if (student != null) {
                if (student.getId().equals(id)) {
                    System.out.println("Sinh vien: " + student);
                    return student;
                }
            }
        }

        System.out.println("Khong co du lieu phu hop.");
        return null;
    }

    public static void updateStudentById(Student[] studentLists) {
        Scanner sc = new Scanner(System.in);
        Student foundStudent = StaticStudentController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        Student originalValues = new Student(foundStudent);
        while (true) {
            Utils.showUpdateOption();
            try {
                System.out.print("Lua chon cua ban: ");
                int option = sc.nextInt();
                if (option == 0) {
                    break;
                } else if (option == 10) {
                    Utils.restoreOriginalValues(foundStudent, originalValues);
                    break;
                }

                Utils.handleUpdateOptionArray(studentLists, foundStudent, option);
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le. Vui long chon lai! ");
                sc.next();
            }
        }

        System.out.println("Sinh vien sau khi duoc sua: " + foundStudent);
    }

    public static void deleteStudentById(Student[] studentLists) {
        Student foundStudent = findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the thuc hien chuc nang xoa sinh vien.");
            return;
        }

        int deleteIndex = Utils.findDeletedStudentIndex(studentLists, foundStudent);

        for (int i = deleteIndex; i < studentCount - 1; i++) {
            studentLists[i] = studentLists[i + 1];
        }
        studentLists[studentCount - 1] = null;
        studentCount--;
        System.out.println("Da xoa thanh cong.");
    }

    public static void displayStudentList(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi danh sach.");
            return;
        }

        System.out.println("\nDanh sach sinh vien: ");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(studentLists[i]);
        }
    }

    public static void displayAcademicAbility(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % hoc luc sinh vien.");
            return;
        }

        List<Student> studentList = new ArrayList<>(Arrays.asList(studentLists));
        Map<LEVEL, Integer> levelMap = Utils.mapList((ArrayList<Student>) studentList, "level");
        if (!levelMap.isEmpty()) {
            System.out.println("\nTy le hoc luc cua cac sinh vien: ");
            ArrayList<Map.Entry<LEVEL, Integer>> levelLists = new ArrayList<>(levelMap.entrySet());
            levelLists.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            for (Map.Entry<LEVEL, Integer> e : levelLists)
                if (e.getKey() != null) {
                    System.out.println(String.format("%s: %.2f%%", e.getKey().getVietnamese(), (double) e.getValue() / studentCount * 100));
                }
        } else {
            System.out.println("Khong co du lieu phu hop.");
        }
    }

    public static void displayAverageRate(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % diem trung binh.");
            return;
        }

        List<Student> studentList = new ArrayList<>(Arrays.asList(studentLists));
        Map<Double, Integer> averageMap = Utils.mapList((ArrayList<Student>) studentList, "gpa");
        if (!averageMap.isEmpty()) {
            System.out.println("Ty le diem trung binh cua cac sinh vien: ");
            for (Map.Entry<Double, Integer> e : averageMap.entrySet()) {
                System.out.println(String.format("%.2f: %.2f%%", e.getKey(), (double) e.getValue() / studentCount * 100));
            }
        } else {
            System.out.println("Du lieu khong hop le.");
        }
    }

    public static void displayStudentListByAcademicAbility(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi sinh vien theo hoc luc.");
            return;
        }

        String academicAbility = Input.inputLevel();
        System.out.println("Danh sach sinh vien co hoc luc '" + academicAbility.toLowerCase() + "': ");
        int count = 0;
        for (Student student : studentLists) {
            if (student != null) {
                if (student.getLevel().getVietnamese().equalsIgnoreCase(academicAbility)) {
                    System.out.println(student);
                } else {
                    count++;
                }
            }
        }

        if (count == studentCount) {
            System.out.println("Khong co sinh vien nao co hoc luc '" + academicAbility.toLowerCase() + "'.");
        }
    }

    public static void writeToFile(Student[] studentLists) {
        if (studentCount == 0) {
            return;
        }

        File file = new File(STATIC_FILENAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Student student : studentLists) {
                if (student != null) {
                    writer.write(student.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu vao file: " + e.getMessage());
        }
    }
}
