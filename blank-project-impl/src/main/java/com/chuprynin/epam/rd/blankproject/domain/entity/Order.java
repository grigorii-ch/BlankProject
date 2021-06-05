package com.chuprynin.epam.rd.blankproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание таблицы order
 */
@Data
@Entity
@Table(name = "order", schema = "chuprynin")
@ToString(exclude = "customer")
public class Order extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JsonIgnore
    @JoinTable (
            name = "orderproduct",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id",referencedColumnName = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Order() {
    }
}
