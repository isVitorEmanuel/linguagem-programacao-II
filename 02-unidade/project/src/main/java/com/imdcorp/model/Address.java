package main.java.com.imdcorp.model;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String CEP;

    /**
     * Constructor.
     */
    public Address(String street, Integer number, String neighborhood, String city, String CEP) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.CEP = CEP;
    }

    /**
     * Getters.
     */
    public String getStreet () { return this.street; }
    public Integer getNumber () { return this.number; }
    public  String getNeighborhood () { return this.neighborhood; }
    public String getCity () { return this.city; }
    String getCEP () { return this.CEP; }

    /**
     * Setters.
     */
    public void setStreet ( String street ) { this.street = street; }
    public void setNumber ( Integer number ) { this.number = number; }
    public void setNeighborhood ( String neighborhood ) { this.neighborhood = neighborhood; }
    public void setCity ( String city ) { this.city = city; }
    public void setCEP ( String CEP ) { this.CEP = CEP; }

    @Override
    public String toString () {
        return "    Rua: " + getStreet() + "\n" +
               "    Número: " + getNumber() + "\n" +
               "    Bairro: " + getNeighborhood() + "\n" +
               "    Cidade: " + getCity() + "\n" +
               "    CEP: " + getCEP() + "\n";
    }
}