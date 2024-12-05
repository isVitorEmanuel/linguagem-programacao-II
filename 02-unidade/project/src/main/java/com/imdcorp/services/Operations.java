package main.java.com.imdcorp.services;

import main.java.com.imdcorp.dao.DataDAO;
import main.java.com.imdcorp.model.ADMTechnician;
import main.java.com.imdcorp.model.People;
import main.java.com.imdcorp.model.Teacher;
import main.java.com.imdcorp.util.FileUtils;
import main.java.com.imdcorp.util.Utils;

public class Operations {
    private static final DataDAO dataDAO = DataDAO.getInstance();
    private final String pathData = "data.bin";

    /**
     * Constructor for the Operations class.
     * Loads functionaries from a file and initializes the data access object (DAO).
     */
    public Operations() {
        dataDAO.setFunctionaries(FileUtils.getFunctionaries(pathData));
    }

    /**
     * Saves the current list of functionaries to a file and prepares the application for closure.
     */
    public void closeApplication() {
        FileUtils.saveFunctionaries(pathData, dataDAO.getFunctionaries());
    }

    /**
     * Creates and save a new teacher in database.
     */
    public void registerTeacher() {
        System.out.println("+++++++++++++++ Cadastro de Professor +++++++++++++++");
        Utils utils = new Utils();
        Teacher newTeacher = utils.createTeacher();
        dataDAO.getFunctionaries().add(newTeacher);
    }

    /**
     * Creates and save a new technician.
     */
    public void registerADMTechnician() {
        System.out.println("++++++++++++++ Cadastro de Técnico +++++++++++++++");
        Utils utils = new Utils();
        ADMTechnician newTechnician = utils.createTechnician();
        dataDAO.getFunctionaries().add(newTechnician);
    }

    /**
     * Iterate in functionaries from dataDAO and list all teachers.
     */
    public void listTeachers() {
        System.out.println("++++++++++++++ Listar Professores ++++++++++++++");
        dataDAO.getFunctionaries().forEach(people -> {
            if (people instanceof Teacher teacher) {
                System.out.println("- " + teacher.getName() + " (Mat: "+ teacher.getEnrollment() +")");
            }
        });
    }

    /**
     * Iterate in functionaries from dataDAO and list all technicians.
     */
    public void listADMTechnicians() {
        System.out.println("++++++++++++++ Listar Técnicos ++++++++++++++");
        dataDAO.getFunctionaries().forEach(people -> {
            if (people instanceof ADMTechnician technician) {
                System.out.println("- " + technician.getName() + " (Mat: "+ technician.getEnrollment() +")");
            }
        });
    }

    /**
     * Method to search for a teacher by enrollment number.
     */
    public void searchTeacher() {
        System.out.println("++++++++++++++ Buscar Professor ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof Teacher) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    System.out.println(functionary);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("ERRO > Professor não encontrado!");
        }
    }

    /**
     * Method to search for a ADM technician by enrollment number.
     */
    public void searchADMTechnician() {
        System.out.println("++++++++++++++ Buscar Técnico  ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof ADMTechnician) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    System.out.println(functionary);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("ERRO > Técnico não encontrado!");
        }
    }

    public void deleteTeacher() {
        System.out.println("++++++++++++++ Deletar Professor ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof Teacher) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    dataDAO.getFunctionaries().remove(functionary);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("ERRO > Professor não encontrado!");
        }
    }

    public void deleteTechnician() {
        System.out.println("++++++++++++++ Deletar Técnico ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof ADMTechnician) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    dataDAO.getFunctionaries().remove(functionary);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("ERRO > Técnico não encontrado!");
        }
    }
}
