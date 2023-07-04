package com.example.sparepartsdistributor.part;

import java.math.BigDecimal;

public record PartDto(Long id,
                      String partNumber,
                      String name,
                      String brand,
                      String category,
                      BigDecimal price,
                      Integer stockQuantity,
                      Integer availableQuantity,
                      Integer deliveryTime,
                      String image) {

    public static PartDto partToDto(Part part){
       return new PartDto(part.getId(),
                part.getPartNumber(),
                part.getName(),
                part.getBrand(),
                part.getCategory(),
                part.getPrice(),
                part.getStockQuantity(),
                part.getAvailableQuantity(),
                part.getDeliveryTime(),
                part.getImage());
    }
}
