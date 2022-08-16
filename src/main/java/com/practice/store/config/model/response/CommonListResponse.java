package com.practice.store.config.model.response;

import lombok.Getter;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommonListResponse<T> {
    private Integer currentPage;
    private Long totalElements;
    private Integer totalPages;
    private List<T> data = new ArrayList<>();

    public CommonListResponse(PageImpl<T> pageImpl) {
        this.currentPage = pageImpl.getPageable().getPageNumber();
        this.totalElements = pageImpl.getTotalElements();
        this.totalPages = pageImpl.getTotalPages();
        this.data = pageImpl.getContent();
    }
}
