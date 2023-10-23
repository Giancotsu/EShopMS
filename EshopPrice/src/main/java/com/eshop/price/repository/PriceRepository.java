package com.eshop.price.repository;

import com.eshop.price.entities.PriceListDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceListDetailsEntity, Long> {
}
