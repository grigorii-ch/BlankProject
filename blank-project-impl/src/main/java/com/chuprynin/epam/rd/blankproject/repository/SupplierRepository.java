package com.chuprynin.epam.rd.blankproject.repository;

import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий Supplier
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
