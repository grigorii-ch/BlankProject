package com.chuprynin.epam.rd.blankproject.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание таблицы product
 */
@Getter
@Setter
@Entity
@Table(name = "product")
@Access(AccessType.FIELD)
@ToString(exclude = "supplier")
public class Product extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Integer productId;

    @Column(name = "productname")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @Column(name = "unitprice")
    private BigDecimal unitprice;

    @Column(name = "isdiscontined")
    private boolean isdiscontined;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Product() {
    }
}
