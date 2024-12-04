package main.java.com.imdcorp.model;

import main.java.com.imdcorp.enums.Gender;
import main.java.com.imdcorp.enums.Level;
import main.java.com.imdcorp.enums.Training;
import main.java.com.imdcorp.interfaces.Functionary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
        return 0.0;
    }

    @Override
    public String toString() {
        return  "---- Detalhes do Professor ----\n" +
                "---------------------------\n" +
                "Nome: " + getName() + "\n" +
                "CPF: " + getCPF() + "\n" +
                "Matrícula: " + getEnrollment() + "\n" +
                "Data de Aniversário: " + getDateBirth() + "\n" +
                "Gênero: " + getGender() + "\n" +
                "Salario: " + getSalary() + "\n" +
                "Departmento: " + getDepartment() + "\n" +
                "Carga horária: " + getWorkload() + "\n" +
                "Data de Ingresso: " + getEntryDate() + "\n" +
                "---------------------------\n" +
                "Nível: " + getLevel() + "\n" +
                "Formação: " + getTeacherTraining() + "\n" +
                "Endereço: \n" + getAddress() +
                "Disciplinas: " + String.join(", ", getSubjects()) + "\n" +
                "---------------------------";
    }

}
