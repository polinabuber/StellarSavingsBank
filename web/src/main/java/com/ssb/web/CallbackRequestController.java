package com.ssb.web;

import com.ssb.data.model.*;
import com.ssb.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@Controller
public class CallbackRequestController {

    private final CallbackRequestService callbackRequestService;

    @Autowired
    public CallbackRequestController(CallbackRequestService callbackRequestService) {
        this.callbackRequestService = callbackRequestService;
    }

    @GetMapping("/requestForm")
    public String showRequestForm(Model model) {
        CallbackRequestDto requestDto = new CallbackRequestDto();
        model.addAttribute("request", requestDto);
        return "requestForm";
    }

    @PostMapping("/requestForm")
    public String saveRequest(@RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber) {
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setName(name);
        requestDto.setPhoneNumber(phoneNumber);
        requestDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        callbackRequestService.saveRequest(requestDto);
        return "redirect:/requestForm";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/callback-requests")
    public String showAllRequests(@RequestParam(value = "sort", defaultValue = "date") String sort, Model model) {
        List<CallbackRequestDto> allRequests = callbackRequestService.getAllRequests(sort);
        model.addAttribute("requests", allRequests);
        return "callback-requests";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/updateRequest")
    public String updateRequest(@RequestParam("id") Long id, @RequestParam("isProcessed") Boolean isProcessed, Model model) {
        CallbackRequestDto requestDto = new CallbackRequestDto();
        requestDto.setId(id);
        requestDto.setProcessed(isProcessed);
        if (isProcessed) {
            requestDto.setProcessedTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        callbackRequestService.updateRequest(requestDto);
        List<CallbackRequestDto> allRequests = callbackRequestService.getAllRequests("date");
        model.addAttribute("requests", allRequests);
        return "redirect:/callback-requests";
    }


}
