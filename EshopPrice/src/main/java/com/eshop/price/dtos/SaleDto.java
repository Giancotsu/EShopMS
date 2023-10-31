package com.eshop.price.dtos;

import lombok.Data;

@Data
public class SaleDto {

    private long id;
    private String name;
    private String description;
    private int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleDto saleDto = (SaleDto) o;

        return getId() == saleDto.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
