package com.lp2.sisproject.model;

import com.lp2.sisproject.interfaces.Validatable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product implements Serializable, Validatable {
    private String name;
    private long id;
    private double price;
    private int quantity;
    private Manufacturer manufacturer;
}
