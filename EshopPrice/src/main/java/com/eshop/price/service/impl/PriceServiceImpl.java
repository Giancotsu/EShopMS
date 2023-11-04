package com.eshop.price.service.impl;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;
import com.eshop.price.dtos.mapper.PriceMapper;
import com.eshop.price.dtos.mapper.SaleMapper;
import com.eshop.price.entities.IvaEntity;
import com.eshop.price.entities.PriceEntity;
import com.eshop.price.entities.SaleEntity;
import com.eshop.price.exceptions.CategoryNotFoundException;
import com.eshop.price.exceptions.PriceNotFoundException;
import com.eshop.price.exceptions.SaleNotFoundException;
import com.eshop.price.repositories.IvaRepository;
import com.eshop.price.repositories.PriceRepository;
import com.eshop.price.repositories.SaleRepository;
import com.eshop.price.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final SaleRepository saleRepository;
    private final IvaRepository ivaRepository;

    public PriceServiceImpl(PriceRepository priceRepository, SaleRepository saleRepository, IvaRepository ivaRepository) {
        this.priceRepository = priceRepository;
        this.saleRepository = saleRepository;
        this.ivaRepository = ivaRepository;
    }

    @Override
    public PriceDto getPriceById(long priceId) {
        return PriceMapper.entityToDto(priceRepository.findById(priceId).orElseThrow(() -> new PriceNotFoundException("Price could not be found")));
    }

    @Override
    public BigDecimal getPriceByItem(long itemId) {

        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new PriceNotFoundException("Price could not be found"));

        if(priceEntity==null){
            return BigDecimal.valueOf(999999999);
        }

        return priceEntity.getPrice();
    }

    public PriceDto getPriceDtoByItem(long itemId){
        return PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new PriceNotFoundException("Price could not be found")));
    }

    @Override
    public PriceDto setPriceToItem(long itemId, BigDecimal price) {
        if(itemId<=0){throw new RuntimeException("Price could not be set");}
        PriceDto priceDto;
        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new PriceNotFoundException("Price could not be found"));
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

        PriceEntity priceEntity;
        if(priceRepository.findPriceEntityByItemId(itemId).isPresent()){
            priceEntity = priceRepository.findPriceEntityByItemId(itemId).get();
            priceEntity.setPrice(itemClientRequest.getPrice());
            priceEntity.setItemCategoriesId(itemClientRequest.getItemCategoriesId());
        }else{
            priceEntity = new PriceEntity();
            priceEntity.setItemId(itemClientRequest.getItemId());
            priceEntity.setPrice(itemClientRequest.getPrice());
            priceEntity.setItemCategoriesId(itemClientRequest.getItemCategoriesId());
            priceEntity.setSales(new HashSet<>());
        }

        PriceDto priceDto = PriceMapper.entityToDto(priceRepository.save(priceEntity));
        this.setIva(priceDto);
        return priceDto;
    }

    @Override
    public PriceDto setPriceSaleSingleByPriceId(long priceId, SaleDto saleDto) {

        saleRepository.findById(saleDto.getId()).orElseThrow(()->new SaleNotFoundException("Sale could not be found"));
        PriceDto priceDto = getPriceById(priceId);
        Set<SaleDto> sales = priceDto.getSales();
        sales.add(saleDto);
        priceDto.setSales(sales);
        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }

    @Override
    public PriceDto setPriceSaleSingleByItemId(long itemId, SaleDto saleDto) {

        saleRepository.findById(saleDto.getId()).orElseThrow(()->new SaleNotFoundException("Sale could not be found"));
        PriceDto priceDto = PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new PriceNotFoundException("Price could not be found")));
        Set<SaleDto> sales = priceDto.getSales();
        sales.add(saleDto);
        priceDto.setSales(sales);
        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }

    @Override
    public List<PriceDto> setPriceSaleByCategory(long saleId, long category){
        SaleEntity sale = saleRepository.findById(saleId).orElseThrow(()->new SaleNotFoundException("Sale could not be found"));
        List<PriceEntity> prices = priceRepository.findPriceByCategory(category).orElseThrow(()-> new PriceNotFoundException("Price could not be found"));
        if(prices.size()==0) throw new CategoryNotFoundException("Category not found");
        List<PriceDto> priceDtos = prices.stream().map(PriceMapper::entityToDto).toList();
        priceDtos.forEach(priceDto -> {
            Set<SaleDto> sales = priceDto.getSales();
            sales.add(SaleMapper.entityToDto(sale));
            priceDto.setSales(sales);
            priceRepository.save(PriceMapper.dtoToEntity(priceDto));
        });
        return priceDtos;
    }

    @Override
    public String removeSaleSingleByItemId(long itemId, SaleDto saleDto) {

        PriceDto priceDto = PriceMapper.entityToDto(priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()->new PriceNotFoundException("Price could not be found")));
        Set<SaleDto> sales = priceDto.getSales();
        if(!sales.contains(saleDto)){
            return String.format("Sale %d not present", saleDto.getId());
        }
        sales.remove(saleDto);
        priceDto.setSales(sales);
        PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
        return String.format("Sale %d removed", saleDto.getId());
    }

    @Override
    public String removeSale(long saleId) {
        SaleEntity sale = saleRepository.findById(saleId).orElseThrow(()-> new SaleNotFoundException("Sale could not be found"));
        List<PriceEntity> allPrice = priceRepository.findAll();
        allPrice.forEach(price -> {
            price.getSales().remove(sale);
            priceRepository.save(price);
        });
        saleRepository.deleteById(saleId);
        return "sale removed";
    }

    @Override
    public String removeSaleFromSinglePrice(long itemId, long saleId) {
        SaleEntity sale = saleRepository.findById(saleId).orElseThrow(()-> new SaleNotFoundException("Sale could not be found"));
        PriceEntity price = priceRepository.findPriceEntityByItemId(itemId).orElseThrow(()-> new PriceNotFoundException("Price could not be found"));

        price.getSales().remove(sale);
        priceRepository.save(price);

        return "sale removed";
    }

    private int setIva(PriceDto price){

        IvaEntity iva;

        Set<Long> categories = price.getItemCategoriesId();
        long category=0;

        if(categories.size()==1){
            category = categories.stream().findFirst().get();
        }

        switch ((int) category) {
            case 1 -> {
                iva = ivaRepository.selIvaByValue(4).orElseThrow(()-> new RuntimeException("Iva not found"));
                Set<PriceEntity> prices = iva.getPrices();
                prices.add(PriceMapper.dtoToEntity(price));
                price.setIva(iva);
            }
            case 2 -> {
                iva = ivaRepository.selIvaByValue(5).orElseThrow(()-> new RuntimeException("Iva not found"));
                price.setIva(iva);
            }
            case 3 -> {
                iva = ivaRepository.selIvaByValue(10).orElseThrow(()-> new RuntimeException("Iva not found"));
                price.setIva(iva);
            }
            default -> {
                iva = ivaRepository.selIvaByValue(22).orElseThrow(()-> new RuntimeException("Iva not found"));
                price.setIva(iva);
            }
        };

        PriceMapper.dtoToEntity(price);
        priceRepository.save(PriceMapper.dtoToEntity(price));
        ivaRepository.save(iva);
        return iva.getValue();
    }


}





























