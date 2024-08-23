package controller;

import constant.LEVEL;
import model.Student;
import utils.Input;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static constant.Constant.FILENAME;
import static constant.Constant.MAX_STUDENT;
import static view.ConsoleUI.studentCount;

public class StudentArrayController {
    public static void createStudentById(Student[] studentLists) {
        System.out.println("Nhap sinh vien moi: ");

        String name = Input.inputName();
        String birthDate = Input.inputBirthDate();
        String address = Input.inputAddress();
        double height = Input.inputHeight();
        double weight = Input.inputWeight();
        String studentId = Input.inputStudentId();
        String school = Input.inputSchool();
        int startYear = Input.inputStartYear();
        double gpa = Input.inputGpa();

        Student student = new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), address, height, weight, studentId, school, startYear, gpa);
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

        int id = Input.inputID();

        if (id > studentCount) {
            System.out.println("Khong co du lieu phu hop.");
            return null;
        }

        for (Student student : studentLists) {
            if (student.getId() == id) {
                System.out.println("Sinh vien: " + student);
                return student;
            }
        }

        System.out.println("Khong co du lieu phu hop.");
        return null;
    }

    public static void updateStudentById(Student[] studentLists) {
        Scanner sc = new Scanner(System.in);
        Student foundStudent = StudentArrayController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        if (foundStudent != null) {
            boolean shouldUpdate = true;
            String oldName = foundStudent.getName();
            String oldBirthDate = foundStudent.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String oldAddress = foundStudent.getAddress();
            double oldHeight = foundStudent.getHeight();
            double oldWeight = foundStudent.getWeight();
            String oldStudentId = foundStudent.getStudentId();
            String oldSchool = foundStudent.getSchool();
            int oldStartYear = foundStudent.getStartYear();
            double oldGpa = foundStudent.getGpa();

            String name = oldName;
            String birthDate = oldBirthDate;
            String address = oldAddress;
            double height = oldHeight;
            double weight = oldWeight;
            String studentId = oldStudentId;
            String school = oldSchool;
            int startYear = oldStartYear;
            double gpa = oldGpa;

            while (true) {
                Input.showOptionUpdate();

                try {
                    System.out.print("Lua chon cua ban: ");
                    int option = sc.nextInt();
                    sc.nextLine();
                    if (option == 0) {
                        break;
                    } else if (option == 10) {
                        foundStudent.setName(oldName);
                        foundStudent.setBirthDate(LocalDate.parse(oldBirthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        foundStudent.setAddress(oldAddress);
                        foundStudent.setHeight(oldHeight);
                        foundStudent.setWeight(oldWeight);
                        foundStudent.setSchool(oldSchool);
                        foundStudent.setStudentId(oldStudentId);
                        foundStudent.setStartYear(oldStartYear);
                        foundStudent.setGpa(oldGpa);
                        shouldUpdate = false;
                        break;
                    }

                    switch (option) {
                        case 1:
                            name = Input.inputName();
                            break;
                        case 2:
                            birthDate = Input.inputBirthDate();
                            break;
                        case 3:
                            address = Input.inputAddress();
                            break;
                        case 4:
                            height = Input.inputHeight();
                            break;
                        case 5:
                            weight = Input.inputWeight();
                            break;
                        case 6:
                            studentId = Input.inputStudentId();
                            break;
                        case 7:
                            school = Input.inputSchool();
                            break;
                        case 8:
                            startYear = Input.inputStartYear();
                            break;
                        case 9:
                            gpa = Input.inputGpa();
                            break;
                        default:
                            System.out.println("Lua chon khong ton tai. Vui long nhap lai! ");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Lua chon khong hop le. Vui long chon lai! ");
                    sc.next();
                }
            }

            if (shouldUpdate) {
                foundStudent.setName(name);
                foundStudent.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                foundStudent.setAddress(address);
                foundStudent.setHeight(height);
                foundStudent.setWeight(weight);
                foundStudent.setSchool(school);
                foundStudent.setStudentId(studentId);
                foundStudent.setStartYear(startYear);
                foundStudent.setGpa(gpa);
            }
        }

        System.out.print("Sinh vien sau khi duoc sua: " + foundStudent);
    }


    public static void deleteStudentById(Student[] studentLists) {
        Student foundStudent = findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the thuc hien chuc nang xoa sinh vien.");
            return;
        }

        int deleteIndex = 0;
        for (int i = 0; i < studentCount; i++) {
            if (studentLists[i] == foundStudent) {
                deleteIndex = i;
            }
        }

        for (int i = deleteIndex; i < studentCount - 1; i++) {
            studentLists[i] = studentLists[i + 1];
        }
        --studentCount;

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
        Map<LEVEL, Integer> levelMap = new HashMap<>();
        int studentCount = 0;
        for (Student student : studentLists) {
            if (student != null) {
                studentCount++;
                if (student.getLevel() != null) {
                    if (!levelMap.containsKey(student.getLevel())) {
                        levelMap.put(student.getLevel(), 1);
                    } else {
                        levelMap.replace(student.getLevel(), levelMap.get(student.getLevel()) + 1);
                    }
                }
            }
        }

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
        Map<Double, Integer> averageMap = new TreeMap<>();
        int studentCount = 0;
        for (Student student : studentLists) {
            if (student != null) {
                studentCount++;
                if (!averageMap.containsKey(student.getGpa())) {
                    averageMap.put(student.getGpa(), 1);
                } else {
                    averageMap.replace(student.getGpa(), averageMap.get(student.getGpa()) + 1);
                }
            }
        }

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
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap hoc luc cua sinh vien: ");
        String academicAbility = sc.nextLine();
        while (!academicAbility.equalsIgnoreCase(LEVEL.KEM.getVietnamese())
                && !academicAbility.equalsIgnoreCase(LEVEL.YEU.getVietnamese())
                && !academicAbility.equalsIgnoreCase(LEVEL.TRUNG_BINH.getVietnamese())
                && !academicAbility.equalsIgnoreCase(LEVEL.KHA.getVietnamese())
                && !academicAbility.equalsIgnoreCase(LEVEL.GIOI.getVietnamese())
                && !academicAbility.equalsIgnoreCase(LEVEL.XUAT_SAC.getVietnamese())) {
            System.out.print("Hoc luc khong dung (Kem, Yeu, Trung binh, Kha, Gioi, Xuat sac). ");
            academicAbility = sc.nextLine();
        }

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
            System.out.println("Khong co sinh vien nao co hoc luc '" + academicAbility.toLowerCase() + "'");
            return;
        }
    }

    public static void writeToFile(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Mang tinh: Danh sach sinh vien rong. Khong co sinh vien nao duoc ghi ra file.");
            return;
        }

        File file = new File(FILENAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Student student : studentLists) {
                if (student != null) {
                    writer.write(student.toString());
                    writer.newLine();
                }
            }
            System.out.println("Mang tinh: Danh sach sinh vien da duoc ghi vao file.");
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu vao file: " + e.getMessage());
        }
    }
}
