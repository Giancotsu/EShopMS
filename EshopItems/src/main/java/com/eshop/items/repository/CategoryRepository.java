package com.eshop.items.repository;

import com.eshop.items.entities.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
    Optional<ItemCategoryEntity> findByCategoryName(String name);
}
