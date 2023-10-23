package com.eshop.items.repository;

import com.eshop.items.entities.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Long> {
}
