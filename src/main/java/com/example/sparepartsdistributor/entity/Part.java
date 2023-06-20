package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
@Getter @Setter
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

    public void addOrderItem(OrderItem item){
        orderItems.add(item);
        item.setPart(this);
    }

    public void removeOrderItem(OrderItem item){
        orderItems.remove(item);
        item.setPart(null);
    }

}
