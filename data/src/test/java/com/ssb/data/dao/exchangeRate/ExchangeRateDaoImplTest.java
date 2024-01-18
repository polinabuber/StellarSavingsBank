package com.ssb.data.dao.exchangeRate;

import com.ssb.*;
import com.ssb.data.model.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class ExchangeRateDaoImplTest {
    @Autowired
    private ExchangeRateDao exchangeRateDao;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        conn = SSBTestDataSource.getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM exchange_rates;");
        }
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        exchangeRateDao = null;
    }

    @Test
    public void testGetAllExchangeRates() {
        // Given
        List<ExchangeRateDto> expectedRates = new ArrayList<>();

        // When
        List<ExchangeRateDto> actualRates = exchangeRateDao.getAllExchangeRates();

        // Then
        assertNotNull(actualRates);
        assertEquals(expectedRates.size(), actualRates.size());
    }

    @Test
    public void testGetExchangeRate() {
        // Given
        ExchangeRateDto expectedRate = new ExchangeRateDto();
        expectedRate.setName("Test Rate");
        expectedRate.setBuyRate(new BigDecimal("1.0000"));
        expectedRate.setSellRate(new BigDecimal("1.2000"));
        int id = exchangeRateDao.addExchangeRate(expectedRate);

        // When
        ExchangeRateDto actualRate = exchangeRateDao.getExchangeRate(id);

        // Then
        assertNotNull(actualRate);
        assertEquals(expectedRate.getName(), actualRate.getName());
        assertEquals(0, expectedRate.getBuyRate().compareTo(actualRate.getBuyRate()));
        assertEquals(0, expectedRate.getSellRate().compareTo(actualRate.getSellRate()));
    }

    @Test
    public void testAddExchangeRate() {
        // Given
        ExchangeRateDto rate = new ExchangeRateDto();
        rate.setName("Test Rate");
        rate.setBuyRate(new BigDecimal("1.0000"));
        rate.setSellRate(new BigDecimal("1.2000"));

        // When
        int id = exchangeRateDao.addExchangeRate(rate);

        // Then
        ExchangeRateDto addedRate = exchangeRateDao.getExchangeRate(id);
        assertNotNull(addedRate);
        assertEquals(rate.getName(), addedRate.getName());
        assertEquals(0, rate.getBuyRate().compareTo(addedRate.getBuyRate()));
        assertEquals(0, rate.getSellRate().compareTo(addedRate.getSellRate()));
    }

    @Test
    public void testUpdateExchangeRate() {
        // Given
        ExchangeRateDto rate = new ExchangeRateDto();
        rate.setName("Old Name");
        rate.setBuyRate(new BigDecimal("1.0000"));
        rate.setSellRate(new BigDecimal("1.2000"));
        int id = exchangeRateDao.addExchangeRate(rate);
        rate.setId(id);
        rate.setName("New Name");
        rate.setBuyRate(new BigDecimal("2.0000"));
        rate.setSellRate(new BigDecimal("2.2200"));

        // When
        exchangeRateDao.updateExchangeRate(rate);

        // Then
        ExchangeRateDto updatedRate = exchangeRateDao.getExchangeRate(id);
        assertEquals(rate.getName(), updatedRate.getName());
        assertEquals(0, rate.getBuyRate().compareTo(updatedRate.getBuyRate()));
        assertEquals(0, rate.getSellRate().compareTo(updatedRate.getSellRate()));
    }

    @Test
    public void testDeleteExchangeRate() {
        // Given
        ExchangeRateDto rate = new ExchangeRateDto();
        rate.setName("Test Rate");
        rate.setBuyRate(new BigDecimal("1.0000"));
        rate.setSellRate(new BigDecimal("1.2000"));
        int id = exchangeRateDao.addExchangeRate(rate);

        // When
        boolean result = exchangeRateDao.deleteExchangeRate(rate);

        // Then
        assertNull(exchangeRateDao.getExchangeRate(id));
    }
}

