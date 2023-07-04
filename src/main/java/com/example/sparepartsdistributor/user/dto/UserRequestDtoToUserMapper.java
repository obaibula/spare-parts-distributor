package com.example.sparepartsdistributor.user.dto;

import com.example.sparepartsdistributor.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserRequestDtoToUserMapper implements Function<UserRequestDTO, User> {
    @Override
    public User apply(UserRequestDTO userRequest) {
        return User.builder()
                .email(userRequest.email())
                .phone(userRequest.phone())
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .password(userRequest.password())
                .balance(userRequest.balance())
                .discount(userRequest.discount())
                .paymentDeferment(userRequest.paymentDeferment())
                .creditLimit(userRequest.creditLimit())
                .shipmentStatus(userRequest.shipmentStatus())
                .build();
    }
}
