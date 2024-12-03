package main.java.com.imdcorp.model;

import main.java.com.imdcorp.enums.Gender;
import main.java.com.imdcorp.enums.Level;
import main.java.com.imdcorp.enums.Training;
import main.java.com.imdcorp.interfaces.Functionary;

import java.io.Serializable;
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
                         Double salary, String department) {
        super(name, CPF, dateBirth, gender, address, enrollment, salary, department);
        this.technicianLevel = technicianLevel;
        this.technicianTraining = technicianTraining;
        this.insalubrious = insalubrious;
        this.rewardedFunction = rewardedFunction;
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
        return 0.0;
    }
}
