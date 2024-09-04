package utils;

import constant.LEVEL;
import model.Student;
import java.util.*;
import java.util.function.Supplier;
import static constant.Constant.MAX_YEAR;
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
        System.out.print("Nhap ngay sinh (dd/MM/yyyy) trong khoang 1900 - " + MAX_YEAR + ": ");
        String birthDate = sc.nextLine();

        while (!Validation.validatebirthDate(birthDate)) {
            System.out.print("Ngay sinh khong hop le (dd/MM/yyyy) trong khoang 1900 - " + MAX_YEAR + ". Vui long nhap lai! ");
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
        Double height = getValidInput(sc::nextDouble, "Nhap chieu cao khong dung.");

        while (!Validation.validateHeight(height)) {
            System.out.print("Chieu cao khong hop le (50.0 - 300.0). Vui long nhap lai! ");
            height = getValidInput(sc::nextDouble, "Nhap chieu cao khong dung.");
        }

        return height;
    }

    public static double inputWeight() {
        System.out.print("Nhap can nang trong khoang 5.0 - 1000.0: ");
        Double weight = getValidInput(sc::nextDouble, "Nhap can nang khong dung.");

        while (!Validation.validateWeight(weight)) {
            System.out.print("Can nang khong hop le (5.0 - 1000.0). Vui long nhap lai! ");
            weight = getValidInput(sc::nextDouble, "Nhap can nang khong dung.");
        }

        sc.nextLine();
        return weight;
    }

    public static String inputStudentId(ArrayList<Student> studentLists) {
        boolean checkStudentIdRepeat;

        System.out.print("Nhap ma sinh vien (10 ki tu): ");
        String studentId = sc.nextLine();
        checkStudentIdRepeat = Input.checkStudentIdDuplicate(studentLists, studentId);

        while (!Validation.validateStudentId(studentId) || checkStudentIdRepeat) {
            if (checkStudentIdRepeat) {
                System.out.print("Ma sinh vien da bi trung. Vui long nhap lai! ");
            } else if (!Validation.validateStudentId(studentId)) {
                System.out.print("Ma sinh vien khong hop le (Ma sinh vien khong trong, khong trung va phai dung 10 ki tu). Vui long nhap lai! ");
            }

            studentId = sc.nextLine();
            checkStudentIdRepeat = Input.checkStudentIdDuplicate(studentLists, studentId);
        }

        return studentId;
    }

    public static boolean checkStudentIdDuplicate(ArrayList<Student> studentLists, String studentId) {
        if (studentLists != null && studentCount > 0) {
            for (Student student : studentLists) {
                if (student != null) {
                    if (studentId.equals(student.getStudentId())) {
                        return true;
                    }
                }
            }
        }

        return false;
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
        Integer startYear;

        System.out.print("Nhap nam bat dau hoc dai hoc (1900 - " + MAX_YEAR + "): ");
        startYear = Input.getValidInput(sc::nextInt, "Nhap nam bat dau khong dung.");

        while (!Validation.validateStartYear(startYear)) {
            System.out.print("Nam bat dau hoc khong hop le (1900 - " + MAX_YEAR + "). Vui long nhap lai! ");
            startYear = Input.getValidInput(sc::nextInt, "Nhap nam bat dau khong dung.");
        }

        return startYear;
    }

    public static double inputGpa() {
        Double gpa;

        System.out.print("Nhap diem trung binh tich luy (0.0 - 10.0): ");
        gpa = Input.getValidInput(sc::nextDouble, "Nhap diem trung binh khong dung.");

        while (!Validation.validateGpa(gpa)) {
            System.out.print("Diem trung binh tich luy khong hop le (0.0 - 10.0). Vui long nhap lai! ");
            gpa = Input.getValidInput(sc::nextDouble, "Nhap diem trung binh khong dung.");
        }

        sc.nextLine();
        return gpa;
    }

    public static int inputID() {
        Scanner sc = new Scanner(System.in);
        int id = 0;

        System.out.print("Nhap ID cua sinh vien (ID > 0): ");

        do {
            try {
                id = sc.nextInt();
                if (id <= 0) {
                    System.out.print("ID khong hop le (ID > 0). Vui long nhap lai! ");
                }
            } catch (Exception e) {
                System.out.print("Nhap ID khong dung, ID la so nguyen (ID > 0). Vui long nhap lai! ");
                sc.next();
            }
        } while (id <= 0);

        return id;
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

    public static <T> T getValidInput(Supplier<T> inputMethod, String errorMessage) {
        T input = null;
        try {
            input = inputMethod.get();
        } catch (Exception e) {
            System.out.println(errorMessage);
            sc.next();
        }

        return input;
    }
}
