package com.lp2.sisproject.handler;

import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.model.Product;

import java.io.*;
import java.util.ArrayList;

/**
 * Utility class for handling file operations related to products and manufacturers.
 * Supports reading from and writing to files using serialization.
 */
public class FileHandler {
    /**
     * Writes the lists of products and manufacturers to separate files.
     *
     * @param products      the list of products to be saved
     * @param manufacturers the list of manufacturers to be saved
     */
    public static void write(ArrayList<Product> products, ArrayList<Manufacturer> manufacturers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
            oos.writeObject(products);
            System.out.println("Products saved to file");
        } catch (IOException e) {
            System.err.println("Não foi possivel salvar os produtos.");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("manufacturers.txt"))) {
            oos.writeObject(manufacturers);
            System.out.println("Manufacturers saved to file");
        } catch (IOException e) {
            System.err.println("Não foi possível salvar os fabricantes.");
        }
    }

    /**
     * Reads the lists of products and manufacturers from their respective files
     * and adds them to the provided lists.
     *
     * @param products      the list to which products read from the file will be added
     * @param manufacturers the list to which manufacturers read from the file will be added
     */
    @SuppressWarnings("unchecked")
    public static void read(ArrayList<Product> products, ArrayList<Manufacturer> manufacturers) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.txt"))) {

                ArrayList<Product> productsFromFile = (ArrayList<Product>) ois.readObject();
                products.addAll(productsFromFile);
                System.out.println("Products read from file");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading the products file");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("manufacturers.txt"))) {

                ArrayList<Manufacturer> manufacturersFromFile = (ArrayList<Manufacturer>) ois.readObject();
                manufacturers.addAll(manufacturersFromFile);
                System.out.println("Manufacturers read from file");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading the manufacturers file");
        }
    }
}


