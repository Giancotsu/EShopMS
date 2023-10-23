package com.eshop.items.repository;

import com.eshop.items.entities.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
}
