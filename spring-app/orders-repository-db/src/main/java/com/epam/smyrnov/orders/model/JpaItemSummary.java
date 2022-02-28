package com.epam.smyrnov.orders.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "item_summary")
@Table(name = "item_summary")
@NoArgsConstructor
@AllArgsConstructor
public class JpaItemSummary implements ItemSummary {

    @Id
    @Column(name = "item_id")
    Long id;
    String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemSummary")
    @ToString.Exclude
    List<JpaOrderedItem> orderedItems;
}
