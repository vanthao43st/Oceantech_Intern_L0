package view;

import controller.StudentArrayController;
import controller.StudentListController;
import model.Person;
import model.Student;
import java.util.ArrayList;
import java.util.Scanner;
import static constant.Constant.MAX_STUDENT;

public class ConsoleUI {
    public static ArrayList<Student> studentLists = new ArrayList<>();
    public static Student[] studentArrays = new Student[MAX_STUDENT];
    public static int studentCount = 0;

    public ConsoleUI() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n-----------------Chon mang------------------");
            System.out.println("1. Chon mang tinh.");
            System.out.println("2. Chon mang dong.");
            System.out.println("0. Thoat va ghi du lieu sinh vien ra file.");
            System.out.println("--------------------------------------------");

            try {
                System.out.print("Lua chon cua ban: ");
                int option = sc.nextInt();
                if (option == 0) {
                    StudentArrayController.writeToFile(studentArrays);
                    StudentListController.writeToFile(studentLists);
                    System.out.println("Ban da thoat chuong trinh!");
                    break;
                } else if (option == 1) {
                    Person.setIdCounter(1);
                    System.out.println("Ban da chon mang tinh. Vui long lua chon chuc nang!");
                    while (true) {
                        System.out.println("\n**********");
                        System.out.println("1. Tao sinh vien.");
                        System.out.println("2. Tim kiem sinh vien theo ID.");
                        System.out.println("3. Cap nhat sinh vien theo ID.");
                        System.out.println("4. Xoa sinh vien theo ID.");
                        System.out.println("5. Hien thi danh sach sinh vien.");
                        System.out.println("6. Hien thi % hoc luc cua sinh vien.");
                        System.out.println("7. Hien thi % diem trung binh cua sinh vien.");
                        System.out.println("8. Hien thi danh sach sinh vien theo hoc luc.");
                        System.out.println("0. Thoat.");
                        System.out.println("**********");

                        try {
                            System.out.print("Lua chon cua ban: ");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                System.out.println("Ban da thoat chuong trinh.");
                                break;
                            }
                            switch (choice) {
                                case 1:
                                    StudentArrayController.createStudentById(studentArrays);
                                    break;
                                case 2:
                                    Student foundStudent = StudentArrayController.findStudentById(studentArrays);
                                    if (foundStudent == null) {
                                        System.out.println("Khong co du lieu phu hop.");
                                        break;
                                    } else {
                                        System.out.println(STR."Sinh vien can tim: \{foundStudent}");
                                    }
                                    break;
                                case 3:
                                    StudentArrayController.updateStudentById(studentArrays);
                                    break;
                                case 4:
                                    StudentArrayController.deleteStudentById(studentArrays);
                                    break;
                                case 5:
                                    System.out.println("\nDanh sach sinh vien: ");
                                    StudentArrayController.displayStudentList(studentArrays);
                                    break;
                                case 6:
                                    StudentArrayController.displayAcademicAbility(studentArrays);
                                    break;
                                case 7:
                                    StudentArrayController.displayAverageRate(studentArrays);
                                    break;
                                case 8:
                                    StudentArrayController.displayStudentListByAcademicAbility(studentArrays);
                                    break;
                                default:
                                    System.out.println("Lua chon khong ton tai. Vui long chon lai!");
                            }
                        } catch (Exception e) {
                            System.out.println("Lua chon khong hop le! Vui long chon lai!");
                            sc.next();
                        }
                    }
                } else if (option == 2) {
                    Person.setIdCounter(1);
                    System.out.println("Ban da chon mang dong. Vui long lua chon chuc nang!");
                    while (true) {
                        System.out.println("\n**********");
                        System.out.println("1. Tao sinh vien.");
                        System.out.println("2. Tim kiem sinh vien theo ID.");
                        System.out.println("3. Cap nhat sinh vien theo ID.");
                        System.out.println("4. Xoa sinh vien theo ID.");
                        System.out.println("5. Hien thi danh sach sinh vien.");
                        System.out.println("6. Hien thi % hoc luc cua sinh vien.");
                        System.out.println("7. Hien thi % diem trung binh cua sinh vien.");
                        System.out.println("8. Hien thi danh sach sinh vien theo hoc luc.");
                        System.out.println("0. Thoat.");
                        System.out.println("**********");

                        try {
                            System.out.print("Lua chon cua ban: ");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                System.out.println("Ban da thoat chuong trinh.");
                                break;
                            }
                            switch (choice) {
                                case 1:
                                    StudentListController.createStudentById(studentLists);
                                    break;
                                case 2:
                                    Student foundStudent = StudentListController.findStudentById(studentLists);
                                    if (foundStudent == null) {
                                        System.out.println("Khong co du lieu phu hop.");
                                        break;
                                    } else {
                                        System.out.println(STR."Sinh vien can tim: \{foundStudent}");
                                    }
                                    break;
                                case 3:
                                    StudentListController.updateStudentById(studentLists);
                                    break;
                                case 4:
                                    StudentListController.deleteStudentById(studentLists);
                                    break;
                                case 5:
                                    StudentListController.displayStudentList(studentLists);
                                    break;
                                case 6:
                                    StudentListController.displayAcademicAbility(studentLists);
                                    break;
                                case 7:
                                    StudentListController.displayAverageRate(studentLists);
                                    break;
                                case 8:
                                    StudentListController.displayStudentListByAcademicAbility(studentLists);
                                    break;
                                default:
                                    System.out.println("Lua chon khong ton tai. Vui long chon lai!");
                            }
                        } catch (Exception e) {
                            System.out.println("Lua chon khong hop le! Vui long chon lai!");
                            sc.next();
                        }
                    }
                } else {
                    System.out.println("Lua chon khong ton tai. Vui long chon lai!");
                }
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le! Vui long chon lai!");
                sc.next();
            }
        }
    }
}