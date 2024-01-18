package com.ssb.data.dao.exchangeRate;

import com.ssb.data.model.*;

import java.util.*;

public interface ExchangeRateDao {
    ArrayList<ExchangeRateDto> getAllExchangeRates();
    ExchangeRateDto getExchangeRate(int id);
    int addExchangeRate(ExchangeRateDto exchangeRateDto);
    void updateExchangeRate(ExchangeRateDto exchangeRateDto);
    boolean deleteExchangeRate(ExchangeRateDto exchangeRateDto);
    ExchangeRateDto getExchangeRateByName(String name);
}