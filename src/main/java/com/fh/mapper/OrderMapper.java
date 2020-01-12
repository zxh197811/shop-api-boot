package com.fh.mapper;

import com.fh.model.Order;
import com.fh.model.OrderItem;

import java.util.List;

public interface OrderMapper {
    void addOrder(Order order);

    void addOrderItemList(List<OrderItem> orderItemList);

    void addOrderId(String orderId);

    void updateOrder(Order order);
}
