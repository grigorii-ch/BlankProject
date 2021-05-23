package com.chuprynin.epam.rd.blankproject.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание таблицы order
 */
@Getter
@Setter
@Entity
@Table(name = "order", schema = "chuprynin")
@Access(AccessType.FIELD)
@ToString(exclude = "customer")
public class Order extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Integer orderId;

    @Column(name = "ordernumber")
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @Column(name = "orderdate")
    private Date orderDate;

    @Column(name = "totalamount")
    private BigDecimal totalAmount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (
            name = "orderproduct",
            joinColumns = @JoinColumn(name = "orderid", referencedColumnName = "orderid"),
            inverseJoinColumns = @JoinColumn(name ="productid",referencedColumnName = "productid")
    )
    private Set<Product> products = new HashSet<>();

    public Order() {
    }
}
