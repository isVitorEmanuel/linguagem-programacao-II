package com.imdcorp.model;

import com.imdcorp.enums.Gender;
import com.imdcorp.enums.Level;
import com.imdcorp.enums.Training;
import com.imdcorp.interfaces.Functionary;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Teacher extends People implements Functionary, Serializable {
    private Level teacherLevel;
    private Training teacherTraining;
    private List<String> subjects;

    /**
     * Constructor.
     */
    public Teacher(Level level, Training teacherTraining, List<String> subjects,
                   String name, String CPF, LocalDate dateBirth,
                   Gender gender, Address address, Long enrollment,
                   Double salary, String department, Integer workload, LocalDate entryDate) {
        super(name, CPF, dateBirth, gender, address, enrollment, salary, department, workload, entryDate);
        this.teacherLevel = level;
        this.teacherTraining = teacherTraining;
        this.subjects = subjects;
        this.setSalary(this.calculateSalary());
    }

    /**
     * Getters.
     */
    public Level getLevel() { return this.teacherLevel; }
    public Training getTeacherTraining() { return this.teacherTraining; }
    public List<String> getSubjects() { return this.subjects; }

    /**
     * Setters.
     */
    public void setLevel(Level level) { this.teacherLevel = level; }
    public void setTeacherTraining(Training teacherTraining) { this.teacherTraining = teacherTraining; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    @Override
    public Double calculateSalary() {
        Double baseSalary = this.getSalary();
        Double levelSalary = baseSalary * Math.pow(1.05, getLevel().ordinal());

        switch (getTeacherTraining()) {
            case SPECIALIZATION -> levelSalary = levelSalary + (baseSalary * 0.25);
            case MASTER -> levelSalary = levelSalary + (baseSalary * 0.5);
            case DOCTORATE -> levelSalary = levelSalary + (baseSalary * 0.75);
        }

        /* Another way to make it.
        for (int i = 0; i <= this.getLevel().ordinal(); ++i) { levelSalary = levelSalary + (levelSalary * 0.05); }
        switch (getTeacherTraining()) {
            case SPECIALIZATION -> additionalFormation = baseSalary * 0.25;
            case MASTER -> additionalFormation = baseSalary * 0.5;
            case DOCTORATE -> additionalFormation = baseSalary * 0.75;
        }
        */

        return levelSalary;
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat("#.##");

        return  "---- Detalhes do Professor ----\n" +
                "---------------------------\n" +
                "Nome: " + getName() + "\n" +
                "CPF: " + getCPF() + "\n" +
                "Matrícula: " + getEnrollment() + "\n" +
                "Data de Aniversário: " + getDateBirth() + "\n" +
                "Gênero: " + (Objects.equals(getGender().toString(), "MALE") ? "Masculino" : "Feminino") + "\n" +
                "Salario: R$" + dFormat.format(getSalary()) + "\n" +
                "Departmento: " + getDepartment() + "\n" +
                "Carga horária: " + getWorkload() + "h\n" +
                "Data de Ingresso: " + getEntryDate() + "\n" +
                "---------------------------\n" +
                "Nível: " + getLevel() + "\n" +
                "Formação: " + getTeacherTraining() + "\n" +
                "Endereço: \n" + getAddress() +
                "Disciplinas: " + String.join(", ", getSubjects()) + "\n" +
                "---------------------------";
    }

}
