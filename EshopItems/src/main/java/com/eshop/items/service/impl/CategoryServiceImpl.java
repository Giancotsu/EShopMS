package com.eshop.items.service.impl;

import com.eshop.items.dto.CategoryDto;
import com.eshop.items.dto.converter.CategoryConverter;
import com.eshop.items.models.ItemCategoryEntity;
import com.eshop.items.repository.CategoryRepository;
import com.eshop.items.service.CategoryService;
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
    public CategoryDto getCategoryById(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category could not be found"));
        return CategoryConverter.categoryEntityToDto(categoryRepository.findById(id).get());
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
