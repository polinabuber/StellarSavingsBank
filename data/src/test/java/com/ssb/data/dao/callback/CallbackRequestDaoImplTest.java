package com.ssb.data.dao.callback;

import com.ssb.*;
import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
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
public class CallbackRequestDaoImplTest {
    @Autowired
    private CallbackRequestDao callbackRequestDao;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        conn = SSBTestDataSource.getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM callback_requests;");
        }
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        callbackRequestDao = null;
    }

    @Test
    public void testGetAllRequests() {
        // Given
        List<CallbackRequestDto> expectedRequests = new ArrayList<>();

        // When
        List<CallbackRequestDto> actualRequests = callbackRequestDao.getAllRequests();

        // Then
        assertNotNull(actualRequests);
        assertEquals(expectedRequests, actualRequests);
    }

    @Test
    public void testSaveRequest() {
        // Given
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setName("Test Name");
        requestDto.setPhoneNumber("Test Phone Number");

        // When
        callbackRequestDao.saveRequest(requestDto);

        // Then
        List<CallbackRequestDto> requests = callbackRequestDao.getAllRequests();
        assertNotNull(requests);
        assertEquals(1, requests.size());
        assertEquals(requestDto.getName(), requests.get(0).getName());
        assertEquals(requestDto.getPhoneNumber(), requests.get(0).getPhoneNumber());
        assertFalse(requests.get(0).getProcessed());
    }

    @Test
    public void testUpdateRequest() {
        // Given
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setName("Test Name");
        requestDto.setPhoneNumber("Test Phone Number");
        Long id = callbackRequestDao.saveRequest(requestDto);

        CallbackRequestDto updateDto = new CallbackRequestDto();
        updateDto.setId(id);
        updateDto.setProcessed(true);
        updateDto.setProcessedTimestamp(new Timestamp(System.currentTimeMillis()));

        // When
        callbackRequestDao.updateRequest(updateDto);

        // Then
        CallbackRequestDto updatedRequest = callbackRequestDao.getAllRequests().get(0);
        assertTrue(updatedRequest.getProcessed());
        assertNotNull(updatedRequest.getProcessedTimestamp());
    }
}


