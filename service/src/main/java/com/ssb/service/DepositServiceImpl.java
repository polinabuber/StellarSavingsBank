package com.ssb.service;

import com.ssb.data.dao.deposits.*;
import com.ssb.data.model.*;
import com.ssb.data.person.user.account.*;
import com.ssb.data.person.user.usersDeposits.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class DepositServiceImpl implements DepositService {
    private final DepositDao depositDao;
    private final UserAccountDao userAccountDao;
    private final UserDepositDao userDepositsDao;

    @Autowired
    public DepositServiceImpl(DepositDao depositDao, UserAccountDao userAccountDao, UserDepositDao userDepositsDao) {
        this.depositDao = depositDao;
        this.userAccountDao = userAccountDao;
        this.userDepositsDao = userDepositsDao;
    }

    @Override
    public List<DepositsDto> getAllDeposits() {
        return depositDao.getAllDeposits();
    }

    @Override
    public DepositsDto getDeposit(int id) {
        return depositDao.getDeposit(id);
    }

    @Override
    public int addDeposit(DepositsDto depositDto) {
        return depositDao.addDeposit(depositDto);
    }

    @Override
    public void updateDeposit(DepositsDto depositDto) {
        depositDao.updateDeposit(depositDto);
    }

    @Override
    public boolean deleteDeposit(int id) {
        return depositDao.deleteDeposit(id);
    }

    @Override
    public void addToDeposit(int userId, int depositId, BigDecimal amount) {
        UserAccountDto userAccount = userAccountDao.getUserAccountById(userId);
        if (userAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        userAccount.setBalance(userAccount.getBalance().subtract(amount));
        userAccountDao.updateUserAccount(userAccount);

        UserDepositsDto userDeposit = userDepositsDao.getUserDeposit(userId);
        if (userDeposit == null) {
            userDeposit = new UserDepositsDto();
            userDeposit.setUserAccountId(userId);
            userDeposit.setDepositId(depositId);
        }
        userDeposit.setBalance(userDeposit.getBalance().add(amount));
        userDepositsDao.updateUserDeposit(userDeposit);
    }

    @Override
    public void calculateInterest() {
        List<UserDepositsDto> allUserDeposits = userDepositsDao.getAllUserDeposits();
        for (UserDepositsDto userDeposit : allUserDeposits) {
            DepositsDto deposit = getDeposit(userDeposit.getDepositId());
            BigDecimal dailyInterestRate = BigDecimal.valueOf(deposit.getPercentage()).divide(BigDecimal.valueOf(36500), 2, RoundingMode.HALF_UP);
            BigDecimal interest = userDeposit.getBalance().multiply(dailyInterestRate);
            userDeposit.setBalance(userDeposit.getBalance().add(interest));
            userDepositsDao.updateUserDeposit(userDeposit);
        }
    }
}


