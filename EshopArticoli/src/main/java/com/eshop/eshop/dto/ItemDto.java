package com.eshop.eshop.dto;

import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.models.IvaEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDto {

    private long id;
    @Size(min=3, message="required")
    private String name;
    @Size(min=5, message="required")
    private String details;
    @NotNull(message = "required")
    @Min(value = 0, message = "min zero")
    private BigDecimal price;
    private List<ItemCategoryEntity> categories;
    private IvaEntity iva;
}
