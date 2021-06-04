package com.chuprynin.epam.rd.blankproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание таблицы customer
 */
@Entity
@Getter
@Setter
@Table(name = "customer", schema = "chuprynin")
@Access(AccessType.FIELD)
@ToString(exclude = "orders")
public class Customer extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch=FetchType.EAGER, cascade = CascadeType.MERGE )
    private List<Order> orders = new ArrayList<>();
}

