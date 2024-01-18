package com.ssb.data.person.user.account;


import com.ssb.*;
import com.ssb.data.model.*;
import com.ssb.data.person.user.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.math.*;
import java.sql.*;
import java.util.*;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class UserAccountDaoImplTest {
    @Autowired
    private UserAccountDao userAccountDao;
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
        userAccountDao = null;
        userDao = null;
    }

    @Test
    public void testGetAllUserAccounts() {
        // Given
        List<UserAccountDto> expectedUserAccounts = new ArrayList<>();

        // When
        List<UserAccountDto> actualUserAccounts = userAccountDao.getAllUserAccounts();

        // Then
        assertNotNull(actualUserAccounts);
        assertEquals(expectedUserAccounts.size(), actualUserAccounts.size());
        assertTrue(actualUserAccounts.isEmpty());
    }

    @Test
    public void testGetUserAccountById() {
        // Given
        UserAccountDto expectedUserAccount = new UserAccountDto();
        expectedUserAccount.setBalance(new BigDecimal("100.00"));
        UserDto userDto = new UserDto();
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        int userId = userDao.addUser(userDto);
        expectedUserAccount.setUserId(userId);
        int id = userAccountDao.addUserAccount(expectedUserAccount);

        // When
        UserAccountDto actualUserAccount = userAccountDao.getUserAccountById(id);

        // Then
        assertNotNull(actualUserAccount);
        assertEquals(expectedUserAccount.getBalance(), actualUserAccount.getBalance());
        assertEquals(expectedUserAccount.getUserId(), actualUserAccount.getUserId());
        assertEquals(id, actualUserAccount.getId());
    }

    @Test
    public void testAddUserAccount() {
        // Given
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setBalance(new BigDecimal("100.00"));
        UserDto userDto = new UserDto();
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        int userId = userDao.addUser(userDto);
        userAccountDto.setUserId(userId);

        // When
        int id = userAccountDao.addUserAccount(userAccountDto);

        // Then
        UserAccountDto addedUserAccount = userAccountDao.getUserAccountById(id);
        assertNotNull(addedUserAccount);
        assertEquals(userAccountDto.getBalance(), addedUserAccount.getBalance());
        assertEquals(userAccountDto.getUserId(), addedUserAccount.getUserId());
        assertEquals(id, addedUserAccount.getId());
    }

    @Test
    public void testDeleteUserAccount() {
        // Given
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setBalance(new BigDecimal("100.00"));
        UserDto userDto = new UserDto();
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        int userId = userDao.addUser(userDto);
        userAccountDto.setUserId(userId);
        int id = userAccountDao.addUserAccount(userAccountDto);

        // When
        boolean result = userAccountDao.deleteUserAccount(id);

        // Then
        assertNull(userAccountDao.getUserAccountById(id));
        assertTrue(result);
    }
}


