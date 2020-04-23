package com.tuanle.loyaltyprogram.repository;

import com.tuanle.loyaltyprogram.entity.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
}
