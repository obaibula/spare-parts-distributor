package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private BigDecimal total = BigDecimal.ZERO;

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

    public void addOrderItem(OrderItem item){
        orderItems.add(item);
        item.setOrder(this);
    }

    public void removeOrderItem(OrderItem item){
        orderItems.remove(item);
        item.setOrder(null);
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
