package com.eshop.eshop.dto.converter;

import com.eshop.eshop.dto.IvaDto;
import com.eshop.eshop.models.IvaEntity;

public class IvaConverter {

    public static IvaEntity ivaDtoToEntity(IvaDto ivaDto) {
        IvaEntity iva = new IvaEntity();
        iva.setIvaValue(ivaDto.getIvaValue());

        return iva;
    }

    public static IvaDto ivaEntityToDto(IvaEntity iva) {
        IvaDto ivaDto = new IvaDto();
        ivaDto.setIvaValue(iva.getIvaValue());

        return ivaDto;
    }
}
