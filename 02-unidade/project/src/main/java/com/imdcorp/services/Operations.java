package main.java.com.imdcorp.services;

import main.java.com.imdcorp.dao.DataDAO;
import main.java.com.imdcorp.model.Teacher;
import main.java.com.imdcorp.util.FileUtils;
import main.java.com.imdcorp.util.Create;

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
        System.out.println(" +++ Cadastro de Professor +++ ");
        Create create = new Create();
        Teacher newTeacher = create.createTeacher();
        dataDAO.getFunctionaries().add(newTeacher);
    }

    /**
     *
     */
    public void registerADMTechnician() {
        // TODO: Implements the register of technician.
    }

    /**
     * Iterate in functionaries from dataDAO and list all teachers.
     */
    public void listTeachers() {
        System.out.println(">>> Listar Professores: ");
        dataDAO.getFunctionaries().forEach(people -> {
            if (people instanceof Teacher teacher) {
                System.out.println("- " + teacher.getName() + " (Mat: "+ teacher.getEnrollment() +")");
            }
        });
    }
}
