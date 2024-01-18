package com.ssb.service;

import com.ssb.data.dao.exchangeRate.*;
import com.ssb.data.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateDao exchangeRateDao;

    public ExchangeRateServiceImpl(@Autowired ExchangeRateDao exchangeRateDao) {
        this.exchangeRateDao = exchangeRateDao;
    }

    @Override
    public ArrayList<ExchangeRateDto> getAllExchangeRates() {
        return exchangeRateDao.getAllExchangeRates();
    }

    @Override
    public ExchangeRateDto getExchangeRate(int id) {
        return exchangeRateDao.getExchangeRate(id);
    }

    @Override
    public int addExchangeRate(ExchangeRateDto exchangeRateDto) {
        return exchangeRateDao.addExchangeRate(exchangeRateDto);
    }

    @Override
    public void updateExchangeRate(ExchangeRateDto exchangeRateDto) {
        if (exchangeRateDto.getBuyRate().scale() > 4 || exchangeRateDto.getSellRate().scale() > 4) {
            throw new IllegalArgumentException("Please enter a valid exchange rate");
        }

        ExchangeRateDto currentExchangeRate = exchangeRateDao.getExchangeRate(exchangeRateDto.getId());

        if ("USD".equals(currentExchangeRate.getName()) || "EUR".equals(currentExchangeRate.getName())) {
            exchangeRateDto.setDisplayInList(true);
        } else {
            exchangeRateDto.setDisplayInList(false);
        }

        currentExchangeRate.setBuyRate(exchangeRateDto.getBuyRate());
        currentExchangeRate.setSellRate(exchangeRateDto.getSellRate());
        currentExchangeRate.setDisplayInList(exchangeRateDto.isDisplayInList());

        exchangeRateDao.updateExchangeRate(currentExchangeRate);
    }



    @Override
    public boolean deleteExchangeRate(ExchangeRateDto exchangeRateDto) {
        return exchangeRateDao.deleteExchangeRate(exchangeRateDto);
    }

    @Override
    public ExchangeRateDto getExchangeRateByName(String name) {
        return exchangeRateDao.getExchangeRateByName(name);
    }

    @Override
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        ExchangeRateDto fromExchangeRate = getExchangeRateByName(fromCurrency);
        ExchangeRateDto toExchangeRate = getExchangeRateByName(toCurrency);

        if (fromExchangeRate != null && toExchangeRate != null) {
            BigDecimal rate;
            if ("BYN".equals(fromCurrency)) {
                rate = fromExchangeRate.getSellRate().divide(toExchangeRate.getBuyRate(), 4, RoundingMode.HALF_UP);
            } else {
                rate = fromExchangeRate.getBuyRate().divide(toExchangeRate.getSellRate(), 4, RoundingMode.HALF_UP);
            }
            return amount.multiply(rate);
        }

        return null;
    }

}
