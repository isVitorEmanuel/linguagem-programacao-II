package com.imdcorp.util;

import com.imdcorp.model.People;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
    /**
     * Saves a list of functionaries to a file.
     *
     * @param pathData       the file path where the data will be saved
     * @param functionaries  the list of functionaries to save
     */
    public static void saveFunctionaries(String pathData, ArrayList<People> functionaries) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathData))) {
            out.writeObject(functionaries);
        } catch (IOException e) {
            System.out.println(">>> Error saving functionaries: " + e.getMessage());
        }
    }

    /**
     * Loads a list of functionaries from a file.
     *
     * @param pathData  the file path from where the data will be loaded
     * @return an ArrayList of functionaries; if the file is not found or an error occurs, returns an empty list
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<People> getFunctionaries(String pathData) {
        ArrayList<People> functionaries = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathData))) {
            functionaries = (ArrayList<People>) in.readObject();
            System.out.println(">>> Funcionários carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println(">>> Arquivo não encontrado! Iniciando vazio.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(">>> Erro ao carregar funcionários: " + e.getMessage());
        }
        return functionaries;
    }
}
