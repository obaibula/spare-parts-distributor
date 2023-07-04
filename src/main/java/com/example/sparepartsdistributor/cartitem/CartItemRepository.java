package com.example.sparepartsdistributor.cartitem;

import com.example.sparepartsdistributor.cart.CartDto;
import com.example.sparepartsdistributor.part.PartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing сart items.
 * Provided CRUD operations and query methods for the {@link CartItem} entity.
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Retrieves a list of CartItemDto objects.
     * <p>
     * This method executes a custom JPQL query to fetch a list of CartItemDto objects
     * containing specific attributes from the CartItem entity along with associated entities,
     * such as Cart and Part, represented by their respective DTO classes.
     *
     * @return a list of {@link CartItemDto} objects representing CartItem details along with associated entities
     * @see CartItemDto
     * @see CartDto
     * @see PartDto
     */
    @Query("""
            SELECT new com.example.sparepartsdistributor.cartitem.CartItemDto(
                ci.id,
                ci.quantity,
                new com.example.sparepartsdistributor.cart.CartDto(
                    c.id,
                    c.createdAt,
                    c.totalPrice),
                new com.example.sparepartsdistributor.part.PartDto(
                    p.id,
                    p.partNumber,
                    p.name,
                    p.brand,
                    p.category,
                    p.price,
                    p.stockQuantity,
                    p.availableQuantity,
                    p.deliveryTime,
                    p.image)
            )
            FROM CartItem ci
            INNER JOIN ci.part p
            INNER JOIN ci.cart c
            """)
    List<CartItemDto> findAllDtos();

    @Query("""
            FROM CartItem ci
            INNER JOIN ci.part p
            INNER JOIN ci.cart c
            WHERE c.id = :cartId AND p.id = :partId
            """)
    Optional<CartItem> findByCartIdAndPartId(Long cartId, Long partId);
}
