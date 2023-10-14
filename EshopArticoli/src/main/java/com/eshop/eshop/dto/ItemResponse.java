package com.eshop.eshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemResponse {

    private List<ItemDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
