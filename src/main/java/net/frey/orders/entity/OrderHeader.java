package net.frey.orders.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

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
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    Set<OrderLine> orderLines;

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }

        orderLines.add(orderLine);
        orderLine.setOrderHeader(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrderHeader that = (OrderHeader) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this)
                        .getHibernateLazyInitializer()
                        .getPersistentClass()
                        .hashCode()
                : getClass().hashCode();
    }
}
