package com.ssb.data.dao.deposits;

import com.ssb.data.model.*;


import java.util.*;

public interface DepositDao {
    ArrayList<DepositsDto> getAllDeposits();
    DepositsDto getDeposit(int id);
    int addDeposit(DepositsDto depositDto);
    void updateDeposit(DepositsDto depositDto);
    boolean deleteDeposit(int id);
}
