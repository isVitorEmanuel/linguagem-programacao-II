package main.java.com.imdcorp.dao;

import main.java.com.imdcorp.model.People;

import java.util.ArrayList;

public class DataDAO {
    ArrayList<People> functionaries;
    private static DataDAO instance;

    /**
     * Constructor.
     */
    private DataDAO() { functionaries = new ArrayList<>(); }

    /**
     * Retrieves the singleton instance of the DataDAO class.
     *
     * @return A DAO database instance.
     */
    public static DataDAO getInstance() {
        if (instance == null) { instance = new DataDAO(); }
        return instance;
    }

    /**
     * This method provides access to the collection of People objects representing the functionaries.
     *
     * @return An ArrayList containing People objects, which represent the functionaries.
     */
    public ArrayList<People> getFunctionaries() { return this.functionaries; }

    /**
     * Updates the list of functionaries in the DAO.
     *
     * @param functionaries the new list of functionaries to be set
     */
    public void setFunctionaries(ArrayList<People> functionaries) {
        this.functionaries = functionaries;
    }

}
