package com.ssb.service;

import com.ssb.data.dao.callback.*;
import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;
import java.util.stream.*;

@Service
public class CallbackRequestServiceImpl implements CallbackRequestService {

    private final CallbackRequestDao callbackRequestDao;

    @Autowired
    public CallbackRequestServiceImpl(CallbackRequestDao callbackRequestDao) {
        this.callbackRequestDao = callbackRequestDao;
    }

    @Override
    public List<CallbackRequestDto> getAllRequests(String sort) {
        Stream<CallbackRequestDto> stream = callbackRequestDao.getAllRequests().stream();
        if ("processed".equals(sort)) {
            return stream
                    .sorted(Comparator.comparing(CallbackRequestDto::getProcessed)
                            .thenComparing(CallbackRequestDto::getName)
                            .reversed())
                    .collect(Collectors.toList());
        } else {
            return stream
                    .sorted(Comparator.comparing(CallbackRequestDto::getTimestamp)
                            .thenComparing(CallbackRequestDto::getName)
                            .reversed())
                    .collect(Collectors.toList());
        }
    }


    @Override
    public Long saveRequest(CallbackRequestDto requestDto) {
        requestDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return callbackRequestDao.saveRequest(requestDto);
    }

    @Override
    public void updateRequest(CallbackRequestDto requestDto) {
        if (requestDto.getProcessed()) {
            requestDto.setProcessedTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        callbackRequestDao.updateRequest(requestDto);
    }
}



