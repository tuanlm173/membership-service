package com.tuanle.loyaltyprogram.controller;

import com.tuanle.loyaltyprogram.dto.NameCardType;
import com.tuanle.loyaltyprogram.dto.RestEnvelope;
import com.tuanle.loyaltyprogram.entity.CardType;
import com.tuanle.loyaltyprogram.entity.Configuration;
import com.tuanle.loyaltyprogram.entity.LoyaltyCard;
import com.tuanle.loyaltyprogram.entity.Transaction;
import com.tuanle.loyaltyprogram.repository.CardTypeRepository;
import com.tuanle.loyaltyprogram.repository.ConfigurationRepository;
import com.tuanle.loyaltyprogram.repository.CustomConfigurationRepository;
import com.tuanle.loyaltyprogram.repository.CustomLoyaltyCardRepository;
import com.tuanle.loyaltyprogram.repository.CustomTransactionRepository;
import com.tuanle.loyaltyprogram.repository.LoyaltyCardRepository;
import com.tuanle.loyaltyprogram.repository.TransactionRepository;
import com.tuanle.loyaltyprogram.service.CustomCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MembershipController {

    private final CustomCardTypeService customCardTypeService;
    private final ConfigurationRepository configurationRepository;
    private final CustomConfigurationRepository customConfigurationRepository;
    private final CustomTransactionRepository customTransactionRepository;
    private final CustomLoyaltyCardRepository customLoyaltyCardRepository;
    private final LoyaltyCardRepository loyaltyCardRepository;
    private final TransactionRepository transactionRepository;
    private final CardTypeRepository cardTypeRepository;

    @Autowired
    public MembershipController(CustomCardTypeService customCardTypeService, ConfigurationRepository configurationRepository, CustomConfigurationRepository customConfigurationRepository, CustomTransactionRepository customTransactionRepository, CustomLoyaltyCardRepository customLoyaltyCardRepository, LoyaltyCardRepository loyaltyCardRepository, TransactionRepository transactionRepository, CardTypeRepository cardTypeRepository) {
        this.customCardTypeService = customCardTypeService;
        this.configurationRepository = configurationRepository;
        this.customConfigurationRepository = customConfigurationRepository;
        this.customTransactionRepository = customTransactionRepository;
        this.customLoyaltyCardRepository = customLoyaltyCardRepository;
        this.loyaltyCardRepository = loyaltyCardRepository;
        this.transactionRepository = transactionRepository;
        this.cardTypeRepository = cardTypeRepository;
    }

    @GetMapping(path = "api/test/cardtypename", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestEnvelope> getNameCardTypeById(@RequestParam(value = "id") Integer id) {
        NameCardType nameCardType = customCardTypeService.convertToNameCardTypeDto(id);
        return ResponseEntity.ok(RestEnvelope.of(nameCardType));
    }

    @PutMapping(path = "api/test/config/{id}")
    public ResponseEntity<RestEnvelope> updateConfig(@PathVariable(value = "id") Long id,
                                                     @RequestParam(value = "sales") Long sales) {
        return ResponseEntity.ok(RestEnvelope.of(customConfigurationRepository.updateConfig(id, sales)));
    }

    @GetMapping(path = "api/test/config")
    public ResponseEntity<RestEnvelope> getConfigById(@RequestParam(value = "id") Long id) {
        Optional<Configuration> optionalConfig = configurationRepository.findById(id);
        if(!optionalConfig.isPresent()) {
            String notFoundResource = "Resource not found";
            return ResponseEntity.ok(RestEnvelope.of(notFoundResource));
        }
        Configuration configuration = optionalConfig.get();
        return ResponseEntity.ok(RestEnvelope.of(configuration));
    }

    @GetMapping(path = "api/test/cardtype")
    public ResponseEntity<RestEnvelope> getCardTypeById(@RequestParam(value = "id") Integer id) {
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        if(!optionalCardType.isPresent()) {
            String notFoundResource = "Resource not found";
            return ResponseEntity.ok(RestEnvelope.of(notFoundResource));
        }
        CardType cardType = optionalCardType.get();
        return ResponseEntity.ok(RestEnvelope.of(cardType));
    }

    @GetMapping(path = "api/test/loyaltycard")
    public ResponseEntity<RestEnvelope> getLoyaltyCardById(@RequestParam(value = "id") Integer id) {
        Optional<LoyaltyCard> optionalLoyaltyCard = loyaltyCardRepository.findById(id);
        if(!optionalLoyaltyCard.isPresent()) {
            String notFoundResource = "Resource not found";
            return ResponseEntity.ok(RestEnvelope.of(notFoundResource));
        }
        LoyaltyCard loyaltyCard = optionalLoyaltyCard.get();
        return ResponseEntity.ok(RestEnvelope.of(loyaltyCard));
    }

    @GetMapping(path = "api/test/transaction")
    public ResponseEntity<RestEnvelope> getTransactionById(@RequestParam(value = "id") Integer id) {
        Optional<Transaction> optionalTrans = transactionRepository.findById(id);
        if(!optionalTrans.isPresent()) {
            String notFoundResource = "Resource not found";
            return ResponseEntity.ok(RestEnvelope.of(notFoundResource));
        }
        Transaction transaction = optionalTrans.get();
        return ResponseEntity.ok(RestEnvelope.of(transaction));
    }

    @PostMapping(path = "api/test/addtransaction")
    public ResponseEntity<RestEnvelope> updateTransaction(@RequestParam(value = "id") Integer id,
                                                          @RequestParam(value = "loyaltyCardId") Integer loyaltyCardId,
                                                          @RequestParam(value = "spentAdjust") Long spentAdjust) {
        customTransactionRepository.insertTransaction(id, loyaltyCardId, spentAdjust);
        int result = customLoyaltyCardRepository.updateLoyaltyCard(id);
        return ResponseEntity.ok(RestEnvelope.of(result));
    }
}
