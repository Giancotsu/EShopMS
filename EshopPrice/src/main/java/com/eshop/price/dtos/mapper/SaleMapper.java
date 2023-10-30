package com.eshop.price.dtos.mapper;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;
import com.eshop.price.entities.PriceEntity;
import com.eshop.price.entities.SaleEntity;

public class SaleMapper {

    public static SaleEntity dtoToEntity(SaleDto saleDto){
        SaleEntity sale = new SaleEntity();
        sale.setSaleId(saleDto.getId());
        sale.setAmount(saleDto.getAmount());
        sale.setName(saleDto.getName());
        sale.setDescription(saleDto.getDescription());
        return sale;
    }

    public static SaleDto entityToDto(SaleEntity sale){
        SaleDto saleDto = new SaleDto();
        saleDto.setId(sale.getSaleId());
        saleDto.setAmount(sale.getAmount());
        saleDto.setName(sale.getName());
        saleDto.setDescription(sale.getDescription());
        return saleDto;
    }
}
