package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.ShipmentStatus;
import com.example.sparepartsdistributor.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record UserDto(Long id,
                      LocalDate createdAt,
                      String email,
                      String phone,
                      String firstName,
                      String lastName,
                      String password,
                      BigDecimal balance,
                      Integer discount,
                      Integer paymentDeferment,
                      BigDecimal creditLimit,
                      ShipmentStatus shipmentStatus,
                      List<OrderDto> orders,
                      CartDto cart) {

    public static UserDto userToDto(User user){
        return new UserDto(
                user.getId(),
                user.getCreatedAt(),
                user.getEmail(),
                user.getPhone(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getBalance(),
                user.getDiscount(),
                user.getPaymentDeferment(),
                user.getCreditLimit(),
                user.getShipmentStatus(),
                getOrderDtos(user),
                CartDto.cartToDto(user.getCart())
        );
    }

    private static List<OrderDto> getOrderDtos(User user) {
        return user.getOrders()
                .stream()
                .map(OrderDto::orderToDto)
                .collect(Collectors.toList());
    }
}
