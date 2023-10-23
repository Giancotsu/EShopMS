package com.eshop.items.service.impl;

import com.eshop.items.dto.IvaDto;
import com.eshop.items.dto.converter.IvaConverter;
import com.eshop.items.entities.IvaEntity;
import com.eshop.items.repository.IvaRepository;
import com.eshop.items.service.IvaService;
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

        IvaEntity iva = ivaRepository.save(IvaConverter.ivaDtoToEntity(ivaDto));
        return IvaConverter.ivaEntityToDto(iva);
    }
}
