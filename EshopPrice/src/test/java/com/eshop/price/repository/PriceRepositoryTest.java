package com.eshop.price.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshop.price.entities.PriceListDetailsEntity;
import com.eshop.price.entities.PriceListEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@TestPropertySource(locations="classpath:application-price-list.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceRepositoryTest {

    private final PriceListRepository priceListRepository;

    long priceListId = 100;
    long itemId = 1;
    BigDecimal price = BigDecimal.valueOf(1.00);

    public PriceRepositoryTest(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @Test
    @Order(1)
    public void testInsListino()
    {
        PriceListEntity priceListTest = new PriceListEntity(priceListId, "Price list test 100",false);

        Set<PriceListDetailsEntity> listDetails = new HashSet<>();
        PriceListDetailsEntity listDetailsTest = new PriceListDetailsEntity(itemId, price);
        listDetailsTest.setPriceList(priceListTest);
        listDetails.add(listDetailsTest);

        priceListTest.setPriceListDetails(listDetails);

        PriceListEntity priceList = priceListRepository.save(priceListTest);

        assertThat(priceListRepository.findById(priceList.getPriceListId())).isNotEmpty();

    }
}
