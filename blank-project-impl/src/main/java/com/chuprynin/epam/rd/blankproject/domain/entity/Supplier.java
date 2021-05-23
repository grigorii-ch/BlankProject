package com.chuprynin.epam.rd.blankproject.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание таблицы supplier
 */
@Getter
@Setter
@Entity
@Table(name = "supplier")
@Access(AccessType.FIELD)
@ToString
public class Supplier extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private Integer supplierId;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Supplier() {
    }
}
