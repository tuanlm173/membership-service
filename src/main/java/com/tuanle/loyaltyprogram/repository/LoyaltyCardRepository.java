package com.tuanle.loyaltyprogram.repository;

import com.tuanle.loyaltyprogram.entity.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Integer> {
}
