package com.example.sparepartsdistributor.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents the shipment status for a user.
 */
public enum ShipmentStatus {
    ALLOWED,
    BLOCKED,
    INVALID;

    // todo: is it the best approach?
    /**
     * Converts enum constant name to enum constant.
     *
     * @param name
     *         enum constant name
     * @return enum constant, or {@link #INVALID} if there is no enum constant with that name
     */
    @JsonCreator
    public static ShipmentStatus valueOfOrInvalid(String name) {
        try {
            return ShipmentStatus.valueOf(name);
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }
}
