package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.CategoryDto;
import com.eshop.eshop.dto.converter.CategoryConverter;
import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.repository.CategoryRepository;
import com.eshop.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<ItemCategoryEntity> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryConverter::categoryEntityToDto).toList();

    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        ItemCategoryEntity category = categoryRepository.save(CategoryConverter.categoryDtoToEntity(categoryDto));
        return CategoryConverter.categoryEntityToDto(category);
    }
}