package com.practice.store.user.model.response;

import lombok.Getter;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserListResponse {
    private Integer currentPage;
    private Long totalElements;
    private Integer totalPages;
    private List<UserResponse> data = new ArrayList<>();

    public UserListResponse(PageImpl<UserResponse> pageImpl) {
        this.currentPage = pageImpl.getPageable().getPageNumber();
        this.totalElements = pageImpl.getTotalElements();
        this.totalPages = pageImpl.getTotalPages();
        this.data = pageImpl.getContent();
    }

}
