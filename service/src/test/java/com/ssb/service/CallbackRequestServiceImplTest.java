package com.ssb.service;

import com.ssb.data.dao.callback.*;
import com.ssb.data.model.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfigurationTest.class)
public class CallbackRequestServiceImplTest {

    @Autowired
    CallbackRequestService callbackRequestService;

    @Autowired
    CallbackRequestDao callbackRequestDao;

    @Test
    public void testGetAllRequests() {
        // Given
        String sort = "processed";

        // When
        List<CallbackRequestDto> result = callbackRequestService.getAllRequests(sort);

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Request3", result.get(0).getName());
        assertEquals("Request2", result.get(1).getName());
        assertEquals("Request1", result.get(2).getName());
    }


    @Test
    public void testSaveRequest() {
        // Given
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setName("New Request");
        requestDto.setPhoneNumber("1234567890");
        when(callbackRequestDao.saveRequest(any(CallbackRequestDto.class))).thenReturn(1L);

        // When
        Long result = callbackRequestService.saveRequest(requestDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.longValue());
    }

    @Test
    public void testUpdateRequest() {
        // Given
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setId(1L);
        requestDto.setProcessed(true);

        // When
        callbackRequestService.updateRequest(requestDto);

        // Then
        verify(callbackRequestDao, times(1)).updateRequest(any(CallbackRequestDto.class));
    }
}
