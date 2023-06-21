package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a spare part.
 */
@Entity
@Table(name = "parts", uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "partNumber"}))
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"partNumber", "brand"}) // part-numbers can be similar
@ToString
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String partNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer stockQuantity = 0;

    private Integer availableQuantity = 0;

    private Integer deliveryTime = 0;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "part",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * Constructs a new Part object with the specified attributes.
     *
     * <p>This constructor is specifically designed for use with the Builder pattern.
     * It sets default values, when needed, for fields not provided by the builder.
     */
    @Builder
    public Part(Long id,
                String partNumber,
                String name,
                String brand,
                String category,
                BigDecimal price,
                Integer stockQuantity,
                Integer availableQuantity,
                Integer deliveryTime,
                String image) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stockQuantity = (stockQuantity != null) ? stockQuantity : 0;
        this.availableQuantity = (availableQuantity != null) ? availableQuantity : 0;
        this.deliveryTime = (deliveryTime != null) ? deliveryTime : 0;
        this.image = image;
    }

    /**
     * Adds an order item to the list of order items for this part.
     *
     * @param item the order item to be added
     */
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setPart(this);
    }

    /**
     * Removes an order item from the list of order items for this part.
     *
     * @param item the order item to be removed
     */
    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
        item.setPart(null);
    }
}
