package com.eshop.eshop.repository;

import com.eshop.eshop.models.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Long> {
}
