package com.eshop.price.repositories;

import com.eshop.price.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findPriceEntityByItemId(long itemId);

    @Query(value = "SELECT * FROM PRICE WHERE :categoryId = ANY(PRICE.ITEM_CATEGORIES_ID)", nativeQuery = true)
    Optional<List<PriceEntity>> findPriceByCategory(long categoryId);
}
