package com.lp2.sisproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletronicProduct extends Product {
    private String technicalInfo;

    public EletronicProduct(String name, long id, double price, int quantity,Manufacturer manufacturer, String technicalInfo) {
        super(name, id, price, quantity, manufacturer);
        this.technicalInfo = technicalInfo;
    }

    @Override
    public boolean isValid() {
        if (getName() == null || getName().isEmpty()) { return false; }
        if (getPrice() <= 0) { return false; }
        if (getQuantity() < 0) { return false; }

        return technicalInfo != null && !technicalInfo.isEmpty() && !technicalInfo.isBlank();
    }
}
