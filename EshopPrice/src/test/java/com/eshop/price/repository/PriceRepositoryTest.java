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

    @Test
    @Order(1)
    public void testInsListino()
    {
        Listini listinoTest = new Listini(IdList,"Listino Test 100","No");

        Set<DettListini> dettListini = new HashSet<>();
        DettListini dettListTest = new DettListini(CodArt,Prezzo, listinoTest);
        dettListini.add(dettListTest);

        listinoTest.setDettListini(dettListini);

        listinoRepository.save(listinoTest);

        assertThat(listinoRepository
                .findById(IdList))
                .isNotEmpty();

    }
}
