package com.ssb.service;

import com.ssb.data.dao.deposits.*;
import com.ssb.data.model.*;
import com.ssb.data.person.user.account.*;
import com.ssb.data.person.user.usersDeposits.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.math.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfigurationTest.class)
public class DepositServiceImplTest {

    @Autowired
    DepositService depositService;

    @Autowired
    DepositDao depositDao;

    @Autowired
    UserAccountDao userAccountDao;

    @Autowired
    UserDepositDao userDepositsDao;

    @Test
    public void testGetAllDeposits() {
        List<DepositsDto> result = depositService.getAllDeposits();

        assertNotNull(result);
    }

    @Test
    public void testAddToDeposit() {
        // Given
        int userId = 1;
        int depositId = 1;
        BigDecimal amount = BigDecimal.valueOf(1000);

        UserAccountDto userAccount = new UserAccountDto();
        userAccount.setId(userId);
        userAccount.setBalance(amount);
        when(userAccountDao.getUserAccountById(userId)).thenReturn(userAccount);

        UserDepositsDto userDeposit = new UserDepositsDto();
        userDeposit.setUserAccountId(userId);
        userDeposit.setDepositId(depositId);
        userDeposit.setBalance(BigDecimal.ZERO);
        when(userDepositsDao.getUserDeposit(userId)).thenReturn(userDeposit);

        // When
        depositService.addToDeposit(userId, depositId, amount);

        // Then
        UserAccountDto updatedUserAccount = userAccountDao.getUserAccountById(userId);
        assertEquals(0, updatedUserAccount.getBalance().compareTo(BigDecimal.ZERO));

        UserDepositsDto updatedUserDeposit = userDepositsDao.getUserDeposit(userId);
        assertEquals(0, updatedUserDeposit.getBalance().compareTo(amount));
    }



}