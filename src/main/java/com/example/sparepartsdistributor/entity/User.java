package com.example.sparepartsdistributor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = "email") // email is a business key
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private BigDecimal balance;

    private Integer discount;

    private Integer paymentDeferment;

    private BigDecimal creditLimit;

    @Enumerated(value = EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @PrePersist
    protected void onCreate(){
        setCreatedAt(LocalDate.now());
    }

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
        this.balance = balance;
        this.discount = discount;
        this.paymentDeferment = paymentDeferment;
        this.creditLimit = creditLimit;
        this.shipmentStatus = shipmentStatus;
    }
}
