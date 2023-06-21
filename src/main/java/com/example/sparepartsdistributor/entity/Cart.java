package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cart.
 */
@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "user")
public class Cart {

    @Id
    private Long id;

    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    // I considered using a bidirectional one-to-one relationship,
    // taking into account the following:
    // 'Because of the reduced memory footprint and enabling the second-level cache direct retrieval,
    // the JPA 2.0 derived identifier is the preferred @OneToOne mapping strategy'
    // (High-Performance Java Persistence by Vlad Mihalcea).
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    /**
     * Adds a cart item to the list of cart items for this cart.
     *
     * @param cartItem the cart item to be added
     */
    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    /**
     * Removes a cart item to the list of cart items for this cart.
     *
     * @param cartItem the cart item to be removed
     */
    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
        cartItem.setPart(null);
    }


    /**
     * Sets the creation date of the cart to the current date when persisting the entity
     */
    @PrePersist
    protected void onCreate() {
        setCreatedAt(LocalDateTime.now());
    }
}

