package com.ssb.service;

import com.ssb.data.model.*;

import java.math.*;
import java.util.*;

public interface DepositService {
    List<DepositsDto> getAllDeposits();
    DepositsDto getDeposit(int id);
    int addDeposit(DepositsDto depositDto);
    void updateDeposit(DepositsDto depositDto);
    boolean deleteDeposit(int id);
    void addToDeposit(int userId, int depositId, BigDecimal amount);
    void calculateInterest();
}


