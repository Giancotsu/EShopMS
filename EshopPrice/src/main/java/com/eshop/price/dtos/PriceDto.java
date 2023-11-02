package com.eshop.price.dtos;

import com.eshop.price.entities.IvaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class PriceDto {

    private long id;
    private long itemId;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal price;
    private Set<SaleDto> sales;
    private Set<Long> itemCategoriesId;
    private IvaEntity iva;
}
