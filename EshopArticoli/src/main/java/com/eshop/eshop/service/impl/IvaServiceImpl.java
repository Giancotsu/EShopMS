package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.IvaDto;
import com.eshop.eshop.dto.converter.IvaConverter;
import com.eshop.eshop.models.IvaEntity;
import com.eshop.eshop.repository.IvaRepository;
import com.eshop.eshop.service.IvaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IvaServiceImpl implements IvaService {

    private final IvaRepository ivaRepository;

    public IvaServiceImpl(IvaRepository ivaRepository) {
        this.ivaRepository = ivaRepository;
    }

    @Override
    public List<IvaDto> getAllIva() {

        List<IvaEntity> ivas = ivaRepository.findAll();
        return ivas.stream().map(IvaConverter::ivaEntityToDto).toList();
    }

    @Override
    public IvaDto getIvaById(Long ivaId) {
        if(ivaRepository.findById(ivaId).isEmpty()){
            throw new RuntimeException("Iva not found");
        }
        return IvaConverter.ivaEntityToDto(ivaRepository.findById(ivaId).get());
    }

    @Override
    public IvaDto createIva(IvaDto ivaDto) {
        return null;
    }
}
