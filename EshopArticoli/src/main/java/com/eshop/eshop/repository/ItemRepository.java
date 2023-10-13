package com.eshop.eshop.repository;

import com.eshop.eshop.models.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    List<Item> findByDetailsLike(String details, Pageable pageable);

    @Query(value = "SELECT * FROM ITEM WHERE DETAILS LIKE :details", nativeQuery = true)
    List<Item> selByDetailsLike(@Param("details") String details);
}
