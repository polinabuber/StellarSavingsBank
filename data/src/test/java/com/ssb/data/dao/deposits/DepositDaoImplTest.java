package com.ssb.data.dao.deposits;

import com.ssb.*;
import com.ssb.data.model.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class DepositDaoImplTest {
    @Autowired
    private DepositDao depositDao;
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
        depositDao = null;
    }

    @Test
    public void testGetAllDeposits() {
        // Given
        List<DepositsDto> expectedDeposits = new ArrayList<>();

        // When
        List<DepositsDto> actualDeposits = depositDao.getAllDeposits();

        // Then
        assertNotNull(actualDeposits);
        assertEquals(expectedDeposits.size(), actualDeposits.size());
        assertTrue(actualDeposits.isEmpty());
    }

    @Test
    public void testGetDeposit() {
        // Given
        DepositsDto expectedDeposit = new DepositsDto();
        expectedDeposit.setName("Fixed Deposit");
        expectedDeposit.setPercentage(2.5);
        expectedDeposit.setDepositDetails("Fixed deposit with a tenure of 1 year");
        int id = depositDao.addDeposit(expectedDeposit);

        // When
        DepositsDto actualDeposit = depositDao.getDeposit(id);

        // Then
        assertNotNull(actualDeposit);
        assertEquals(expectedDeposit.getName(), actualDeposit.getName());
        assertEquals(expectedDeposit.getPercentage(), actualDeposit.getPercentage(), 0.01);
        assertEquals(expectedDeposit.getDepositDetails(), actualDeposit.getDepositDetails());
        assertEquals(id, actualDeposit.getId());
    }

    @Test
    public void testAddDeposit() {
        // Given
        DepositsDto depositDto = new DepositsDto();
        depositDto.setName("Fixed Deposit");
        depositDto.setPercentage(2.5);
        depositDto.setDepositDetails("Fixed deposit with a tenure of 1 year");

        // When
        int id = depositDao.addDeposit(depositDto);

        // Then
        DepositsDto addedDeposit = depositDao.getDeposit(id);
        assertNotNull(addedDeposit);
        assertEquals(depositDto.getName(), addedDeposit.getName());
        assertEquals(depositDto.getPercentage(), addedDeposit.getPercentage(), 0.01);
        assertEquals(depositDto.getDepositDetails(), addedDeposit.getDepositDetails());
        assertEquals(id, addedDeposit.getId());
    }

    @Test
    public void testDeleteDeposit() {
        // Given
        DepositsDto depositDto = new DepositsDto();
        depositDto.setName("Fixed Deposit");
        depositDto.setPercentage(2.5);
        depositDto.setDepositDetails("Fixed deposit with a tenure of 1 year");
        int id = depositDao.addDeposit(depositDto);

        // When
        boolean result = depositDao.deleteDeposit(id);

        // Then
        assertTrue(result);
    }
}
