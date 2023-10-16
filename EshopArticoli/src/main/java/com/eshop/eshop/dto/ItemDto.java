package com.eshop.eshop.dto;

import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.models.IvaEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDto {

    private long id;
    private String name;
    private String details;
    private BigDecimal price;
    private List<ItemCategoryEntity> categories;
    private IvaEntity iva;
}
