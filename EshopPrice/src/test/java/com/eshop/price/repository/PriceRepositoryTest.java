package com.eshop.price.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;

@TestPropertySource(locations="classpath:application-price-list.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceRepositoryTest {

    private final PriceRepository priceRepository;

    public PriceRepositoryTest(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    String priceListId = "100";
    String itemId = "1";
    Double price = 1.00;

    @Test
    @Order(1)
    public void testInsListino()
    {
        PriceList priceListTest = new PriceList(priceListId,"Price list test 100","No");

        Set<PriceListDetails> listDetails = new HashSet<>();
        PriceListDetails listDetailsTest = new PriceListDetails(itemId, price, priceListTest);
        listDetails.add(listDetailsTest);

        priceListTest.setPriceListDetails(listDetails);

        priceRepository.save(priceListTest);

        assertThat(priceRepository
                .findById(PriceListId))
                .isNotEmpty();

    }
}
