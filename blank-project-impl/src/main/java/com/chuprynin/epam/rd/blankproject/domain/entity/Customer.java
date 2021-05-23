package com.chuprynin.epam.rd.blankproject.domain.entity;

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
@ToString
@Table(name = "customer", schema = "chuprynin")
@Access(AccessType.FIELD)
public class Customer extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "customername")
    private String customerName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }
}

