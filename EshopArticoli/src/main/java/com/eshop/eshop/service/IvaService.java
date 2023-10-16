package com.eshop.eshop.service;

import com.eshop.eshop.dto.IvaDto;

import java.util.List;

public interface IvaService {

    List<IvaDto> getAllIva();
    IvaDto getIvaById(Long ivaId);
    IvaDto createIva(IvaDto ivaDto);
}
