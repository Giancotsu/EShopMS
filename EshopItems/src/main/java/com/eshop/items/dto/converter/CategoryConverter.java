package com.eshop.items.dto.converter;

import com.eshop.items.dto.CategoryDto;
import com.eshop.items.entities.ItemCategoryEntity;

public class CategoryConverter {

    public static ItemCategoryEntity categoryDtoToEntity(CategoryDto categoryDto) {
        ItemCategoryEntity category = new ItemCategoryEntity();
        category.setCategoryId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());

        return category;
    }

    public static CategoryDto categoryEntityToDto(ItemCategoryEntity itemCategory) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(itemCategory.getCategoryId());
        categoryDto.setCategoryName(itemCategory.getCategoryName());

        return categoryDto;
    }
}
