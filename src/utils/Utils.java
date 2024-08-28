package utils;

import constant.LEVEL;
import model.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static view.ConsoleUI.studentCount;

public class Utils {
    public static Student inputCreateStudentArray(Student[] studentLists) {
        String name = Input.inputName();
        String birthDate = Input.inputBirthDate();
        String address = Input.inputAddress();
        double height = Input.inputHeight();
        double weight = Input.inputWeight();
        String studentId = Input.inputStudentIdArray(studentLists);
        String school = Input.inputSchool();
        int startYear = Input.inputStartYear();
        double gpa = Input.inputGpa();

        return new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                address, height, weight, studentId, school, startYear, gpa);
    }

    public static Student inputCreateStudentList(ArrayList<Student> studentLists) {
        String name = Input.inputName();
        String birthDate = Input.inputBirthDate();
        String address = Input.inputAddress();
        double height = Input.inputHeight();
        double weight = Input.inputWeight();
        String studentId = Input.inputStudentIdList(studentLists);
        String school = Input.inputSchool();
        int startYear = Input.inputStartYear();
        double gpa = Input.inputGpa();

        return new Student(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                address, height, weight, studentId, school, startYear, gpa);
    }

    public static void showUpdateOption() {
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

    public static void handleUpdateOptionArray(Student[] studentLists, Student student, int option) {
        switch (option) {
            case 1: student.setName(Input.inputName());     break;
            case 2: student.setBirthDate(LocalDate.parse(Input.inputBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));   break;
            case 3: student.setAddress(Input.inputAddress());   break;
            case 4: student.setHeight(Input.inputHeight());     break;
            case 5: student.setWeight(Input.inputWeight());     break;
            case 6: student.setStudentId(Input.inputStudentIdArray(studentLists));  break;
            case 7: student.setSchool(Input.inputSchool());     break;
            case 8: student.setStartYear(Input.inputStartYear());   break;
            case 9: student.setGpa(Input.inputGpa());   break;
            default: System.out.println("Lua chon khong ton tai. Vui long nhap lai! ");     break;
        }
    }


    public static void handleUpdateOptionList(ArrayList<Student> studentLists, Student student, int option) {
        switch (option) {
            case 1: student.setName(Input.inputName());     break;
            case 2: student.setBirthDate(LocalDate.parse(Input.inputBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));   break;
            case 3: student.setAddress(Input.inputAddress());   break;
            case 4: student.setHeight(Input.inputHeight());     break;
            case 5: student.setWeight(Input.inputWeight());     break;
            case 6: student.setStudentId(Input.inputStudentIdList(studentLists));   break;
            case 7: student.setSchool(Input.inputSchool());     break;
            case 8: student.setStartYear(Input.inputStartYear());   break;
            case 9: student.setGpa(Input.inputGpa());   break;
            default: System.out.println("Lua chon khong ton tai. Vui long nhap lai! ");     break;
        }
    }

    public static void restoreOriginalValues(Student student, Student oldStudent) {
        student.setName(oldStudent.getName());
        student.setBirthDate(oldStudent.getBirthDate());
        student.setAddress(oldStudent.getAddress());
        student.setHeight(oldStudent.getHeight());
        student.setWeight(oldStudent.getWeight());
        student.setStudentId(oldStudent.getStudentId());
        student.setSchool(oldStudent.getSchool());
        student.setStartYear(oldStudent.getStartYear());
        student.setGpa(oldStudent.getGpa());
    }

    public static void handleUpdateStudentArray(Student[] studentLists, Student foundStudent) {
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

                Utils.handleUpdateOptionArray(studentLists, foundStudent, option);
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le. Vui long chon lai! ");
                sc.next();
            }
        }
    }

    public static void handleUpdateStudentList(ArrayList<Student> studentLists, Student foundStudent) {
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

                Utils.handleUpdateOptionList(studentLists, foundStudent, option);
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le. Vui long chon lai! ");
                sc.next();
            }
        }
    }

    public static void handleDeleteStudent(Student[] studentLists, Student foundStudent) {
        int deleteIndex = 0;
        for (int i = 0; i < studentCount; i++) {
            if (studentLists[i] != null) {
                if (studentLists[i] == foundStudent) {
                    deleteIndex = i;
                }
            }
        }

        for (int i = deleteIndex; i < studentCount - 1; i++) {
            studentLists[i] = studentLists[i + 1];
        }
        studentLists[studentCount - 1] = null;
        studentCount--;
        System.out.println("Da xoa thanh cong.");
    }

    public static <T> Map<T, Integer> mapArray(Student[] studentLists, String field) {
        Map<T, Integer> resultMap = new TreeMap<>();

        for (Student student : studentLists) {
            if (student != null) {
                T key = null;
                if (field.equals("gpa")) {
                    key = (T) Double.valueOf(student.getGpa());
                } else if (field.equals("level")) {
                    key = (T) student.getLevel();
                }

                if (key != null) {
                    resultMap.put(key, resultMap.getOrDefault(key,0) + 1);
                }
            }
        }

        return resultMap;
    }


    public static <T> Map<T, Integer> mapList(ArrayList<Student> studentLists, String field) {
        Map<T, Integer> resultMap = new TreeMap<>();

        for (Student student : studentLists) {
            if (student != null) {
                T key = null;
                if (field.equals("gpa")) {
                    key = (T) Double.valueOf(student.getGpa());
                } else if (field.equals("level")) {
                    key = (T) student.getLevel();
                }

                if (key != null) {
                    resultMap.put(key, resultMap.getOrDefault(key,0) + 1);
                }
            }
        }

        return resultMap;
    }

    public static void showAcademicAbility(Student[] studentLists) {
        Map<LEVEL, Integer> levelMap = Utils.mapArray(studentLists, "level");

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

    public static void showAcademicAbility(ArrayList<Student> studentLists) {
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

    public static void showAverageRate(Student[] studentLists) {
        Map<Double, Integer> averageMap = Utils.mapArray(studentLists, "gpa");
        if (!averageMap.isEmpty()) {
            System.out.println("Ty le diem trung binh cua cac sinh vien: ");
            for (Map.Entry<Double, Integer> e : averageMap.entrySet()) {
                System.out.println(String.format("%.2f: %.2f%%", e.getKey(), (double) e.getValue() / studentCount * 100));
            }
        } else {
            System.out.println("Du lieu khong hop le.");
        }
    }

    public static void showAverageRate(ArrayList<Student> studentLists) {
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

    public static void handleAcademicAbility(Student[] studentLists) {
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

        if (count == studentCount) {
            System.out.println("Khong co sinh vien nao co hoc luc '" + academicAbility.toLowerCase() + "'.");
        }
    }

    public static void handleAcademicAbility(ArrayList<Student> studentLists) {
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
}