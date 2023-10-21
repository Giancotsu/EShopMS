package com.eshop.eshop.repository;

import com.eshop.eshop.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<ItemEntity, Long>, JpaRepository<ItemEntity, Long> {

    @Query(value = "SELECT * FROM ITEM i INNER JOIN ITEMS_CATEGORIES ic ON i.ITEM_ID = ic.I_ID INNER JOIN CATEGORY c ON ic.C_ID = c.CATEGORY_ID AND c.CATEGORY_NAME LIKE :category_name", nativeQuery = true)
    List<ItemEntity> selItemsByCategory(@Param("category_name") String category);

    @Query(value = "SELECT COUNT(*) FROM ITEM")
    long itemsCount();

}
