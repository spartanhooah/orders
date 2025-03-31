package net.frey.orders.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderApproval extends BaseEntity {
    private String approvedBy;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderApproval that = (OrderApproval) o;
        return Objects.equals(approvedBy, that.approvedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(approvedBy);
    }
}
