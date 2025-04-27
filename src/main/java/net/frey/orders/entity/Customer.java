package net.frey.orders.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer extends BaseEntity {
    @Size(max = 50)
    private String name;

    @Valid
    @Embedded
    private Address address;

    @Size(max = 20)
    private String phone;

    @Email
    @Size(max = 255)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<OrderHeader> orderHeaders = new LinkedHashSet<>();

    @Version
    private Integer version;

    public void addOrderHeader(OrderHeader orderHeader) {
        orderHeaders.add(orderHeader);
        orderHeader.setCustomer(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return Objects.equals(getName(), customer.getName())
                && Objects.equals(getAddress(), customer.getAddress())
                && Objects.equals(getPhone(), customer.getPhone())
                && Objects.equals(getEmail(), customer.getEmail());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getAddress());
        result = 31 * result + Objects.hashCode(getPhone());
        result = 31 * result + Objects.hashCode(getEmail());
        return result;
    }
}
