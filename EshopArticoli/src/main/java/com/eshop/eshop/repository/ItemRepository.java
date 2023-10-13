package com.eshop.eshop.repository;

import com.eshop.eshop.models.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    List<Item> findByDetailsLike(String details, Pageable);
}
