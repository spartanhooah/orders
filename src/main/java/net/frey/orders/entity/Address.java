package net.frey.orders.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Embeddable
public record Address(
        @Size(max = 30) String streetAddress,
        @Size(max = 30) String city,
        @Size(max = 30) String state,
        @Size(max = 30) String zipCode) {}
