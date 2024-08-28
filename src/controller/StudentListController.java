package controller;

import model.Person;
import model.Student;
import utils.Input;
import utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static constant.Constant.FILENAME;

public class StudentListController {
    public static void createStudentById(ArrayList<Student> studentLists) {
        System.out.println("Nhap sinh vien moi: ");
        Student student = Utils.inputCreateStudentList(studentLists);

        studentLists.add(student);
        System.out.print("\nThem thanh cong sinh vien: \n" + student);
    }

    public static Student findStudentById(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
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

    public static void updateStudentById(ArrayList<Student> studentLists) {
        Student foundStudent = StudentListController.findStudentById(studentLists);
        if (foundStudent == null) {
            System.out.println("Khong the cap nhat sinh vien.");
            return;
        }

        Utils.handleUpdateStudentList(studentLists, foundStudent);

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

        Utils.showAcademicAbility(studentLists);
    }

    public static void displayAverageRate(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi % diem trung binh.");
            return;
        }

        Utils.showAverageRate(studentLists);
    }

    public static void displayStudentListByAcademicAbility(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Danh sach sinh vien rong. Khong the hien thi sinh vien theo hoc luc.");
            return;
        }

        Utils.handleAcademicAbility(studentLists);
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
