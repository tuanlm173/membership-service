package com.tuanle.loyaltyprogram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.sql.Date.valueOf;

@Repository
public class CustomLoyaltyCardRepository {

    private static final String UPDATE_LOYALTY_CARD_WITH_NEW_TRANSACTION = "UPDATE loyalty_card " +
            "SET LoyaltyCartTypeid = :cardTypeId, Point = :point, TotalSpent = :totalSpent, ModifiedOn = :modifiedOn WHERE Id = :id";

    private static final String GET_CURRENT_POINT = "SELECT Point FROM loyalty_card WHERE Id = :id";

    private static final String GET_CURRENT_SPENT = "SELECT TotalSpent FROM loyalty_card WHERE Id = :id";

    private static final String GET_CURRENT_CARD_TYPE = "SELECT LoyaltyCartTypeid FROM loyalty_card WHERE Id = :id";

    private List<String> CardTypeName;

    private final CustomTransactionRepository customTransactionRepository;
    private final CustomCardTypeRepository customCardTypeRepository;

    @Autowired
    public CustomLoyaltyCardRepository(CustomTransactionRepository customTransactionRepository, CustomCardTypeRepository customCardTypeRepository) {
        this.customTransactionRepository = customTransactionRepository;
        this.customCardTypeRepository = customCardTypeRepository;
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public int updateLoyaltyCard(Integer transactionId) {
        Integer loyaltyCardId = customTransactionRepository.getLoyaltyCardId(transactionId);
        // Get current point, spent
        Object currentPointObj = em.createNativeQuery(GET_CURRENT_POINT).setParameter("id", loyaltyCardId).getSingleResult();
        Double currentPoint = Double.parseDouble(currentPointObj.toString());
        Object currentSpentObj = em.createNativeQuery(GET_CURRENT_SPENT).setParameter("id", loyaltyCardId).getSingleResult();
        Long currentSpent = Long.parseLong(currentSpentObj.toString());
        Object currentCardTypeObj = em.createNativeQuery(GET_CURRENT_CARD_TYPE).setParameter("id", loyaltyCardId).getSingleResult();
        Integer currentCardTypeId = Integer.parseInt(currentCardTypeObj.toString());

        // Get new point, spent
        Double newPoint = customTransactionRepository.getPoint(transactionId);
        Long newSpent = customTransactionRepository.getSpent(transactionId);

        // Update
        Double point = currentPoint + newPoint;
        Long spent = currentSpent + newSpent;
        // Update card type if any
        Map<Long, Integer> mapThresholdsToName = customCardTypeRepository.getMapThresholdsToName();
        List<Long> listThresholds = new ArrayList<>(mapThresholdsToName.keySet());
        listThresholds = listThresholds.stream().sorted().collect(Collectors.toList());
        Optional<Long> upperBoundedThreshold = listThresholds.stream().filter(thresHold -> thresHold > spent).findFirst();
        if (upperBoundedThreshold.isPresent()) {
            Long upperThreshold = upperBoundedThreshold.get();
            int index = listThresholds.indexOf(upperThreshold) - 1;
            Long lowerThreshold = listThresholds.get(index);
            currentCardTypeId = mapThresholdsToName.get(lowerThreshold);
        }

        return em.createNativeQuery(UPDATE_LOYALTY_CARD_WITH_NEW_TRANSACTION)
                .setParameter("cardTypeId", currentCardTypeId)
                .setParameter("point", point)
                .setParameter("totalSpent", spent)
                .setParameter("id", loyaltyCardId)
                .setParameter("modifiedOn", valueOf(LocalDateTime.now().toLocalDate()))
                .executeUpdate();
    }

}
