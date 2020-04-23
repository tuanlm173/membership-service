package com.tuanle.loyaltyprogram.service;

import com.tuanle.loyaltyprogram.dto.NameCardType;
import com.tuanle.loyaltyprogram.repository.CardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CustomCardTypeServiceImpl implements CustomCardTypeService{

    private static final String SQL_GET_NAME_CARD_TYPE_BY_ID = "SELECT Name FROM loyalty_card_type WHERE Id = :id";
    private String name;

    @PersistenceContext
    private EntityManager em;

    private final CardTypeRepository cardTypeRepository;

    @Autowired
    public CustomCardTypeServiceImpl(CardTypeRepository cardTypeRepository) {
        this.cardTypeRepository = cardTypeRepository;
    }

    @Override
    public void getNameCardType(Integer cardTypeId) {
        Object rawData = em.createNativeQuery(SQL_GET_NAME_CARD_TYPE_BY_ID).setParameter("id", cardTypeId).getSingleResult();
        name = rawData.toString();
    }

    @Override
    public NameCardType convertToNameCardTypeDto(Integer cardTypeId) {
        getNameCardType(cardTypeId);
        NameCardType nameCardType = new NameCardType();
        nameCardType.setName(name);
        return nameCardType;
    }


}
