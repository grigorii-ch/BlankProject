package com.chuprynin.epam.rd.blankproject.domain.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание таблицы supplier
 */
@Data
@Entity
@Table(name = "supplier")
@Access(AccessType.FIELD)
@ToString
public class Supplier extends EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "supplier", fetch=FetchType.EAGER, cascade = CascadeType.MERGE )
    private List<Product> products = new ArrayList<>();

    public Supplier() {
    }
}
