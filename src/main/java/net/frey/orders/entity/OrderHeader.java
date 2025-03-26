package net.frey.orders.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class OrderHeader extends BaseEntity {
    private String customerName;
}