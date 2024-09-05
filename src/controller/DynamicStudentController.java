package controller;

import constant.LEVEL;
import model.Student;
import utils.Input;
import utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static constant.Constant.DYNAMIC_FILENAME;

public class DynamicStudentController {
    public static void createStudentById(ArrayList<Student> studentLists) {
        System.out.println("Nhap sinh vien moi: ");

        String name = Input.inputName();
        String birthDate = Input.inputBirthDate();
        String address = Input.inputAddress();
        double height = Input.inputHeight();
        double weight = Input.inputWeight();
        String studentId = Input.inputStudentId(studentLists);
        String school = Input.inputSchool();
        int startYear = Input.inputStartYear();
        double gpa = Input.inputGpa();

        Student student = new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                address, height, weight, studentId, school, startYear, gpa);

        studentLists.add(student);
        System.out.print("\nThem thanh cong sinh vien: \n" + student);
    }

    public static Student findStudentById(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. ");
            return null;
        }

        Long id = Input.inputID();
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

    public static void updateStudentById(ArrayList<Student> studentLists) {
        Student foundStudent = DynamicStudentController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        Scanner sc = new Scanner(System.in);
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

                Utils.handleUpdateOption(studentLists, foundStudent, option);
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le. Vui long chon lai! ");
                sc.next();
            }
        }

        System.out.println("Sinh vien sau khi duoc sua: " + foundStudent);
    }

    public static void deleteStudentById(ArrayList<Student> studentLists) {
        Student foundStudent = findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the thuc hien chuc nang xoa sinh vien.");
            return;
        }

        studentLists.remove(foundStudent);
        System.out.println("Da xoa thanh cong.");
    }

    public static void displayStudentList(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi danh sach.");
            return;
        }

        System.out.println("\nDanh sach sinh vien: ");
        for (Student student : studentLists) {
            System.out.println(student);
        }
    }

    public static void displayAcademicAbility(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % hoc luc sinh vien.");
            return;
        }

        Map<LEVEL, Integer> levelMap = Utils.mapList(studentLists, "level");
        if (!levelMap.isEmpty()) {
            System.out.println("\nTy le hoc luc cua cac sinh vien: ");
            ArrayList<Map.Entry<LEVEL, Integer>> levelLists = new ArrayList<>(levelMap.entrySet());
            levelLists.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            for (Map.Entry<LEVEL, Integer> e : levelLists)
                if (e.getKey() != null) {
                    System.out.println(String.format("%s: %.2f%%", e.getKey().getVietnamese(), (double) e.getValue() / studentLists.size() * 100));
                }
        } else {
            System.out.println("Khong co du lieu phu hop.");
        }
    }

    public static void displayAverageRate(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % diem trung binh.");
            return;
        }

        Map<Double, Integer> averageMap = Utils.mapList(studentLists, "gpa");
        if (!averageMap.isEmpty()) {
            System.out.println("Ty le diem trung binh cua cac sinh vien: ");
            for (Map.Entry<Double, Integer> e : averageMap.entrySet()) {
                System.out.println(String.format("%.2f: %.2f%%", e.getKey(), (double) e.getValue() / studentLists.size() * 100));
            }
        } else {
            System.out.println("Du lieu khong hop le.");
        }
    }

    public static void displayStudentListByAcademicAbility(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
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

        if (count == studentLists.size()) {
            System.out.println("Khong co sinh vien nao co hoc luc '" + academicAbility.toLowerCase() + "'.");
        }
    }

    public static void writeToFile(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            return;
        }

        File file = new File(DYNAMIC_FILENAME);
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
