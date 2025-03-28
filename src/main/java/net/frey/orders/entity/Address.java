package net.frey.orders.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String streetAddress, String city, String state, String zipCode) {}
