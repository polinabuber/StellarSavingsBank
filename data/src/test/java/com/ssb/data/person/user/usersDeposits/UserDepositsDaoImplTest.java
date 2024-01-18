package com.ssb.data.person.user.usersDeposits;

import com.ssb.*;
import com.ssb.data.dao.deposits.*;
import com.ssb.data.model.*;
import com.ssb.data.person.user.*;
import com.ssb.data.person.user.account.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.math.*;
import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class UserDepositsDaoImplTest {
    @Autowired
    private UserDepositDao userDepositsDao;
    @Autowired
    private UserAccountDao userAccountDao;
    @Autowired
    private DepositDao depositDao;
    @Autowired
    private UserDao userDao;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        conn = SSBTestDataSource.getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM user_deposits;");
            statement.executeUpdate("DELETE FROM deposits;");
            statement.executeUpdate("DELETE FROM user_account;");
            statement.executeUpdate("DELETE FROM ssb_user;");
            statement.executeUpdate("DELETE FROM ssb_role;");
        }
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        userDepositsDao = null;
        userAccountDao = null;
        depositDao = null;
    }

    @Test
    public void testGetAllUserDeposits() {
        // Given
        int userId = addTestUser();
        int userAccountId = addTestAccount(userId);
        int depositId = addTestDeposit();

        UserDepositsDto userDepositsDto1 = new UserDepositsDto();
        userDepositsDto1.setUserAccountId(userAccountId);
        userDepositsDto1.setDepositId(depositId);
        userDepositsDao.addUserDeposit(userDepositsDto1);

        UserDepositsDto userDepositsDto2 = new UserDepositsDto();
        userDepositsDto2.setUserAccountId(userAccountId);
        userDepositsDto2.setDepositId(depositId);
        userDepositsDao.addUserDeposit(userDepositsDto2);

        // When
        List<UserDepositsDto> actualUserDeposits = userDepositsDao.getAllUserDeposits();

        // Then
        assertNotNull(actualUserDeposits);
        assertEquals(2, actualUserDeposits.size());
    }


    @Test
    public void testGetUserDeposit() {
        // Given
        int userId = addTestUser();

        int userAccountId = addTestAccount(userId);

        int depositId = addTestDeposit();

        UserDepositsDto expectedUserDeposit = new UserDepositsDto();
        expectedUserDeposit.setUserAccountId(userAccountId);
        expectedUserDeposit.setDepositId(depositId);
        int id = userDepositsDao.addUserDeposit(expectedUserDeposit);

        // When
        UserDepositsDto actualUserDeposit = userDepositsDao.getUserDeposit(id);

        // Then
        assertNotNull(actualUserDeposit);
        assertEquals(expectedUserDeposit.getUserAccountId(), actualUserDeposit.getUserAccountId());
        assertEquals(expectedUserDeposit.getDepositId(), actualUserDeposit.getDepositId());
        assertEquals(id, actualUserDeposit.getId());
    }

    private int addTestDeposit() {
        DepositsDto depositDto = new DepositsDto();
        depositDto.setName("Fixed Deposit");
        depositDto.setPercentage(2.5);
        depositDto.setDepositDetails("Fixed deposit with a tenure of 1 year");
        int depositId = depositDao.addDeposit(depositDto);
        return depositId;
    }

    private int addTestUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstname("Test");
        userDto.setLastname("User");
        userDto.setPhoneNumber("1234567890");
        userDto.setPassword("password");
        userDto.setRoleId(1);
        int userId = userDao.addUser(userDto);
        return userId;
    }

    @Test
    public void testAddUserDeposit() {
        // Given
        int userId = addTestUser();

        int userAccountId = addTestAccount(userId);

        int depositId = addTestDeposit();

        UserDepositsDto userDepositsDto = new UserDepositsDto();
        userDepositsDto.setUserAccountId(userAccountId);
        userDepositsDto.setDepositId(depositId);
        userDepositsDto.setBalance(new BigDecimal("100.00"));

        // When
        int id = userDepositsDao.addUserDeposit(userDepositsDto);

        // Then
        UserDepositsDto addedUserDeposit = userDepositsDao.getUserDeposit(id);
        assertNotNull(addedUserDeposit);
        assertEquals(userDepositsDto.getUserAccountId(), addedUserDeposit.getUserAccountId());
        assertEquals(userDepositsDto.getDepositId(), addedUserDeposit.getDepositId());
        assertEquals(id, addedUserDeposit.getId());
    }

    private int addTestAccount(int userId) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setUserId(userId);
        userAccountDto.setBalance(new BigDecimal("100.00"));
        int userAccountId = userAccountDao.addUserAccount(userAccountDto);
        return userAccountId;
    }

    @Test
    public void testDeleteUserDeposit() {
        // Given
        int userId = addTestUser();

        int userAccountId = addTestAccount(userId);

        int depositId = addTestDeposit();

        UserDepositsDto userDepositsDto = new UserDepositsDto();
        userDepositsDto.setUserAccountId(userAccountId);
        userDepositsDto.setDepositId(depositId);
        userDepositsDto.setBalance(new BigDecimal("100.00"));
        int id = userDepositsDao.addUserDeposit(userDepositsDto);

        // When
        boolean result = userDepositsDao.deleteUserDeposit(userDepositsDto);

        // Then
        assertTrue(result);
    }

}