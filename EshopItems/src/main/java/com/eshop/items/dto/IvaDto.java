package com.eshop.items.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class IvaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 5300033926473664629L;

    private int ivaValue;
}
