package controller;

import model.Person;
import model.Student;
import utils.Input;
import utils.Utils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import static constant.Constant.FILENAME;
import static constant.Constant.MAX_STUDENT;
import static view.ConsoleUI.studentCount;

public class StudentArrayController {
    public static void createStudentById(Student[] studentLists) {
        System.out.println("Nhap sinh vien moi: ");
        Student student = Utils.inputCreateStudentArray(studentLists);

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
        if (id > Person.getIdCounter()) {
            System.out.println("Khong co du lieu phu hop.");
            return null;
        }

        for (Student student : studentLists) {
            if (student != null) {
                if (student.getId() == id) {
                    System.out.println("Sinh vien: " + student);
                    return student;
                }
            }
        }

        System.out.println("Khong co du lieu phu hop.");
        return null;
    }

    public static void updateStudentById(Student[] studentLists) {
        Student foundStudent = StudentArrayController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        Utils.handleUpdateStudentArray(studentLists, foundStudent);

        System.out.println("Sinh vien sau khi duoc sua: " + foundStudent);
    }

    public static void deleteStudentById(Student[] studentLists) {
        Student foundStudent = findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the thuc hien chuc nang xoa sinh vien.");
            return;
        }

        Utils.handleDeleteStudent(studentLists, foundStudent);
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

        Utils.showAcademicAbility(studentLists);
    }

    public static void displayAverageRate(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % diem trung binh.");
            return;
        }

        Utils.showAverageRate(studentLists);
    }

    public static void displayStudentListByAcademicAbility(Student[] studentLists) {
        if (studentCount == 0) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi sinh vien theo hoc luc.");
            return;
        }

        Utils.handleAcademicAbility(studentLists);
    }

    public static void writeToFile(Student[] studentLists) {
        if (studentCount == 0) {
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
