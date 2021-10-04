package com.epam.smyrnov.items.model;

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "items")
@NoArgsConstructor
@AllArgsConstructor
public class JpaItem implements Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String category;
    @Column(unique = true)
    String name;
    BigDecimal price;
    Date dateOfAddition;
    String size;
    String color;
    Integer weight;
    //List<String> imageURLs;

    public static Item empty() {
        return new JpaItem();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JpaItem item = (JpaItem) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

