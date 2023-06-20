package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.OrderCreateRequestDto;
import com.example.sparepartsdistributor.dto.OrderDto;

public interface OrderService {
    OrderDto save(OrderCreateRequestDto orderRequest);
}
