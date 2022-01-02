package com.integratedfinance.exchange.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {

    private List<T> items = new ArrayList<>();
    private Integer page;
    private Integer size;
    private Long totalSize;

    public DataResponse() {
    }

    public DataResponse(List<T> items) {
        this.items = items;
    }

    public DataResponse(List<T> items, Integer page, Integer size, Long totalSize) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalSize = totalSize;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return Objects.nonNull(size) ? size : items.size();
    }

    public Long getTotalSize() {
        return totalSize;
    }

}
