package com.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagingResponse <T>{
    private List<T> pagedScheduleDtos ;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    public PagingResponse(List<T> pagedScheduleDtos, int page, int size, long totalElements) {
        this.pagedScheduleDtos = pagedScheduleDtos;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements /size);
        this.last = (page + 1) >= totalPages;
    }
}
