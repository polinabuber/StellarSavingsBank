package com.ssb.service;

import com.ssb.data.model.*;

import java.math.*;
import java.util.*;

public interface ExchangeRateService {

    ArrayList<ExchangeRateDto> getAllExchangeRates();

    ExchangeRateDto getExchangeRate(int id);

    int addExchangeRate(ExchangeRateDto exchangeRateDto);

    void updateExchangeRate(ExchangeRateDto exchangeRateDto);

    boolean deleteExchangeRate(ExchangeRateDto exchangeRateDto);
    ExchangeRateDto getExchangeRateByName(String name);
    BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount);
}