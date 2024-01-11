package com.eshop.items.dto;

import com.eshop.items.entities.ItemCategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7809761527879446715L;

    private long id;
    @NotNull(message = "required")
    @Size(min=2, max = 50, message = "min 3, max 50")
    @Schema(name = "name", description = "Item name")
    private String name;
    @NotNull(message = "required")
    @Size(min=2, max = 200, message="min 5, max 200")
    @Schema(name = "details", description = "Item details")
    private String details;
    @NotNull(message = "required")
    @Min(value = 0, message = "min zero")
    @Schema(name = "price", description = "Item price")
    private BigDecimal price;
    private Set<ItemCategoryEntity> categories;

    public ItemDto(@NotNull(message = "required") String name, @NotNull(message = "required") String details, @NotNull(message = "required") BigDecimal price, Set<ItemCategoryEntity> categories) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.categories = categories;
    }
}
