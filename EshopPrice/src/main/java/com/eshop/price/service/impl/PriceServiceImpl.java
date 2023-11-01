package com.eshop.price.service.impl;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;
import com.eshop.price.dtos.mapper.PriceMapper;
import com.eshop.price.entities.PriceEntity;
import com.eshop.price.openfeign.ItemClient;
import com.eshop.price.repositories.PriceRepository;
import com.eshop.price.repositories.SaleRepository;
import com.eshop.price.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final SaleRepository saleRepository;
    private final ItemClient itemClient;

    public PriceServiceImpl(PriceRepository priceRepository, SaleRepository saleRepository, ItemClient itemClient) {
        this.priceRepository = priceRepository;
        this.saleRepository = saleRepository;
        this.itemClient = itemClient;
    }

    @Override
    public PriceDto getPriceById(long priceId) {
        return PriceMapper.entityToDto(priceRepository.findById(priceId).orElseThrow(() -> new RuntimeException("Price could not be found")));
    }

    @Override
    public BigDecimal getPriceByItem(long itemId) {

        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found"));

        if(priceEntity==null){
            return BigDecimal.valueOf(999999999);
        }

        return priceEntity.getPrice();
    }

    public PriceDto getPriceDtoByItem(long itemId){
        return PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found")));
    }

    @Override
    public PriceDto setPriceToItem(long itemId, BigDecimal price) {
        if(itemId<=0){throw new RuntimeException("Price could not be set");}
        PriceDto priceDto;
        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found"));
        if(priceEntity!=null){
            priceDto = PriceMapper.entityToDto(priceEntity);
            priceDto.setPrice(price);
        }else{
            priceDto = new PriceDto();
            priceDto.setItemId(itemId);
            priceDto.setPrice(price);
            priceDto.setSales(new HashSet<>());
        }

        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }

    @Override
    public PriceDto setItemPriceAndCategories(ItemClientRequestPriceCategory itemClientRequest) {

        long itemId=itemClientRequest.getItemId();
        if(itemId<=0)throw new RuntimeException("Price could not be set");

        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found"));
        if(priceEntity!=null){
            priceEntity.setPrice(itemClientRequest.getPrice());
            priceEntity.setItemCategoriesId(itemClientRequest.getItemCategoriesId());
        }else{
            priceEntity = new PriceEntity();
            priceEntity.setItemId(itemClientRequest.getItemId());
            priceEntity.setPrice(itemClientRequest.getPrice());
            priceEntity.setItemCategoriesId(itemClientRequest.getItemCategoriesId());
            priceEntity.setSales(new HashSet<>());
        }

        return PriceMapper.entityToDto(priceRepository.save(priceEntity));
    }

    @Override
    public PriceDto setPriceSaleSingleByPriceId(long priceId, SaleDto saleDto) {

        saleRepository.findById(saleDto.getId()).orElseThrow(()->new RuntimeException("Sale not found"));
        PriceDto priceDto = getPriceById(priceId);
        Set<SaleDto> sales = priceDto.getSales();
        sales.add(saleDto);
        priceDto.setSales(sales);
        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }

    @Override
    public PriceDto setPriceSaleSingleByItemId(long itemId, SaleDto saleDto) {

        saleRepository.findById(saleDto.getId()).orElseThrow(()->new RuntimeException("Sale not found"));
        PriceDto priceDto = PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found")));
        Set<SaleDto> sales = priceDto.getSales();
        sales.add(saleDto);
        priceDto.setSales(sales);
        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }

    @Override
    public String removeSaleSingleByItemId(long itemId, SaleDto saleDto) {

        PriceDto priceDto = PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new RuntimeException("Item not found")));
        Set<SaleDto> sales = priceDto.getSales();
        if(!sales.contains(saleDto)){
            return String.format("Sale %d not present", saleDto.getId());
        }
        sales.remove(saleDto);
        priceDto.setSales(sales);
        PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
        return String.format("Sale %d removed", saleDto.getId());
    }
}





























