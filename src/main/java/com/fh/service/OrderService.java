package com.fh.service;

import com.fh.model.Order;
import com.fh.model.ServerResponse;

public interface OrderService {
    ServerResponse addOrder(Integer id);

    void updateOrder(Order order);
}
