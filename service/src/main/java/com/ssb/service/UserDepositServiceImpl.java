package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.person.user.usersDeposits.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDepositServiceImpl implements UserDepositService {
    private final UserDepositDao userDepositDao;

    @Autowired
    public UserDepositServiceImpl(UserDepositDao userDepositDao) {
        this.userDepositDao = userDepositDao;
    }

    @Override
    public List<UserDepositsDto> getAllUserDeposits() {
        return userDepositDao.getAllUserDeposits();
    }

    @Override
    public UserDepositsDto getUserDeposit(int id) {
        return userDepositDao.getUserDeposit(id);
    }

    @Override
    public int addUserDeposit(UserDepositsDto userDepositsDto) {
        return userDepositDao.addUserDeposit(userDepositsDto);
    }

    @Override
    public void updateUserDeposit(UserDepositsDto userDepositsDto) {
        userDepositDao.updateUserDeposit(userDepositsDto);
    }

    @Override
    public boolean deleteUserDeposit(UserDepositsDto userDepositsDto) {
        return userDepositDao.deleteUserDeposit(userDepositsDto);
    }
}

