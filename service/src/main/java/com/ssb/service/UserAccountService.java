package com.ssb.service;

import com.ssb.data.model.*;

import java.util.*;

public interface UserAccountService {
    List<UserAccountDto> getAllUserAccounts();
    UserAccountDto getUserAccountById(int id);
    int addUserAccount(UserAccountDto userAccountDto);
    void updateUserAccount(UserAccountDto userAccountDto);
    boolean deleteUserAccount(int id);
}
