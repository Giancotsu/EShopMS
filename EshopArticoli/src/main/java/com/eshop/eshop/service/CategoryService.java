package com.eshop.eshop.service;

import com.eshop.eshop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryByName(String name);
    CategoryDto createCategory(CategoryDto categoryDto);

}
