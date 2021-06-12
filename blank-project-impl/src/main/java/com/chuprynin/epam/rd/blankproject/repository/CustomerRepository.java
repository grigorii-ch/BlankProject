package com.chuprynin.epam.rd.blankproject.repository;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий Customer
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
