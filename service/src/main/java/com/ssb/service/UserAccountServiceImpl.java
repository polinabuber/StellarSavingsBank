package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.person.user.account.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountDao userAccountDao;

    @Autowired
    public UserAccountServiceImpl(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public List<UserAccountDto> getAllUserAccounts() {
        return userAccountDao.getAllUserAccounts();
    }

    @Override
    public UserAccountDto getUserAccountById(int id) {
        return userAccountDao.getUserAccountById(id);
    }

    @Override
    public int addUserAccount(UserAccountDto userAccountDto) {
        return userAccountDao.addUserAccount(userAccountDto);
    }

    @Override
    public void updateUserAccount(UserAccountDto userAccountDto) {
        userAccountDao.updateUserAccount(userAccountDto);
    }

    @Override
    public boolean deleteUserAccount(int id) {
        return userAccountDao.deleteUserAccount(id);
    }
}
