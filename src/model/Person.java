package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private static long idCounter = 1;
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String address;
    private Double height;
    private Double weight;

    public Person(String name, LocalDate birthDate, String address, Double height, Double weight) {
        this.id = idCounter++;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public Person(Person other) {
        this.id = other.id;
        this.name = other.name;
        this.birthDate = other.birthDate;
        this.address = other.address;
        this.height = other.height;
        this.weight = other.weight;
    }

    public static void setIdCounter(Long idCounter) {
        Person.idCounter = idCounter;
    }

    public static Long getIdCounter() {
        return idCounter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Thong tin: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", address='" + address + '\'' +
                ", height=" + height +
                ", weight=" + weight;
    }
}
