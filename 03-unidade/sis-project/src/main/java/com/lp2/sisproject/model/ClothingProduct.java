package com.lp2.sisproject.model;

import com.lp2.sisproject.enums.Size;
import com.lp2.sisproject.enums.TypeProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothingProduct extends Product {
    private final TypeProduct typeProduct = TypeProduct.CLOTHING_PRODUCT;
    private Size size;

    public ClothingProduct(String name, long id,
                           double price, int quantity,
                           Manufacturer manufacturer,
                           Size size) {
        super(name, id, price, quantity, manufacturer);
        this.size = size;
    }

    @Override
    public boolean isValid() {
        if (getName() == null || getName().isEmpty()) { return false; }
        if (getPrice() <= 0) { return false; }
        if (getQuantity() < 0) { return false; }
        return size != null;
    }

}
