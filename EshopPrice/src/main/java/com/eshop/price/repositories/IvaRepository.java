package com.eshop.price.repositories;

import com.eshop.price.entities.IvaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IvaRepository extends JpaRepository<IvaEntity, Long> {

    @Query(value = "SELECT * FROM IVA WHERE IVA.VALUE = :value", nativeQuery = true)
    Optional<IvaEntity> selIvaByValue(int value);
}
