package com.ssb.data.model;

import java.io.*;
import java.sql.*;

public class CallbackRequestDto implements Serializable {
    private Long id;
    private String name;
    private String phoneNumber;
    private Boolean isProcessed;
    private Timestamp timestamp;
    private Timestamp processedTimestamp;

    public CallbackRequestDto(Long id, String name, String phoneNumber, Boolean isProcessed, Timestamp timestamp, Timestamp processedTimestamp) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isProcessed = isProcessed;
        this.timestamp = timestamp;
        this.processedTimestamp = processedTimestamp;
    }

    public CallbackRequestDto() {
    }

    public Timestamp getProcessedTimestamp() {
        return processedTimestamp;
    }

    public void setProcessedTimestamp(Timestamp processedTimestamp) {
        this.processedTimestamp = processedTimestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }
}
