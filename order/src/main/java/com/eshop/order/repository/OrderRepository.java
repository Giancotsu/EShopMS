package com.eshop.order.repository;

import com.eshop.order.entity.OrderEntity;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
