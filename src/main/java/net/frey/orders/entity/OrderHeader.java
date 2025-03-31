package net.frey.orders.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
public class OrderHeader extends BaseEntity {
    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Setter(AccessLevel.NONE)
    @OneToMany(
            mappedBy = "orderHeader",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    Set<OrderLine> orderLines;

    @OneToOne(cascade = CascadeType.PERSIST)
    private OrderApproval orderApproval;

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }

        orderLines.add(orderLine);
        orderLine.setOrderHeader(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customer, that.customer)
                && Objects.equals(shippingAddress, that.shippingAddress)
                && Objects.equals(billingAddress, that.billingAddress)
                && orderStatus == that.orderStatus
                && Objects.equals(orderApproval, that.orderApproval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, shippingAddress, billingAddress, orderStatus, orderApproval);
    }
}
