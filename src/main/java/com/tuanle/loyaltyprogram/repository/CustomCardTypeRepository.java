package com.tuanle.loyaltyprogram.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomCardTypeRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String GET_LIST_THRESHOLD = "SELECT SpentThreshold, Id FROM loyalty_card_type";

    public Map<Long, Integer> getMapThresholdsToName() {
        List<Object[]> rows = em.createNativeQuery(GET_LIST_THRESHOLD).getResultList();
        Map<Long, Integer> results = new HashMap<>();
        for(Object[] row : rows) {
            Long thresHold = Long.valueOf(row[0].toString());
            Integer name = Integer.valueOf(row[1].toString());
            results.putIfAbsent(thresHold, name);
        }
        return results;
    }

}
