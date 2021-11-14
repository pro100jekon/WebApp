package com.epam.smyrnov.orders.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "ordered_items")
@Table(name = "ordered_items")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderedItemId.class)
public class JpaOrderedItem implements OrderedItem {

    @Id
    @Column(name = "order_id", updatable = false, insertable = false)
    Long orderId;
    @Id
    Long itemId;
    BigDecimal price;
    Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    JpaOrder order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JpaOrderedItem orderedItem = (JpaOrderedItem) o;
        return Objects.equals(orderId, orderedItem.orderId) && Objects.equals(itemId, orderedItem.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}