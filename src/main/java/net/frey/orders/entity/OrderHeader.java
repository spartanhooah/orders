package net.frey.orders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@AttributeOverrides({
    @AttributeOverride(name = "shippingAddress.streetAddress", column = @Column(name = "shipping_address")),
    @AttributeOverride(name = "shippingAddress.city", column = @Column(name = "shipping_city")),
    @AttributeOverride(name = "shippingAddress.state", column = @Column(name = "shipping_state")),
    @AttributeOverride(name = "shippingAddress.zipCode", column = @Column(name = "shipping_zip_code")),
    @AttributeOverride(name = "billingAddress.streetAddress", column = @Column(name = "billing_address")),
    @AttributeOverride(name = "billingAddress.city", column = @Column(name = "billing_city")),
    @AttributeOverride(name = "billingAddress.state", column = @Column(name = "billing_state")),
    @AttributeOverride(name = "billingAddress.zipCode", column = @Column(name = "billing_zipCode"))
})
@EqualsAndHashCode(callSuper = true)
public class OrderHeader extends BaseEntity {
    private String customerName;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader")
    Set<OrderLine> orderLines;
}
