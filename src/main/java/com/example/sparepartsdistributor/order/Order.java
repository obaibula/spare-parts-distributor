package com.example.sparepartsdistributor.order;

import com.example.sparepartsdistributor.orderitem.OrderItem;
import com.example.sparepartsdistributor.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order entity.
 */

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "user")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @Column(nullable = false)
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * Adds an orderItem to the order.
     *
     * @param item the orderItem to be added
     */
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }

    /**
     * Removes an orderItem from the order.
     *
     * @param item the orderItem to be removed
     */
    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
        item.setOrder(null);
    }

    /**
     * Sets the createdAt and updatedAt properties to the current date and time before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
    }

    /**
     * Sets the updatedAt property to the current date and time before updating the entity.
     */
    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
