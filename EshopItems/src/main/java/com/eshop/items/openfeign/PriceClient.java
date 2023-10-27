package com.eshop.items.openfeign;

import com.eshop.items.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(name = "EshopPrice", url = "localhost:9001")
public interface PriceClient {

    @GetMapping(value = "/api/price/item/{itemId}")
    public BigDecimal getItemPrice(@PathVariable("itemId") long itemId);

    @PostMapping(value = "/api/price/set/{itemId}")
    public void setItemPrice(@PathVariable("itemId") long itemId, @RequestBody BigDecimal price);
}
