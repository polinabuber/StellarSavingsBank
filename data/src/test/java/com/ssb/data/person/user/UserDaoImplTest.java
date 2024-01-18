package com.ssb.data.person.user;


import com.ssb.*;
import com.ssb.data.model.*;
import com.ssb.data.person.role.*;
import com.ssb.data.person.user.account.*;
import com.ssb.data.pojo.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.sql.*;
import java.util.*;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserAccountDao userAccountDao;
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
        userDao = null;
        roleDao = null;
        userAccountDao = null;
    }

    @Test
    public void testGetAllUsers() {
        // Given
        List<UserDto> expectedUsers = new ArrayList<>();

        // When
        List<UserDto> actualUsers = userDao.getAllUsers();

        // Then
        assertNotNull(actualUsers);
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertTrue(actualUsers.isEmpty());
    }

    @Test
    public void testGetUserById() {
        // Given
        UserDto expectedUser = new UserDto();
        expectedUser.setFirstname("John");
        expectedUser.setLastname("Doe");
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("Admin");
        int roleId = roleDao.addRole(roleDto);
        expectedUser.setRoleId(roleId);
        int id = userDao.addUser(expectedUser);

        // When
        UserDto actualUser = userDao.getUserById(id);

        // Then
        assertNotNull(actualUser);
        assertEquals(expectedUser.getFirstname(), actualUser.getFirstname());
        assertEquals(expectedUser.getLastname(), actualUser.getLastname());
        assertEquals(expectedUser.getRoleId(), actualUser.getRoleId());
        assertEquals(id, actualUser.getId());
    }

    @Test
    public void testAddUser() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("Admin");
        int roleId = roleDao.addRole(roleDto);
        userDto.setRoleId(roleId);

        // When
        int id = userDao.addUser(userDto);

        // Then
        UserDto addedUser = userDao.getUserById(id);
        assertNotNull(addedUser);
        assertEquals(userDto.getFirstname(), addedUser.getFirstname());
        assertEquals(userDto.getLastname(), addedUser.getLastname());
        assertEquals(userDto.getRoleId(), addedUser.getRoleId());
        assertEquals(id, addedUser.getId());
    }

    @Test
    public void testDeleteUser() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("Admin");
        int roleId = roleDao.addRole(roleDto);
        userDto.setRoleId(roleId);
        int id = userDao.addUser(userDto);

        // When
        boolean result = userDao.deleteUser(id);

        // Then
        assertNull(userDao.getUserById(id));
        assertTrue(result);
    }

    @Test
    public void testGetUserByPhoneNumber() {
        // Given
        UserDto expectedUser = new UserDto();
        expectedUser.setFirstname("John");
        expectedUser.setLastname("Doe");
        expectedUser.setPhoneNumber("1234567890");
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("Admin");
        int roleId = roleDao.addRole(roleDto);
        expectedUser.setRoleId(roleId);
        userDao.addUser(expectedUser);

        // When
        User actualUser = userDao.getUserByPhoneNumber(expectedUser.getPhoneNumber());

        // Then
        assertNotNull(actualUser);
        assertEquals(expectedUser.getFirstname(), actualUser.getFirstname());
        assertEquals(expectedUser.getLastname(), actualUser.getLastname());
        assertEquals(expectedUser.getPhoneNumber(), actualUser.getPhoneNumber());
    }
}


