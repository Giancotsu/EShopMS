package com.eshop.price.service.impl;

import com.eshop.price.dtos.SaleDto;
import com.eshop.price.dtos.mapper.SaleMapper;
import com.eshop.price.repositories.SaleRepository;
import com.eshop.price.service.SaleService;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public SaleDto createSale(SaleDto saleDto) {
        return SaleMapper.entityToDto(saleRepository.save(SaleMapper.dtoToEntity(saleDto)));
    }
}
