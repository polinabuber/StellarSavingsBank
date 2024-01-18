package com.ssb.data.person.user.usersDeposits;


import com.ssb.data.model.*;
import com.ssb.data.pojo.*;

import java.util.*;

public interface UserDepositDao {
    ArrayList<UserDepositsDto> getAllUserDeposits();
    UserDepositsDto getUserDeposit(int id);
    int addUserDeposit(UserDepositsDto userDepositsDto);
    void updateUserDeposit(UserDepositsDto userDepositsDto);
    boolean deleteUserDeposit(UserDepositsDto userDepositsDto);
}
