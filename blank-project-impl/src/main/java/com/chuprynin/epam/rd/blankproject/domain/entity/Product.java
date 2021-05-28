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
@Table(name = "product", schema = "chuprynin")
@Access(AccessType.FIELD)
@ToString(exclude = "supplier")
public class Product extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne(cascade = CascadeType.MERGE , fetch=FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "unit_price")
    private BigDecimal unitprice;

    @Column(name = "isdiscontined")
    private boolean isdiscontined;

    @ManyToMany(mappedBy = "products", fetch=FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Product() {
    }
}
