package controller;

import constant.LEVEL;
import model.Student;
import utils.Input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static constant.Constant.FILENAME;

public class StudentListController {
    public static void createStudentById(ArrayList<Student> studentLists) {
        System.out.println("Nhap sinh vien moi: ");
        Student student = Input.inputCreateStudentList(studentLists);

        studentLists.add(student);
        System.out.print("\nThem thanh cong sinh vien: \n" + student);
    }


    public static Student findStudentById(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. ");
            return null;
        }

        int id = Input.inputID();
        if (id > studentLists.size()) {
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

    public static void updateStudentById(ArrayList<Student> studentLists) {
        Scanner sc = new Scanner(System.in);
        Student foundStudent = StudentListController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        Student originalValues = new Student(foundStudent);
        while (true) {
            Input.showUpdateOption();
            try {
                System.out.print("Lua chon cua ban: ");
                int option = sc.nextInt();
                if (option == 0) {
                    break;
                } else if (option == 10) {
                    Input.restoreOriginalValues(foundStudent, originalValues);
                    break;
                }

                Input.handleUpdateOptionList(studentLists, foundStudent, option);
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

    public static void displayAverageRate(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
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
            return;
        }
    }

    public static void writeToFile(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
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
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu vao file: " + e.getMessage());
        }
    }
}
