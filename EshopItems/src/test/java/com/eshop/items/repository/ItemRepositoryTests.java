package com.eshop.items.repository;

import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.entities.ItemEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void itemRepository_saveItem_returnSavedItem(){

        //Arrange
        ItemEntity item = new ItemEntity();
        item.setName("Lenovo computer");
        item.setDetails("Computer 250GB HD 8GB RAM 20'");
        /*
        Set<ItemCategoryEntity> categories = new HashSet<>();
        ItemCategoryEntity category = new ItemCategoryEntity();
        category.setCategoryId(8);
        category.setCategoryName("COMPUTER");
        categories.add(category);
        item.setCategories(categories);
         */

        //act
        ItemEntity savedItem = itemRepository.save(item);

        //assert
        Assertions.assertThat(savedItem).isNotNull();
        Assertions.assertThat(savedItem.getItemId()).isGreaterThan(0);
        Assertions.assertThat(savedItem.getName()).isEqualTo("Lenovo computer");
    }

    @Test
    public void itemRepository_getAll_returnMoreThenOneItem() {

        List<ItemEntity> items = itemRepository.findAll();

        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.size()).isGreaterThan(1);
    }

    @Test
    public void itemRepository_getItemsByCategory_returnMoreThenOneItem() {

        List<ItemEntity> items = itemRepository.selItemsByCategory("ARTS");

        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.size()).isGreaterThan(1);
    }
}
