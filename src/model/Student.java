package model;

import constant.LEVEL;
import java.io.Serializable;
import java.time.LocalDate;

public class Student extends Person implements Serializable {
    private String studentId;
    private String school;
    private int startYear;
    private double gpa;
    private LEVEL level;

    public Student(String name, LocalDate birthDate, String address, double height,
                   double weight, String studentId, String school, int startYear, double gpa) {
        super(name, birthDate, address, height, weight);
        this.studentId = studentId;
        this.school = school;
        this.startYear = startYear;
        this.gpa = gpa;
        setLevel(gpa);
    }

    public Student(Student other) {
        super(other);
        this.studentId = other.studentId;
        this.school = other.school;
        this.startYear = other.startYear;
        this.gpa = other.getGpa();
        setLevel(this.gpa);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        setLevel(this.gpa);
    }

    public LEVEL getLevel() {
        return level;
    }

    public void setLevel(double gpa) {
        if (gpa < 3) {
            this.level = LEVEL.KEM;
        } else if (gpa >= 3 && gpa < 5) {
            this.level = LEVEL.YEU;
        } else if (gpa >= 5 && gpa < 6.5) {
            this.level = LEVEL.TRUNG_BINH;
        } else if (gpa >= 6.5 && gpa < 7.5) {
            this.level = LEVEL.KHA;
        } else if (gpa >= 7.5 && gpa < 9) {
            this.level = LEVEL.GIOI;
        } else if (gpa >= 9) {
            this.level = LEVEL.XUAT_SAC;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nstudentId='" + studentId + '\'' +
                ", school='" + school + '\'' +
                ", startYear=" + startYear +
                ", gpa=" + gpa +
                ", level=" + level.getVietnamese() +
                "\n";
    }
}
