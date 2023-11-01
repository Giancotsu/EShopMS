package com.eshop.price.repositories;

import com.eshop.price.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findPriceEntityByItemId(long itemId);
}
