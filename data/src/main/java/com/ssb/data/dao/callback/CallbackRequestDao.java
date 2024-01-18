package com.ssb.data.dao.callback;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;

import java.util.*;

public interface CallbackRequestDao {

    List<CallbackRequestDto> getAllRequests();

    Long saveRequest(CallbackRequestDto requestDto);

    void updateRequest(CallbackRequestDto requestDto);
}
