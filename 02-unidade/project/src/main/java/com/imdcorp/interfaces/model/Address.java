package main.java.com.imdcorp.interfaces.model;

public class Address {
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String CEP;

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
}