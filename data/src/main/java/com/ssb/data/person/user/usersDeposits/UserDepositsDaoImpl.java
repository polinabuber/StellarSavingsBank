package com.ssb.data.person.user.usersDeposits;

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
public class UserDepositsDaoImpl implements UserDepositDao {
    private final SessionFactory sessionFactory;

    public UserDepositsDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<UserDepositsDto> getAllUserDeposits() {
        Session session = sessionFactory.getCurrentSession();
        Query<UserDeposits> query = session.createQuery("FROM UserDeposits", UserDeposits.class);
        return convertToDtoList(query.list());
    }

    @Override
    public UserDepositsDto getUserDeposit(int id) {
        Session session = sessionFactory.getCurrentSession();
        return convertToDto(session.get(UserDeposits.class, id));
    }

    @Override
    public int addUserDeposit(UserDepositsDto userDepositsDto) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(convertToEntity(userDepositsDto));
    }

    @Override
    public void updateUserDeposit(UserDepositsDto userDepositsDto) {
        Session session = sessionFactory.getCurrentSession();
        session.update(convertToEntity(userDepositsDto));
    }

    @Override
    public boolean deleteUserDeposit(UserDepositsDto userDepositsDto) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(convertToEntity(userDepositsDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private UserDepositsDto convertToDto(UserDeposits userDeposits) {
        UserDepositsDto dto = new UserDepositsDto();
        dto.setId(userDeposits.getId());
        dto.setUserAccountId(userDeposits.getUserAccount().getId());
        dto.setDepositId(userDeposits.getDeposit().getId());
        dto.setBalance(userDeposits.getBalance());
        return dto;
    }

    private UserDeposits convertToEntity(UserDepositsDto dto) {
        UserDeposits userDeposits = new UserDeposits();
        UserAccount userAccount = new UserAccount();
        userAccount.setId(dto.getUserAccountId());
        Deposits deposit = new Deposits();
        deposit.setId(dto.getDepositId());
        userDeposits.setId(dto.getId());
        userDeposits.setUserAccount(userAccount);
        userDeposits.setDeposit(deposit);
        userDeposits.setBalance(dto.getBalance());
        return userDeposits;
    }

    private ArrayList<UserDepositsDto> convertToDtoList(List<UserDeposits> userDepositsList) {
        ArrayList<UserDepositsDto> dtoList = new ArrayList<>();
        for (UserDeposits userDeposits : userDepositsList) {
            dtoList.add(convertToDto(userDeposits));
        }
        return dtoList;
    }
}
