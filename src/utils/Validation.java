package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static constant.Constant.*;

public class Validation {
    public static boolean validateName(String name) {
        for (int i = 0; i<name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                return false;
            }
        }

        return name != null && !name.isEmpty() && !name.isBlank() && name.length() <= MAX_NAME_LENGTH;
    }

    public static boolean validatebirthDate(String birthDate) {
        if (birthDate == null || birthDate.isEmpty()) {
            System.out.println("Ngay sinh khong the de trong.Vui long nhap ngay sinh!");
            return false;
        }

        if (!DATE_PATTERN.matcher(birthDate).matches()){
            System.out.println("Dinh dang cua ngay sinh khong dung.");
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if (!isValidDate(birthDate)) {
                System.out.println("Ngay hoac thang khong hop le.");
                return false;
            }

            LocalDate date = LocalDate.parse(birthDate, formatter);
            if (date.getYear() < MIN_YEAR || date.getYear() > LocalDateTime.now().getYear()) {
                System.out.println("Nam sinh phai nam trong khoang (1900 - " + MAX_YEAR + "). ");
                return false;
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Dinh dang cua ngay sinh khong dung.");
            return false;
        }

        return true;
    }

    public static boolean isValidDate(String date) {
        for (int i = 0; i < date.length(); i++) {
            if (Character.isLetter(date.charAt(i))){
                return false;
            }
        }

        int day = Integer.parseInt(date.split("/")[0]);
        int month = Integer.parseInt(date.split("/")[1]);
        int year = Integer.parseInt(date.split("/")[2]);

        if (month > 12 || month < 1 || day > 31 || day < 1) {
            return false;
        }

        if ((month == 4 || month == 6 || month == 9 || month == 12) && day > 30){
            return false;
        }

        if (month == 2) {
            if ((year % 100 == 0 && year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        } else {
            return true;
        }
    }

    public static boolean validateAddress(String address) {
        return address != null && !address.isEmpty() && !address.isBlank() && address.length() <= MAX_ADDRESS_LENGTH;
    }

    public static boolean validateHeight(Double height) {
        return height!=null && height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    public static boolean validateWeight(Double weight) {
        return weight!=null && weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }

    public static boolean validateStudentId(String studentId) {
        return studentId != null && !studentId.isBlank() && !studentId.isEmpty() && studentId.length() == STUDENT_ID_LENGTH;
    }

    public static boolean validateSchool(String school) {
        return school != null && !school.isEmpty() && !school.isBlank() && school.length() <= MAX_SCHOOL_LENGTH;
    }

    public static boolean validateStartYear(Integer startYear) {
        return startYear!=null && String.valueOf(startYear).length() == 4 && startYear >= MIN_YEAR && startYear < MAX_YEAR;
    }

    public static boolean validateGpa(Double gpa) {
        return gpa != null && gpa >= MIN_GPA && gpa <= MAX_GPA;
    }
}
