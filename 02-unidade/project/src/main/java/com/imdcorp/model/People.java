package main.java.com.imdcorp.model;

import main.java.com.imdcorp.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class People implements Serializable {
    private String name;
    private String CPF;
    private LocalDate dateBirth;
    private Gender gender;
    private Address address;
    private Long enrollment;
    private Double salary;
    private String department;
    private Integer workload;
    private LocalDate entryDate;

    /**
     * Constructor.
     */
    public People(String name, String CPF, LocalDate dateBirth,
                  Gender gender, Address address, Long enrollment,
                  Double salary, String department, Integer workload, LocalDate entryDate) {
        this.name = name;
        this.CPF = CPF;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.address = address;
        this.enrollment = enrollment;
        this.salary = salary;
        this.department = department;
        this.workload = workload;
        this.entryDate = entryDate;
    }

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
    public Integer getWorkload() { return this.workload; }
    public LocalDate getEntryDate() { return this.entryDate; }

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
    public void setWorkload(Integer workload) { this.workload = workload; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }
}
