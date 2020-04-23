package com.tuanle.loyaltyprogram.repository;

import com.tuanle.loyaltyprogram.service.PointConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

import com.tuanle.loyaltyprogram.utils.CalcPointUtils;
import static java.sql.Date.valueOf;

@Repository
public class CustomTransactionRepository {

    @Autowired
    private PointConverterService pointConverterService;

    @PersistenceContext
    private EntityManager em;

    private static final Long basePoint = 1L;

    private static final String INSERT_TRANSACTION = "INSERT INTO transaction (Id, LoyaltyCardId, PointAdjust, SpentAdjust, CreatedOn) " +
            "VALUES (:id, :loyaltyCardId, :pointAdjust, :spentAdjust, :createdOn)";

    private static final String GET_POINT_FROM_TRANSACTION = "SELECT PointAdjust FROM transaction WHERE Id = :id";

    private static final String GET_SPENT_FROM_TRANSACTION = "SELECT SpentAdjust FROM transaction WHERE Id = :id";

    private static final String GET_LOYALTY_CARD_ID_FROM_TRANSACTION = "SELECT LoyaltyCardId FROM transaction WHERE Id = :id";

    @Transactional
    public int insertTransaction(Integer transactionId, Integer loyaltyCardId, Long spentAdjust) {
        //TODO: process transaction
        Map<Long, Long> converter = pointConverterService.getConverter();
        Long baseSpent = converter.getOrDefault(basePoint, 0L);
        Double pointAdjust = CalcPointUtils.calculatePoint(spentAdjust, baseSpent).doubleValue();
        return em.createNativeQuery(INSERT_TRANSACTION)
                .setParameter("id", transactionId)
                .setParameter("loyaltyCardId", loyaltyCardId)
                .setParameter("pointAdjust", pointAdjust)
                .setParameter("spentAdjust", spentAdjust)
                .setParameter("createdOn", valueOf(LocalDateTime.now().toLocalDate()))
                .executeUpdate();
    }

    public Double getPoint(Integer id) {
        Object rawData = em.createNativeQuery(GET_POINT_FROM_TRANSACTION).setParameter("id", id).getSingleResult();
        return Double.parseDouble(rawData.toString());
    }

    public Long getSpent(Integer id) {
        Object rawData = em.createNativeQuery(GET_SPENT_FROM_TRANSACTION).setParameter("id", id).getSingleResult();
        return Long.parseLong(rawData.toString());
    }

    public Integer getLoyaltyCardId(Integer id) {
        Object rawData = em.createNativeQuery(GET_LOYALTY_CARD_ID_FROM_TRANSACTION).setParameter("id", id).getSingleResult();
        return Integer.parseInt(rawData.toString());
    }
}
