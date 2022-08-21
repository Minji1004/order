package com.practice.store.config.model.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommonListResponse<T> {
    @ApiModelProperty(value = "현재 페이지")
    private Integer currentPage;
    @ApiModelProperty(value = "총 데이터 개수")
    private Long totalElements;
    @ApiModelProperty(value = "총 페이지 개수")
    private Integer totalPages;
    @ApiModelProperty(value = "조회된 데이터 리스트")
    private List<T> data = new ArrayList<>();

    public CommonListResponse(PageImpl<T> pageImpl) {
        this.currentPage = pageImpl.getPageable().getPageNumber();
        this.totalElements = pageImpl.getTotalElements();
        this.totalPages = pageImpl.getTotalPages();
        this.data = pageImpl.getContent();
    }
}
