package com.eshop.price.repository;

import com.eshop.price.entities.PriceListDetailsEntity;
import com.eshop.price.entities.PriceListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceListRepository extends JpaRepository<PriceListEntity, Long> {

    @Modifying
    @Query(value = "DELETE FROM PRICE-LIST-DETAILS WHERE item_id = :itemId AND priceListId = :priceListId", nativeQuery = true)
    void deletePriceByItemAndPriceList(@Param("itemId") long itemId, @Param("priceListId") long priceListId);

    //Query JPQL
    @Query(value = "SELECT b FROM PriceListEntity a JOIN a.priceListDetails b WHERE b.itemId = :itemId AND a.priceListId = :priceListId")
    PriceListDetailsEntity SelByItemAndPriceList(@Param("itemId") long itemId, @Param("priceListId") long priceListId);
}
