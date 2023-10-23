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
        sale.setDescription(saleDto.getDescription());
        sale.setPrice(PriceMapper.dtoToEntity(saleDto.getPrice()));
        return sale;
    }

    public static SaleDto entityToDto(SaleEntity sale){
        SaleDto saleDto = new SaleDto();
        saleDto.setId(sale.getSaleId());
        saleDto.setAmount(sale.getAmount());
        saleDto.setDescription(sale.getDescription());
        saleDto.setPrice(PriceMapper.entityToDto(sale.getPrice()));
        return saleDto;
    }
}
