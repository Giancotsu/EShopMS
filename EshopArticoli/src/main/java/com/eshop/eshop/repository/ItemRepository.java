package com.eshop.eshop.repository;

import com.eshop.eshop.models.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
}
