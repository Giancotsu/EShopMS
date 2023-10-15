package com.eshop.eshop.repository;

import com.eshop.eshop.models.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
}
