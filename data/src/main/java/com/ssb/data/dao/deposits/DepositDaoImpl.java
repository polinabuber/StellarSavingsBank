package com.ssb.data.dao.deposits;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Repository
@Transactional
public class DepositDaoImpl implements DepositDao {
    private final SessionFactory sessionFactory;

    public DepositDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<DepositsDto> getAllDeposits() {
        Session session = sessionFactory.getCurrentSession();
        Query<Deposits> query = session.createQuery("FROM Deposits", Deposits.class);
        return convertToDtoList(query.list());
    }

    @Override
    public DepositsDto getDeposit(int id) {
        Session session = sessionFactory.getCurrentSession();
        return convertToDto(session.get(Deposits.class, id));
    }

    @Override
    public int addDeposit(DepositsDto depositDto) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(convertToEntity(depositDto));
    }

    @Override
    public void updateDeposit(DepositsDto depositDto) {
        Session session = sessionFactory.getCurrentSession();
        session.update(convertToEntity(depositDto));
    }

    @Override
    public boolean deleteDeposit(int id) {
        Session session = sessionFactory.getCurrentSession();
        Deposits deposits = session.get(Deposits.class, id);
        if (deposits != null) {
            session.delete(deposits);
            return true;
        } else {
            throw new IllegalArgumentException("News with id " + id + " not found");
        }
    }

    private DepositsDto convertToDto(Deposits deposit) {
        DepositsDto dto = new DepositsDto();
        dto.setId(deposit.getId());
        dto.setName(deposit.getName());
        dto.setPercentage(deposit.getPercentage());
        dto.setDepositDetails(deposit.getDepositDetails());
        return dto;
    }

    private Deposits convertToEntity(DepositsDto dto) {
        Deposits deposit = new Deposits();
        deposit.setId(dto.getId());
        deposit.setName(dto.getName());
        deposit.setPercentage(dto.getPercentage());
        deposit.setDepositDetails(dto.getDepositDetails());
        return deposit;
    }

    private ArrayList<DepositsDto> convertToDtoList(List<Deposits> depositsList) {
        ArrayList<DepositsDto> dtoList = new ArrayList<>();
        for (Deposits deposit : depositsList) {
            dtoList.add(convertToDto(deposit));
        }
        return dtoList;
    }
}
