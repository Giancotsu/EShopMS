package com.eshop.eshop.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class ItemResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -297939757496045214L;

    private List<ItemDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
