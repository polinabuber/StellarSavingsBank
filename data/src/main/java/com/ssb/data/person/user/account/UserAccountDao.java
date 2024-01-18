package com.ssb.data.person.user.account;

import com.ssb.data.model.*;

import java.util.*;

public interface UserAccountDao {
    List<UserAccountDto> getAllUserAccounts();
    UserAccountDto getUserAccountById(int id);
    int addUserAccount(UserAccountDto userAccountDto);
    void updateUserAccount(UserAccountDto userAccountDto);
    boolean deleteUserAccount(int id);
}
