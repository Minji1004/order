package com.practice.store.user.model.response;

import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.user.model.Order;
import com.practice.store.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

@Getter
public class UserListResponse {
    private User user;
    private Order lastOrder;

    public UserListResponse(User user, OrderResponse orderResponse){
        this.user = user;
        this.lastOrder = new Order(orderResponse);
    }
}
