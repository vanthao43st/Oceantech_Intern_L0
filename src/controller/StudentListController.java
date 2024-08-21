package controller;

import constant.LEVEL;
import model.Student;
import utils.Validation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static constant.Constant.FILENAME;

public class StudentListController {
    public static void createStudentById(ArrayList<Student> studentLists) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap sinh vien moi: ");

        System.out.print("Nhap ten (< 100 ki tu): ");
        String name = sc.nextLine();
        while (!Validation.validateName(name)) {
            System.out.print("Ten khong hop le (Ten khong duoc de trong, khong duoc chua so va < 100 ki tu). Vui long nhap lai! ");
            name = sc.nextLine();
        }

        System.out.print(STR."Nhap ngay sinh (dd/MM/yyyy) trong khoang 1900 - \{LocalDateTime.now().getYear()}: ");
        String birthDate = sc.nextLine();
        while (!Validation.validatebirthDate(birthDate)) {
            System.out.print("Ngay sinh khong hop le (dd/MM/yyyy) trong khoang 1900 - " + LocalDateTime.now().getYear() + ". Vui long nhap lai! ");
            birthDate = sc.nextLine();
        }

        System.out.print("Nhap dia chi (< 300 ki tu): ");
        String address = sc.nextLine();
        while (!Validation.validateAddress(address)) {
            System.out.print("Dia chi khong hop le (dia chi khong de trong va < 300 ki tu). Vui long nhap lai! ");
            address = sc.nextLine();
        }

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

        System.out.print("Nhap ma sinh vien (10 ki tu): ");
        String studentId = sc.nextLine();
        while (!Validation.validateStudentId(studentId)) {
            System.out.print("Ma sinh vien khong hop le (Ma sinh vien khong trong va phai dung 10 ki tu). Vui long nhap lai! ");
            studentId = sc.nextLine();
        }

        System.out.print("Nhap ten truong hoc (< 200 ki tu): ");
        String school = sc.nextLine();
        while (!Validation.validateSchool(school)) {
            System.out.print("Ten truong hoc khong hop le (ten truong khong trong va < 200 ki tu). Vui long nhap lai! ");
            school = sc.nextLine();
        }

        System.out.print(STR."Nhap nam bat dau hoc dai hoc (1900 - \{LocalDateTime.now().getYear()}): ");
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

        Student student = new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), address, height, weight, studentId, school, startYear, gpa);
        studentLists.add(student);
        System.out.print("\nThem thanh cong sinh vien: \n" + student);
    }


    public static Student findStudentById(ArrayList<Student> studentLists) {
        Scanner sc = new Scanner(System.in);

        if (studentLists.isEmpty()) {
            System.out.print("Danh sach sinh vien rong. ");
            return null;
        }

        System.out.print("Nhap ID cua sinh vien (ID > 0): ");
        int id = 0;
        try {
            id = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nhap ID khong dung, ID la so nguyen trong khoang (1 - " + studentLists.size() + ").");
            sc.next();
        }
        while (id <= 0 || id > studentLists.size()) {
            System.out.print("ID khong hop le (1 - " + studentLists.size() + "). Vui long nhap lai. ");
            try {
                id = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Nhap ID khong dung, ID la so nguyen trong khoang (1 - " + studentLists.size() + ").");
                sc.next();
            }
        }

        for (Student student : studentLists) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }


    public static void updateStudentById(ArrayList<Student> studentLists) {
        Scanner sc = new Scanner(System.in);
        Student foundStudent = StudentListController.findStudentById(studentLists);
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

                    switch (option){
                        case 1:
                            System.out.print("Nhap ten (< 100 ki tu): ");
                            name = sc.nextLine();
                            while (!Validation.validateName(name)) {
                                System.out.print("Ten khong hop le (Ten khong duoc de trong, khong duoc chua so va < 100 ki tu). Vui long nhap lai! ");
                                name = sc.nextLine();
                            }
                            break;
                        case 2:
                            System.out.print(STR."Nhap ngay sinh (dd/MM/yyyy) trong khoang 1900 - \{LocalDateTime.now().getYear()}: ");
                            birthDate = sc.nextLine();
                            while (!Validation.validatebirthDate(birthDate)) {
                                System.out.print("Ngay sinh khong hop le (dd/MM/yyyy) trong khoang 1900 - " + LocalDateTime.now().getYear() + ". Vui long nhap lai! ");
                                birthDate = sc.nextLine();
                            }
                            break;
                        case 3:
                            System.out.print("Nhap dia chi (< 300 ki tu): ");
                            address = sc.nextLine();
                            while (!Validation.validateAddress(address)) {
                                System.out.print("Dia chi khong hop le (dia chi khong de trong va < 300 ki tu). Vui long nhap lai! ");
                                address = sc.nextLine();
                            }
                            break;
                        case 4:
                            System.out.print("Nhap chieu cao trong khoang 50.0 - 300.0: ");
                            height = 0;
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
                            break;
                        case 5:
                            System.out.print("Nhap can nang trong khoang 5.0 - 1000.0: ");
                            weight = 0;
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
                            break;
                        case 6:
                            System.out.print("Nhap ma sinh vien (10 ki tu): ");
                            studentId = sc.nextLine();
                            while (!Validation.validateStudentId(studentId)) {
                                System.out.print("Ma sinh vien khong hop le (Ma sinh vien khong trong va phai dung 10 ki tu). Vui long nhap lai! ");
                                studentId = sc.nextLine();
                            }
                            break;
                        case 7:
                            System.out.print("Nhap ten truong hoc (< 200 ki tu): ");
                            school = sc.nextLine();
                            while (!Validation.validateSchool(school)) {
                                System.out.print("Ten truong hoc khong hop le (ten truong khong trong va < 200 ki tu). Vui long nhap lai! ");
                                school = sc.nextLine();
                            }
                            break;
                        case 8:
                            System.out.print(STR."Nhap nam bat dau hoc dai hoc (1900 - \{LocalDateTime.now().getYear()}): ");
                            startYear = 0;
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
                            break;
                        case 9:
                            System.out.print("Nhap diem trung binh tich luy (0.0 - 10.0): ");
                            gpa = -1;
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

        System.out.print(STR."Sinh vien sau khi duoc sua: \{foundStudent}");
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

        if (count == studentLists.size()) {
            System.out.println("Khong co sinh vien nao co hoc luc '" + academicAbility.toLowerCase() + "'");
            return;
        }
    }

    public static void writeToFile(ArrayList<Student> studentLists) {
        if (studentLists.isEmpty()) {
            System.out.println("Mang dong: Danh sach sinh vien rong. Khong co sinh vien nao duoc ghi ra file.");
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
            System.out.println("Mang dong: Danh sach sinh vien da duoc ghi vao file.");
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu vao file: " + e.getMessage());
        }
    }
}
