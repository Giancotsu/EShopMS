package com.eshop.items.openfeign;


import com.eshop.items.config.OpenFeignConfig;
import com.eshop.items.dto.SetPriceCategoriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(name = "EshopPrice", configuration = {OpenFeignConfig.class})
public interface PriceClient {

    @GetMapping(value = "/price-service/is-on")
    boolean isOn();

    @GetMapping(value = "/api/price/item/{itemId}")
    BigDecimal getItemPrice(@PathVariable("itemId") long itemId);

    @PostMapping(value = "/api/price/set/{itemId}")
    void setItemPrice(@PathVariable("itemId") long itemId, @RequestBody BigDecimal price);

    @PostMapping(value = "/api/price/set/enhanced")
    void setItemPriceAndCategories(@RequestBody SetPriceCategoriesDto requestBody);
}
