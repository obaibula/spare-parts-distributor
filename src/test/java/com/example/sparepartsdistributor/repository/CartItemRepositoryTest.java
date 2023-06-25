package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.config.TestDatabaseContainerConfig;
import com.example.sparepartsdistributor.dto.CartItemDto;
import com.example.sparepartsdistributor.entity.CartItem;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseContainerConfig.class)
@TestPropertySource("classpath:application-test.properties")
class CartItemRepositoryTest {

    @Autowired
    private static PostgreSQLContainer<?> container;

    @Autowired
    private CartItemRepository underTest;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PartRepository partRepository;

    @BeforeEach
    void setUp(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testFindAllDtos() {
        // given
        CartItem cartItem = new CartItem();
        cartItem.setCart(cartRepository.findById(1L).orElseThrow());
        cartItem.setPart(partRepository.findById(1L).orElseThrow());

        underTest.save(cartItem);

        CartItem carItem2 = new CartItem();
        carItem2.setCart(cartRepository.findById(1L).orElseThrow());
        carItem2.setPart(partRepository.findById(2L).orElseThrow());

        underTest.save(carItem2);

        // when
        List<CartItemDto> cartItemDtos = underTest.findAllDtos();

        // then
        assertThat(cartItemDtos).isNotNull();
        assertThat(cartItemDtos).hasSize(2);
    }

    @Test
    public void test2() {
    }
}