package com.tuanle.loyaltyprogram.utils;

public final class CalcPointUtils {

    private CalcPointUtils(){}

    public static Long calculatePoint(Long sales, Long baseSpent) {
        return sales / baseSpent;
    }
}
