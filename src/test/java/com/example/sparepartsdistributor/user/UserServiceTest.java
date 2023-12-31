package com.example.sparepartsdistributor.user;

import com.example.sparepartsdistributor.cart.CartService;
import com.example.sparepartsdistributor.user.UserService;
import com.example.sparepartsdistributor.user.UserServiceImpl;
import com.example.sparepartsdistributor.user.dto.UserDto;
import com.example.sparepartsdistributor.user.dto.UserRequestDTO;
import com.example.sparepartsdistributor.user.dto.UserRequestDtoToUserMapper;
import com.example.sparepartsdistributor.cart.Cart;
import com.example.sparepartsdistributor.user.User;
import com.example.sparepartsdistributor.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CartService cartService;

    // todo: mock or not???
    @Autowired
    private UserRequestDtoToUserMapper userRequestDtoToUserMapper;
    private UserService underTest;
    // todo: fix test considering mapper
    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, cartService, userRequestDtoToUserMapper);
    }

    @Test
    void canAddUser() {
        // given
        long userId = 5L;
        String email = "user@mail.com";
        String password = "userPass1#";

        var user = createUserRequestDto(email, password);
        var savedUser = createUserWithId(userId, email, password);
        var createdCart = getCreatedCart(userId, savedUser);

        given(userRepository.save(any(User.class)))
                .willReturn(savedUser);
        given(cartService.createCart(any(User.class)))
                .willReturn(createdCart);
        // when
        var result = underTest.save(user);
        // then
        assertThat(result.id()).isEqualTo(savedUser.getId());
        assertThat(result.email()).isEqualTo(savedUser.getEmail());
        assertThat(result).isEqualTo(UserDto.userToDto(savedUser));

        var userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        verify(cartService).createCart(userArgumentCaptor.capture());

        var capturedUser = userArgumentCaptor.getValue();

        assertThat(email).isEqualTo(capturedUser.getEmail());
        assertThat(createdCart).isEqualTo(capturedUser.getCart());
    }


    private static Cart getCreatedCart(long userId, User savedUser) {
        var createdCart = new Cart();
        createdCart.setId(userId);
        createdCart.setUser(savedUser);
        return createdCart;
    }

    private static User createUserWithId(long userId, String email, String password) {
        return User.builder()
                .id(userId)
                .email(email)
                .password(password)
                .build();
    }

    private static UserRequestDTO createUserRequestDto(String email, String password) {
        return UserRequestDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}
















