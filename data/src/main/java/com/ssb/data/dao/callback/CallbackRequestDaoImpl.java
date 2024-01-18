package com.ssb.data.dao.callback;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Repository
@Transactional
public class CallbackRequestDaoImpl implements CallbackRequestDao {
    private final SessionFactory sessionFactory;

    public CallbackRequestDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CallbackRequestDto> getAllRequests() {
        Session session = sessionFactory.getCurrentSession();
        List<CallbackRequest> callbackRequests = session.createQuery("from CallbackRequest", CallbackRequest.class).getResultList();
        return callbackRequests.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private CallbackRequestDto convertToDto(CallbackRequest callbackRequest) {
        CallbackRequestDto dto = new CallbackRequestDto();
        dto.setId(callbackRequest.getId());
        dto.setName(callbackRequest.getName());
        dto.setPhoneNumber(callbackRequest.getPhoneNumber());
        dto.setProcessed(callbackRequest.getProcessed());
        dto.setTimestamp(callbackRequest.getTimestamp());
        dto.setProcessedTimestamp(callbackRequest.getProcessedTimestamp());
        return dto;
    }

    @Override
    public Long saveRequest(CallbackRequestDto requestDto) {
        Session session = sessionFactory.getCurrentSession();
        CallbackRequest request = new CallbackRequest();
        request.setName(requestDto.getName());
        request.setPhoneNumber(requestDto.getPhoneNumber());
        request.setProcessed(false);
        request.setTimestamp(requestDto.getTimestamp());
        Long id = (Long) session.save(request);
        return id;
    }

    @Override
    public void updateRequest(CallbackRequestDto requestDto) {
        Session session = sessionFactory.getCurrentSession();
        CallbackRequest request = session.get(CallbackRequest.class, requestDto.getId().intValue());
        request.setProcessed(requestDto.getProcessed());
        if (requestDto.getProcessed()) {
            request.setProcessedTimestamp(requestDto.getProcessedTimestamp());
        }
        session.saveOrUpdate(request);
    }
}

