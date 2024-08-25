package utils;

import constant.LEVEL;
import model.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static view.ConsoleUI.studentCount;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static String inputName() {
        System.out.print("Nhap ten (< 100 ki tu): ");
        String name = sc.nextLine();
        while (!Validation.validateName(name)) {
            System.out.print("Ten khong hop le (Ten khong duoc de trong, khong duoc chua so va < 100 ki tu). Vui long nhap lai! ");
            name = sc.nextLine();
        }

        return name;
    }

    public static String inputBirthDate() {
        System.out.print("Nhap ngay sinh (dd/MM/yyyy) trong khoang 1900 - " + LocalDateTime.now().getYear() + ": ");
        String birthDate = sc.nextLine();
        while (!Validation.validatebirthDate(birthDate)) {
            System.out.print("Ngay sinh khong hop le (dd/MM/yyyy) trong khoang 1900 - " + LocalDateTime.now().getYear() + ". Vui long nhap lai! ");
            birthDate = sc.nextLine();
        }

        return birthDate;
    }

    public static String inputAddress() {
        System.out.print("Nhap dia chi (< 300 ki tu): ");
        String address = sc.nextLine();
        while (!Validation.validateAddress(address)) {
            System.out.print("Dia chi khong hop le (dia chi khong de trong va < 300 ki tu). Vui long nhap lai! ");
            address = sc.nextLine();
        }

        return address;
    }

    public static double inputHeight() {
        System.out.print("Nhap chieu cao trong khoang 50.0 - 300.0: ");
        double height = 0;
        try {
            height = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Nhap chieu cao khong dung.");
            sc.next();
        }
        while (!Validation.validateHeight(height)) {
            System.out.print("Chieu cao khong hop le (50.0 - 300.0). Vui long nhap lai! ");
            try {
                height = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Nhap chieu cao khong dung.");
                sc.next();
            }
        }

        return height;
    }

    public static double inputWeight() {
        System.out.print("Nhap can nang trong khoang 5.0 - 1000.0: ");
        double weight = 0;
        try {
            weight = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Nhap can nang khong dung.");
            sc.next();
        }
        while (!Validation.validateWeight(weight)) {
            System.out.print("Can nang khong hop le (5.0 - 1000.0). Vui long nhap lai! ");
            try {
                weight = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Nhap can nang khong dung.");
                sc.next();
            }
        }
        sc.nextLine();

        return weight;
    }

    public static String inputStudentIdArray(Student[] studentLists) {
        boolean checkStudentIdRepeat = false;

        System.out.print("Nhap ma sinh vien (10 ki tu): ");
        String studentId = sc.nextLine();
        if (studentLists != null && studentCount > 0){
            for (Student student : studentLists) {
                if (student != null) {
                    if (studentId.equals(student.getStudentId())) {
                        checkStudentIdRepeat = true;
                        break;
                    }
                }
            }
        }

        while (!Validation.validateStudentId(studentId) || checkStudentIdRepeat) {
            if (checkStudentIdRepeat) {
                System.out.print("Ma sinh vien da bi trung. Vui long nhap lai! ");
                checkStudentIdRepeat = false;
            } else if (!Validation.validateStudentId(studentId)) {
                System.out.print("Ma sinh vien khong hop le (Ma sinh vien khong trong, khong trung va phai dung 10 ki tu). Vui long nhap lai! ");
            }

            studentId = sc.nextLine();

            if (studentLists != null && studentCount > 0){
                for (Student student : studentLists) {
                    if (student != null) {
                        if (studentId.equals(student.getStudentId())) {
                            checkStudentIdRepeat = true;
                            break;
                        }
                    }
                }
            }
        }

        return studentId;
    }


    public static String inputStudentIdList(ArrayList<Student> studentLists) {

        boolean checkStudentIdRepeat = false;

        System.out.print("Nhap ma sinh vien (10 ki tu): ");
        String studentId = sc.nextLine();
        if (studentLists != null && !studentLists.isEmpty()) {
            for (Student student : studentLists) {
                if (student != null) {
                    if (studentId.equals(student.getStudentId())) {
                        checkStudentIdRepeat = true;
                    }
                }
            }
        }

        while (!Validation.validateStudentId(studentId) || checkStudentIdRepeat) {
            if (checkStudentIdRepeat) {
                System.out.print("Ma sinh vien da bi trung. Vui long nhap lai! ");
                checkStudentIdRepeat = false;
            } else if (!Validation.validateStudentId(studentId)) {
                System.out.print("Ma sinh vien khong hop le (Ma sinh vien khong trong, khong trung va phai dung 10 ki tu). Vui long nhap lai! ");
            }

            studentId = sc.nextLine();

            if (studentLists != null && !studentLists.isEmpty()) {
                for (Student student : studentLists) {
                    if (student != null) {
                        if (studentId.equals(student.getStudentId())) {
                            checkStudentIdRepeat = true;
                        }
                    }
                }
            }
        }

        return studentId;
    }

    public static String inputSchool() {
        System.out.print("Nhap ten truong hoc (< 200 ki tu): ");
        String school = sc.nextLine();
        while (!Validation.validateSchool(school)) {
            System.out.print("Ten truong hoc khong hop le (ten truong khong trong va < 200 ki tu). Vui long nhap lai! ");
            school = sc.nextLine();
        }

        return school;
    }

    public static int inputStartYear() {
        System.out.print("Nhap nam bat dau hoc dai hoc (1900 - " + LocalDateTime.now().getYear() + "): ");
        int startYear = 0;
        try {
            startYear = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nhap nam bat dau khong dung.");
            sc.next();
        }
        while (!Validation.validateStartYear(startYear)) {
            System.out.print("Nam bat dau hoc khong hop le (1900 - " + LocalDateTime.now().getYear() + "). Vui long nhap lai! ");
            try {
                startYear = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Nhap nam bat dau khong dung.");
                sc.next();
            }
        }

        return startYear;
    }

    public static double inputGpa() {
        System.out.print("Nhap diem trung binh tich luy (0.0 - 10.0): ");
        double gpa = -1;
        try {
            gpa = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Nhap diem trung binh khong dung.");
            sc.next();
        }
        while (!Validation.validateGpa(gpa)) {
            System.out.print("Diem trung binh tich luy khong hop le (0.0 - 10.0). Vui long nhap lai! ");
            try {
                gpa = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Nhap diem trung binh khong dung.");
                sc.next();
            }
        }
        sc.nextLine();

        return gpa;
    }


    public static int inputID() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID cua sinh vien (ID > 0): ");
        int id = 0;
        try {
            id = sc.nextInt();
            if (id <= 0) {
                System.out.print("ID khong hop le (ID > 0). Vui long nhap lai! ");
            }
        } catch (Exception e) {
            System.out.print("Nhap ID khong dung, ID la so nguyen (ID > 0). Vui long nhap lai! ");
            sc.next();
        }

        while (id <= 0) {
            try {
                id = sc.nextInt();
                if (id <= 0) {
                    System.out.print("ID khong hop le (ID > 0). Vui long nhap lai! ");
                }
            } catch (Exception e) {
                System.out.print("Nhap ID khong dung, ID la so nguyen (ID > 0). Vui long nhap lai! ");
                sc.next();
            }
        }

        sc.nextLine();
        return id;
    }

    public static void showOptionUpdate() {
        /**
         * String name, LocalDate birthDate, String address, double height,
         *                    double weight, String studentId, String school, int startYear, double gpa
         */
        System.out.println("\n------------Chon thong tin muon cap nhat------------");
        System.out.println("1. Cap nhat ten.");
        System.out.println("2. Cap nhat ngay sinh.");
        System.out.println("3. Cap nhat dia chi.");
        System.out.println("4. Cap nhat chieu cao.");
        System.out.println("5. Cap nhat can nang.");
        System.out.println("6. Cap nhat ma sinh vien.");
        System.out.println("7. Cap nhat ten truong.");
        System.out.println("8. Cap nhat nam bat dau hoc.");
        System.out.println("9. Cap nhat diem trung binh.");
        System.out.println("10. Thoat (khong cap nhat du lieu).");
        System.out.println("0. Xong");
        System.out.println("-------------------------------------------------");
    }

    public static String inputLevel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap hoc luc cua sinh vien (Kem, Yeu, Trung binh, Kha, Gioi, Xuat sac): ");
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

        return academicAbility;
    }
}
