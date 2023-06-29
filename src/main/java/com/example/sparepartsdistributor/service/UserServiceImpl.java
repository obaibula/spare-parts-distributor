package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.UserDto;
import com.example.sparepartsdistributor.dto.UserRequestDTO;
import com.example.sparepartsdistributor.dto.UserRequestDtoToUserMapper;
import com.example.sparepartsdistributor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link UserService} interface that provides functionality for managing users.
 * This class uses the {@link UserRepository} to interact with the underlying data store
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CartService cartService;
    private final UserRequestDtoToUserMapper userRequestDtoToUserMapper;
    @Override
    @Transactional
    public UserDto save(UserRequestDTO userRequest) {
        var user = userRequestDtoToUserMapper.apply(userRequest);
        var savedUser = userRepository.save(user);
        var createdCart = cartService.createCart(savedUser);
        savedUser.setCart(createdCart);

        return UserDto.userToDto(savedUser);
    }
}
