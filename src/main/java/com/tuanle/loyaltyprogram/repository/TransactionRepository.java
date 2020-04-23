package com.tuanle.loyaltyprogram.repository;

import com.tuanle.loyaltyprogram.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
