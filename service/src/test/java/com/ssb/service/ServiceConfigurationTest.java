package com.ssb.service;

import com.ssb.data.dao.callback.*;
import com.ssb.data.dao.deposits.*;
import com.ssb.data.model.*;
import com.ssb.data.person.user.account.*;
import com.ssb.data.person.user.usersDeposits.*;
import org.mockito.*;
import org.springframework.context.annotation.*;

import java.sql.*;
import java.util.*;

import static org.mockito.Mockito.when;

@Configuration
@Import(ServiceConfiguration.class)
public class ServiceConfigurationTest {
    @Bean
    @Primary
    public CallbackRequestDao callbackRequestDao() {
        CallbackRequestDao mock = Mockito.mock(CallbackRequestDao.class);
        when(mock.getAllRequests()).thenReturn(List.of(
                new CallbackRequestDto(1L, "Request3", "1234567890", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())),
                new CallbackRequestDto(2L, "Request2", "1234567890", false, new Timestamp(System.currentTimeMillis()), null),
                new CallbackRequestDto(3L, "Request1", "1234567890", false, new Timestamp(System.currentTimeMillis()), null)
        ));
        return mock;
    }
    @Bean
    @Primary
    public DepositDao depositDao() {
        return Mockito.mock(DepositDao.class);
    }

    @Bean
    @Primary
    public UserAccountDao userAccountDao() {
        return Mockito.mock(UserAccountDao.class);
    }

    @Bean
    @Primary
    public UserDepositDao userDepositsDao() {
        return Mockito.mock(UserDepositDao.class);
    }



}
