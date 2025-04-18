package net.frey.orders.entity;

import jakarta.persistence.Embeddable;
import org.hibernate.validator.constraints.Length;

@Embeddable
public record Address(
        @Length(max = 30) String streetAddress,
        @Length(max = 30) String city,
        @Length(max = 30) String state,
        @Length(max = 30) String zipCode) {}
