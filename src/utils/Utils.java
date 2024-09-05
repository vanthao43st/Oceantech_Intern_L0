package utils;

import model.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static view.ConsoleUI.studentCount;

public class Utils {

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

    public static void handleUpdateOption(ArrayList<Student> studentLists, Student student, int option) {
        switch (option) {
            case 1: student.setName(Input.inputName());     break;
            case 2: student.setBirthDate(LocalDate.parse(Input.inputBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));   break;
            case 3: student.setAddress(Input.inputAddress());   break;
            case 4: student.setHeight(Input.inputHeight());     break;
            case 5: student.setWeight(Input.inputWeight());     break;
            case 6: student.setStudentId(Input.inputStudentId(studentLists));   break;
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

    public static int findDeletedStudentIndex(Student[] studentLists, Student foundStudent) {
        int deleteIndex = 0;
        for (int i = 0; i < studentCount; i++) {
            if (studentLists[i] != null) {
                if (studentLists[i] == foundStudent) {
                    deleteIndex = i;
                }
            }
        }
        return deleteIndex;
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
}