package com.tuanle.loyaltyprogram.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CustomConfigurationRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String UPDATE_CONFIG = "UPDATE convert SET Sales = :sales WHERE Id = :id";

    @Transactional
    public int updateConfig(Long id, Long sales) {
        return em.createNativeQuery(UPDATE_CONFIG).setParameter("sales", sales).setParameter("id", id).executeUpdate();
    }
}
