package com.eshop.items.service.impl;

import com.eshop.items.dto.CategoryDto;
import com.eshop.items.dto.converter.CategoryConverter;
import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.exceptions.CategoryNotFoundException;
import com.eshop.items.repository.CategoryRepository;
import com.eshop.items.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryInit();
    }

    private void categoryInit(){
        String category1 = "GROCERY";
        String category2 = "MEDICINAL";
        String category3 = "BUILDING";
        String category4 = "BOOKS";
        String category5 = "ARTS";
        String category6 = "BABY";
        String category7 = "BEAUTY";
        String category8 = "COMPUTER";
        String category9 = "SMARTPHONE";
        String category10 = "MUSIC";
        String category11 = "MOVIES";
        String category12 = "PET SUPPLIES";
        String category13 = "GAMES";

        ItemCategoryEntity category;
        if(categoryRepository.findByCategoryName(category1).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category1);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category2).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category2);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category3).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category3);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category4).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category4);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category5).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category5);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category6).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category6);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category7).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category7);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category8).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category8);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category9).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category9);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category10).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category10);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category11).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category11);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category12).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category12);
            categoryRepository.save(category);
        }
        if(categoryRepository.findByCategoryName(category13).isEmpty()) {
            category = new ItemCategoryEntity();
            category.setCategoryName(category13);
            categoryRepository.save(category);
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<ItemCategoryEntity> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryConverter::categoryEntityToDto).toList();

    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return CategoryConverter.categoryEntityToDto(categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category could not be found")));
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return CategoryConverter.categoryEntityToDto(categoryRepository.findByCategoryName(name).orElseThrow(()-> new CategoryNotFoundException("Category could not be found")));
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        ItemCategoryEntity category = categoryRepository.save(CategoryConverter.categoryDtoToEntity(categoryDto));
        return CategoryConverter.categoryEntityToDto(category);
    }
}
