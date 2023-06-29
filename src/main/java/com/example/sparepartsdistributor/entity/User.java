package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a user.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "email") // Email is considered as a business key.
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The created date must not be updated after creation.
    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private LocalDate createdAt;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password;

    private BigDecimal balance = BigDecimal.ZERO;

    private Integer discount = 0;

    private Integer paymentDeferment = 0;

    private BigDecimal creditLimit = BigDecimal.ZERO;

    @Enumerated(value = EnumType.STRING)
    private ShipmentStatus shipmentStatus = ShipmentStatus.ALLOWED;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Cart cart;

    /**
     * Constructs a new User object with the specified attributes.
     *
     * <p>This constructor is specifically designed for use with the Builder pattern.
     * It sets default values, when needed, for fields not provided by the builder.
     */
    @Builder
    public User(Long id,
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
                ShipmentStatus shipmentStatus) {

        this.id = id;
        this.createdAt = createdAt;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.discount = (discount != null) ? discount : 0;
        this.paymentDeferment = (paymentDeferment != null) ? paymentDeferment : 0;
        this.creditLimit = (creditLimit != null) ? creditLimit : BigDecimal.ZERO;
        this.shipmentStatus = (shipmentStatus != null) ? shipmentStatus : ShipmentStatus.ALLOWED;
    }

    /**
     * Adds an order to the list of orders for this user.
     *
     * @param order the order to be added
     */
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    /**
     * Removes an order from the list of orders for this user.
     *
     * @param order the order to be removed
     */
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public void setCart(Cart cart) {
        if (cart == null) {
            if (this.cart != null) this.cart.setUser(null);
        }
        else cart.setUser(this);
        this.cart = cart;
    }

    /**
     * Sets the creation date of the user to the current date when persisting the entity
     */
    @PrePersist
    protected void onCreate() {
        setCreatedAt(LocalDate.now());
    }
}
