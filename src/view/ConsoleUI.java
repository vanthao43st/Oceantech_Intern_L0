package view;

import controller.StaticStudentController;
import controller.DynamicStudentController;
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
                    StaticStudentController.writeToFile(studentArrays);
                    DynamicStudentController.writeToFile(studentLists);
                    System.out.println("Ban da thoat chuong trinh!");
                    break;
                } else if (option == 1) {
                    Person.setIdCounter(1L);
                    System.out.println("Ban da chon mang tinh. Vui long lua chon chuc nang!");
                    while (true) {
                        System.out.println("\n**********Mang tinh**********");
                        System.out.println("1. Tao sinh vien.");
                        System.out.println("2. Tim kiem sinh vien theo ID.");
                        System.out.println("3. Cap nhat sinh vien theo ID.");
                        System.out.println("4. Xoa sinh vien theo ID.");
                        System.out.println("5. Hien thi danh sach sinh vien.");
                        System.out.println("6. Hien thi % hoc luc cua sinh vien.");
                        System.out.println("7. Hien thi % diem trung binh cua sinh vien.");
                        System.out.println("8. Hien thi danh sach sinh vien theo hoc luc.");
                        System.out.println("0. Thoat.");
                        System.out.println("*****************************");

                        try {
                            System.out.print("Lua chon cua ban: ");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                System.out.println("Ban da thoat chuong trinh.");
                                break;
                            }
                            switch (choice) {
                                case 1:
                                    StaticStudentController.createStudentById(studentArrays);
                                    break;
                                case 2:
                                    StaticStudentController.findStudentById(studentArrays);
                                    break;
                                case 3:
                                    StaticStudentController.updateStudentById(studentArrays);
                                    break;
                                case 4:
                                    StaticStudentController.deleteStudentById(studentArrays);
                                    break;
                                case 5:
                                    StaticStudentController.displayStudentList(studentArrays);
                                    break;
                                case 6:
                                    StaticStudentController.displayAcademicAbility(studentArrays);
                                    break;
                                case 7:
                                    StaticStudentController.displayAverageRate(studentArrays);
                                    break;
                                case 8:
                                    StaticStudentController.displayStudentListByAcademicAbility(studentArrays);
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
                    Person.setIdCounter(1L);
                    System.out.println("Ban da chon mang dong. Vui long lua chon chuc nang!");
                    while (true) {
                        System.out.println("\n**********Mang dong**********");
                        System.out.println("1. Tao sinh vien.");
                        System.out.println("2. Tim kiem sinh vien theo ID.");
                        System.out.println("3. Cap nhat sinh vien theo ID.");
                        System.out.println("4. Xoa sinh vien theo ID.");
                        System.out.println("5. Hien thi danh sach sinh vien.");
                        System.out.println("6. Hien thi % hoc luc cua sinh vien.");
                        System.out.println("7. Hien thi % diem trung binh cua sinh vien.");
                        System.out.println("8. Hien thi danh sach sinh vien theo hoc luc.");
                        System.out.println("0. Thoat.");
                        System.out.println("*****************************");

                        try {
                            System.out.print("Lua chon cua ban: ");
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                System.out.println("Ban da thoat chuong trinh.");
                                break;
                            }
                            switch (choice) {
                                case 1:
                                    DynamicStudentController.createStudentById(studentLists);
                                    break;
                                case 2:
                                    DynamicStudentController.findStudentById(studentLists);
                                    break;
                                case 3:
                                    DynamicStudentController.updateStudentById(studentLists);
                                    break;
                                case 4:
                                    DynamicStudentController.deleteStudentById(studentLists);
                                    break;
                                case 5:
                                    DynamicStudentController.displayStudentList(studentLists);
                                    break;
                                case 6:
                                    DynamicStudentController.displayAcademicAbility(studentLists);
                                    break;
                                case 7:
                                    DynamicStudentController.displayAverageRate(studentLists);
                                    break;
                                case 8:
                                    DynamicStudentController.displayStudentListByAcademicAbility(studentLists);
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