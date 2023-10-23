package com.eshop.price.repositories;

import com.eshop.price.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
