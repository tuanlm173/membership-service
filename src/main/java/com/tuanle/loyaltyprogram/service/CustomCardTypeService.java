package com.tuanle.loyaltyprogram.service;

import com.tuanle.loyaltyprogram.dto.NameCardType;

public interface CustomCardTypeService {

    void getNameCardType(Integer cardTypeId);

    NameCardType convertToNameCardTypeDto(Integer cardTypeId);
}
