package com.practice.store.user.model.response;

import com.practice.store.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserListResponse {
    private User user;
}
