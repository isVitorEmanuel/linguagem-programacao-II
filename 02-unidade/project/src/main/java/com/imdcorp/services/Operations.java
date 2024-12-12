package com.imdcorp.services;

import com.imdcorp.dao.DataDAO;
import com.imdcorp.model.ADMTechnician;
import com.imdcorp.model.People;
import com.imdcorp.model.Teacher;
import com.imdcorp.util.FileUtils;
import com.imdcorp.util.Utils;

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
        System.out.println(">>> Professor cadastrado com sucesso!");
    }

    /**
     * Creates and save a new technician.
     */
    public void registerADMTechnician() {
        System.out.println("++++++++++++++ Cadastro de Técnico +++++++++++++++");
        Utils utils = new Utils();
        ADMTechnician newTechnician = utils.createTechnician();
        dataDAO.getFunctionaries().add(newTechnician);
        System.out.println(">>> Técnico cadastrado com sucesso!");
    }

    /**
     * Iterate in functionaries from dataDAO and list all teachers.
     */
    public void listTeachers() {
        System.out.println("++++++++++++++ Listar Professores ++++++++++++++");

        boolean isEmpty = true;

        for (People people : dataDAO.getFunctionaries()) {
            if (people instanceof Teacher teacher) {
                System.out.println("- " + teacher.getName() + " (Mat: "+ teacher.getEnrollment() +")");
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println(">>> Não foram encontrados professores.");
        }
    }

    /**
     * Iterate in functionaries from dataDAO and list all technicians.
     */
    public void listADMTechnicians() {
        System.out.println("++++++++++++++ Listar Técnicos ++++++++++++++");

        boolean isEmpty = true;

        for (People people : dataDAO.getFunctionaries()) {
            if (people instanceof ADMTechnician technician) {
                System.out.println("- " + technician.getName() + " (Mat: "+ technician.getEnrollment() +")");
                isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println(">>> Técnicos não foram encontrados!");
        }
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

    /**
     * This method searches for a teacher by registration, if it is deleted, otherwise it informs that it was not found.
     */
    public void deleteTeacher() {
        System.out.println("++++++++++++++ Deletar Professor ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof Teacher) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    dataDAO.getFunctionaries().remove(functionary);
                    System.out.println(">>> Professor excluído com sucesso!");
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
     * This method searches for a technician by registration, if it is deleted, otherwise it informs that it was not found.
     */
    public void deleteTechnician() {
        System.out.println("++++++++++++++ Deletar Técnico ++++++++++++++");

        Long enrollment = Utils.getEnrollmentFromUser();

        boolean found = false;
        for (People functionary : dataDAO.getFunctionaries()) {
            if (functionary instanceof ADMTechnician) {
                if (functionary.getEnrollment().equals(enrollment)) {
                    dataDAO.getFunctionaries().remove(functionary);
                    System.out.println(">>> Técnico excluído com sucesso!");
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
