package com.ssb.data.person.role;

import com.ssb.*;
import com.ssb.data.model.*;
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
public class RoleDaoImplTest {
    @Autowired
    private RoleDao roleDao;
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
        roleDao = null;
    }

    @Test
    public void testGetAllRoles() {
        // Given
        List<RoleDto> expectedRoles = new ArrayList<>();

        // When
        List<RoleDto> actualRoles = roleDao.getAllRoles();

        // Then
        assertNotNull(actualRoles);
        assertEquals(expectedRoles.size(), actualRoles.size());
    }
    @Test
    public void testDeleteRole() {
        // Given
        RoleDto role = new RoleDto();
        role.setRole("Test Role");
        int id = roleDao.addRole(role);

        // When
        boolean result = roleDao.deleteRole(id);

        // Then
        assertNull(roleDao.getRoleById(id));
    }
}

