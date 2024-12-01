package main.java.com.imdcorp.interfaces.model;

import main.java.com.imdcorp.interfaces.enums.Gender;

import java.time.LocalDate;

public abstract class People {
    private String name;
    private String CPF;
    private LocalDate dateBirth;
    private Gender gender;
    private Address address;
    private Long enrollment;
    private Double salary;
    private String department;

    /**
     * Getters.
     */
    public String getName() { return this.name; }
    public String getCPF() { return this.CPF; }
    public LocalDate getDateBirth() { return this.dateBirth; }
    public Gender getGender() { return this.gender; }
    public Address getAddress() { return this.address; }
    public Long getEnrollment() { return this.enrollment; }
    public Double getSalary() { return this.salary; }
    public String getDepartment() { return this.department; }

    /**
     * Setters.
     */
    public void setName(String name) { this.name = name; }
    public void setCPF(String CPF) { this.CPF = CPF; }
    public void setDateBirth(LocalDate dateBirth) { this.dateBirth = dateBirth; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setAddress(Address address) { this.address = address; }
    public void setEnrollment(Long enrollment) { this.enrollment = enrollment; }
    public void setSalary(Double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }
}
