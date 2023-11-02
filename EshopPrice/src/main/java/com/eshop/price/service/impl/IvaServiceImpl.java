package com.eshop.price.service.impl;

import com.eshop.price.entities.IvaEntity;
import com.eshop.price.repositories.IvaRepository;
import com.eshop.price.service.IvaService;
import org.springframework.stereotype.Service;

@Service
public class IvaServiceImpl implements IvaService {

    private final IvaRepository ivaRepository;

    public IvaServiceImpl(IvaRepository ivaRepository) {
        this.ivaRepository = ivaRepository;
        this.ivaInit();
    }

    private void ivaInit(){

        int iva1 = 4;
        int iva2 = 5;
        int iva3 = 10;
        int iva4 = 22;

        IvaEntity iva;
        if(ivaRepository.selIvaByValue(iva1).isEmpty()) {
            iva = new IvaEntity();
            iva.setValue(iva1);
            ivaRepository.save(iva);
        }
        if(ivaRepository.selIvaByValue(iva2).isEmpty()) {
            iva = new IvaEntity();
            iva.setValue(iva2);
            ivaRepository.save(iva);
        }

        if(ivaRepository.selIvaByValue(iva3).isEmpty()) {
            iva = new IvaEntity();
            iva.setValue(iva3);
            ivaRepository.save(iva);
        }

        if(ivaRepository.selIvaByValue(iva4).isEmpty()) {
            iva = new IvaEntity();
            iva.setValue(iva4);
            ivaRepository.save(iva);
        }
    }
}
