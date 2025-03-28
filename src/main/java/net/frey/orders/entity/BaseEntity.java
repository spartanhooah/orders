package net.frey.orders.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    private Timestamp lastModifiedDate;
}
