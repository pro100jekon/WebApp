package com.epam.smyrnov.orders.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
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
@Entity(name = "user_summary")
@Table(name = "user_summary")
@NoArgsConstructor
@AllArgsConstructor
public class JpaUserSummary implements UserSummary {

    @Id
    @Column(name = "user_id")
    Long id;
    String firstName;
    String lastName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userSummary", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<JpaOrder> orders;
}