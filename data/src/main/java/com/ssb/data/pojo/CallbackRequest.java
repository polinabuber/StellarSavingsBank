package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.sql.*;
import java.util.*;

@Entity
@Table(name = "callback_requests")
public class CallbackRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_processed")
    private Boolean isProcessed;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "processed_timestamp")
    private Timestamp processedTimestamp;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallbackRequest that = (CallbackRequest) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(phoneNumber, that.phoneNumber)) return false;
        if (!Objects.equals(isProcessed, that.isProcessed)) return false;
        if (!Objects.equals(timestamp, that.timestamp)) return false;
        return Objects.equals(processedTimestamp, that.processedTimestamp);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (isProcessed != null ? isProcessed.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (processedTimestamp != null ? processedTimestamp.hashCode() : 0);
        return result;
    }
}

