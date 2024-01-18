package com.ssb.service;

import com.ssb.data.model.*;

import java.util.*;

public interface CallbackRequestService {

    List<CallbackRequestDto> getAllRequests(String sort);

    Long saveRequest(CallbackRequestDto callbackRequestDto);

    void updateRequest(CallbackRequestDto requestDto);
}


