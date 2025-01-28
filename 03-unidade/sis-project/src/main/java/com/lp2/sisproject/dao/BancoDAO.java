package com.lp2.sisproject.dao;

import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Singleton class representing the data access object (DAO) for managing in-memory data storage.
 * Provides access to collections of products and manufacturers.
 */
@Getter
@Setter
public class BancoDAO {
    private ArrayList<Product> products;
    private ArrayList<Manufacturer> manufacturers;

    private static BancoDAO bancoDAO;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the lists for products and manufacturers.
     */
    private BancoDAO() {
        products = new ArrayList<>();
        manufacturers = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the BancoDAO class.
     * If the instance doesn't exist, it is created.
     *
     * @return the singleton instance of BancoDAO
     */
    public static BancoDAO getInstance() {
        if (bancoDAO == null) {
            bancoDAO = new BancoDAO();
        }
        return bancoDAO;
    }

    public ArrayList<Product> getProdutos() {
        return products;
    }
}
