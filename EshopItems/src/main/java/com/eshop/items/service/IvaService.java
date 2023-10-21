package com.eshop.items.service;

import com.eshop.items.dto.IvaDto;

import java.util.List;

public interface IvaService {

    List<IvaDto> getAllIva();
    IvaDto getIvaById(Long ivaId);
    IvaDto createIva(IvaDto ivaDto);
}
