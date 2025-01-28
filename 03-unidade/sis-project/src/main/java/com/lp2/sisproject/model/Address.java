package com.lp2.sisproject.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    private String country;
    private String CEP;

    @Override
    public String toString() {
        return getCountry() + " - " + getState() + " - " + getCEP();
    }
}
