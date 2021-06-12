package com.chuprynin.epam.rd.blankproject.repository;

import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
