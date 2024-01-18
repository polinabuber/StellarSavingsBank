package com.ssb.service;

import com.ssb.data.model.*;

import java.util.*;

public interface UserDepositService {
    List<UserDepositsDto> getAllUserDeposits();
    UserDepositsDto getUserDeposit(int id);
    int addUserDeposit(UserDepositsDto userDepositsDto);
    void updateUserDeposit(UserDepositsDto userDepositsDto);
    boolean deleteUserDeposit(UserDepositsDto userDepositsDto);
}

