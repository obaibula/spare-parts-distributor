package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items", uniqueConstraints =
    @UniqueConstraint(
            columnNames = {"cart_id", "part_id"}))
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"cart", "part"})
@ToString(exclude = {"cart", "part"})
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "part_id")
    private Part part;
}
