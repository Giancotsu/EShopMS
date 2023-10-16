package com.eshop.eshop.dto.converter;

import com.eshop.eshop.dto.CategoryDto;
import com.eshop.eshop.models.ItemCategoryEntity;

public class CategoryConverter {

    public static ItemCategoryEntity categoryDtoToEntity(CategoryDto categoryDto) {
        ItemCategoryEntity category = new ItemCategoryEntity();
        category.setCategoryName(categoryDto.getCategoryName());

        return category;
    }

    public static CategoryDto categoryEntityToDto(ItemCategoryEntity itemCategory) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(itemCategory.getCategoryName());

        return categoryDto;
    }
}
