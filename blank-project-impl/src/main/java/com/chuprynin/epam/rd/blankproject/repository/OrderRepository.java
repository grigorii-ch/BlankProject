package com.chuprynin.epam.rd.blankproject.repository;

import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий Order
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
