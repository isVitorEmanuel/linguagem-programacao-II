package com.imdcorp.model;

import com.imdcorp.enums.Gender;
import com.imdcorp.enums.Level;
import com.imdcorp.enums.Training;
import com.imdcorp.interfaces.Functionary;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class ADMTechnician extends People implements Functionary, Serializable {
    private Level technicianLevel;
    private Training technicianTraining;
    private Boolean insalubrious;
    private Boolean rewardedFunction;

    /**
     * Constructor.
     */
    public ADMTechnician(Level technicianLevel, Training technicianTraining, Boolean insalubrious, Boolean rewardedFunction,
                         String name, String CPF, LocalDate dateBirth,
                         Gender gender, Address address, Long enrollment,
                         Double salary, String department, Integer workload, LocalDate entryDate) {
        super(name, CPF, dateBirth, gender, address, enrollment, salary, department, workload, entryDate);
        this.technicianLevel = technicianLevel;
        this.technicianTraining = technicianTraining;
        this.insalubrious = insalubrious;
        this.rewardedFunction = rewardedFunction;
        this.setSalary(this.calculateSalary());
    }

    /**
     * Getters.
     */
    public Level getTechnicianLevel() { return this.technicianLevel; }
    public Training getTechnicianTraining() { return technicianTraining; }
    public Boolean getInsalubrious() { return insalubrious; }
    public Boolean getRewardedFunction() { return rewardedFunction; }

    /**
     * Setters.
     */
    public void setTechnicianLevel(Level technicianLevel) { this.technicianLevel = technicianLevel; }
    public void setTechnicianTraining(Training technicianTraining) { this.technicianTraining = technicianTraining; }
    public void setInsalubrious(Boolean insalubrious) { this.insalubrious = insalubrious; }
    public void setRewardedFunction(Boolean rewardedFunction) { this.rewardedFunction = rewardedFunction; }

    @Override
    public Double calculateSalary() {
        Double baseSalary = this.getSalary();
        Double levelSalary =  baseSalary * Math.pow(1.03, getTechnicianLevel().ordinal());

        switch (getTechnicianTraining()) {
            case SPECIALIZATION -> levelSalary = levelSalary + (baseSalary * 0.25);
            case MASTER -> levelSalary = levelSalary + (baseSalary * 0.5);
            case DOCTORATE -> levelSalary = levelSalary + (baseSalary * 0.75);
        }

        if (getInsalubrious()) { levelSalary = levelSalary + (baseSalary * 0.5); }
        if (getRewardedFunction()) { levelSalary = levelSalary + (baseSalary * 0.5); }

        return levelSalary;
    }

    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat("#.##");

        return  "---- Detalhes do Técnico ----\n" +
                "-----------------------------\n" +
                "Nome: " + getName() + "\n" +
                "CPF: " + getCPF() + "\n" +
                "Matrícula: " + getEnrollment() + "\n" +
                "Data de Aniversário: " + getDateBirth() + "\n" +
                "Gênero: " + getGender() + "\n" +
                "Salario: R$" + dFormat.format(getSalary()) + "\n" +
                "Departmento: " + getDepartment() + "\n" +
                "Carga horária: " + getWorkload() + "h\n" +
                "Data de Ingresso: " + getEntryDate() + "\n" +
                "---------------------------\n" +
                "Nível: " + getTechnicianLevel() + "\n" +
                "Endereço: \n" + getAddress() +
                "Insalubridade: " + (getInsalubrious() ? "Sim" : "Não") + "\n" +
                "Bônus: " + (getRewardedFunction() ? "Sim" : "Não") + "\n" +
                "-----------------------------";
    }
}
