package com.tuanle.loyaltyprogram.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Service
public class PointConverterServiceImpl implements PointConverterService {

    private final Map<Long, Long> converter = new HashMap<>();
    private static final Long point = 1L;
    private Long sales;
    private static final String GET_MAPPING_CONVERT = "SELECT Sales FROM convert";

    @PersistenceContext
    private EntityManager em;


    private void getSales() {
        Object rawData = em.createNativeQuery(GET_MAPPING_CONVERT).getSingleResult();
        sales = Long.parseLong(rawData.toString());
    }

    @Override
    public Map<Long, Long> getConverter() {
        getSales();
        converter.putIfAbsent(point, sales);
        return converter;
    }

}
