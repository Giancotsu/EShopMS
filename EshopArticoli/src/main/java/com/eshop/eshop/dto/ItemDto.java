package com.eshop.eshop.dto;

import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.models.IvaEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDto {

    private long id;
    @NotNull(message = "required")
    @Size(min=3, max = 50, message = "min 3, max 50")
    @Schema(name = "name", description = "Item name")
    private String name;
    @NotNull(message = "required")
    @Size(min=5, max = 200, message="min 5, max 200")
    @Schema(name = "details", description = "Item details")
    private String details;
    @NotNull(message = "required")
    @Min(value = 0, message = "min zero")
    @Schema(name = "price", description = "Item price")
    private BigDecimal price;
    private List<ItemCategoryEntity> categories;
    private IvaEntity iva;
}
