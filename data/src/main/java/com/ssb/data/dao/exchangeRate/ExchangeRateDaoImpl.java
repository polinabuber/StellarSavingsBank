package com.ssb.data.dao.exchangeRate;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.sql.*;
import java.util.*;

@Repository
@Transactional
public class ExchangeRateDaoImpl implements ExchangeRateDao {
    private final SessionFactory sessionFactory;

    public ExchangeRateDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<ExchangeRateDto> getAllExchangeRates() {
        Session session = sessionFactory.getCurrentSession();
        List<ExchangeRate> exchangeRateList = session.createQuery("FROM ExchangeRate", ExchangeRate.class).getResultList();
        return convertToDtoList(exchangeRateList);
    }

    private ArrayList<ExchangeRateDto> convertToDtoList(List<ExchangeRate> exchangeRateList) {
        ArrayList<ExchangeRateDto> exchangeRateDtoList = new ArrayList<>();
        for (ExchangeRate exchangeRate : exchangeRateList) {
            exchangeRateDtoList.add(convertToDto(exchangeRate));
        }
        return exchangeRateDtoList;
    }

    private ExchangeRateDto convertToDto(ExchangeRate exchangeRate) {
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setId(exchangeRate.getId());
        exchangeRateDto.setName(exchangeRate.getName());
        exchangeRateDto.setBuyRate(exchangeRate.getBuyRate());
        exchangeRateDto.setSellRate(exchangeRate.getSellRate());
        exchangeRateDto.setDisplayInList(exchangeRate.isDisplayInList());
        exchangeRateDto.setTimestamp(exchangeRate.getTimestamp());
        return exchangeRateDto;
    }

    @Override
    public ExchangeRateDto getExchangeRate(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ExchangeRate exchangeRate = session.get(ExchangeRate.class, id);
        return exchangeRate != null ? convertToDto(exchangeRate) : null;
    }

    @Override
    public int addExchangeRate(ExchangeRateDto exchangeRateDto) {
        Session session = this.sessionFactory.getCurrentSession();
        ExchangeRate exchangeRate = convertToEntity(exchangeRateDto);
        return (int) session.save(exchangeRate);
    }

    private ExchangeRate convertToEntity(ExchangeRateDto exchangeRateDto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(exchangeRateDto.getId());
        exchangeRate.setName(exchangeRateDto.getName());
        exchangeRate.setBuyRate(exchangeRateDto.getBuyRate());
        exchangeRate.setSellRate(exchangeRateDto.getSellRate());
        exchangeRate.setDisplayInList(exchangeRateDto.isDisplayInList());
        exchangeRate.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return exchangeRate;
    }

    @Override
    public void updateExchangeRate(ExchangeRateDto exchangeRateDto) {
        Session session = sessionFactory.getCurrentSession();
        ExchangeRate exchangeRate = session.get(ExchangeRate.class, exchangeRateDto.getId());
        if (exchangeRate != null) {
            exchangeRate.setName(exchangeRateDto.getName());
            exchangeRate.setBuyRate(exchangeRateDto.getBuyRate());
            exchangeRate.setSellRate(exchangeRateDto.getSellRate());
            exchangeRate.setDisplayInList(exchangeRateDto.isDisplayInList());
            exchangeRate.setTimestamp(new Timestamp(System.currentTimeMillis()));
            session.update(exchangeRate);
        }
    }

    @Override
    public boolean deleteExchangeRate(ExchangeRateDto exchangeRateDto) {
        Session session = sessionFactory.getCurrentSession();
        ExchangeRate exchangeRate = session.get(ExchangeRate.class, exchangeRateDto.getId());
        if (exchangeRate != null) {
            session.delete(exchangeRate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ExchangeRateDto getExchangeRateByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<ExchangeRate> query = session.createQuery("FROM ExchangeRate WHERE name = :name", ExchangeRate.class);
        query.setParameter("name", name);
        ExchangeRate exchangeRate = query.uniqueResult();
        return exchangeRate != null ? convertToDto(exchangeRate) : null;
    }
}
