package com.eshop.items.service;

import com.eshop.items.dto.CategoryDto;
import com.eshop.items.dto.ItemDto;
import com.eshop.items.dto.converter.CategoryConverter;
import com.eshop.items.dto.converter.ItemConverter;
import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.entities.ItemEntity;
import com.eshop.items.repository.CategoryRepository;
import com.eshop.items.repository.ItemRepository;
import com.eshop.items.service.impl.CategoryServiceImpl;
import com.eshop.items.service.impl.ItemServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void categoryService_createCategory_returnCategoryDto() {

        ItemCategoryEntity category = new ItemCategoryEntity();
        category.setCategoryName("TEST");

        when(categoryRepository.save(Mockito.any(ItemCategoryEntity.class))).thenReturn(category);
        CategoryDto savedCategory = categoryService.createCategory(CategoryConverter.categoryEntityToDto(category));

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getCategoryName()).isEqualTo("TEST");
    }
}
