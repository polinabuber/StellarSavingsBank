package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "buy_rate")
    private BigDecimal buyRate;

    @Column(name = "sell_rate")
    private BigDecimal sellRate;

    @Column(name = "display_in_list")
    private boolean displayInList;

    @Column(name = "timestamp")
    private Timestamp timestamp;


    public boolean isDisplayInList() {
        return displayInList;
    }

    public void setDisplayInList(boolean displayInList) {
        this.displayInList = displayInList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRate that = (ExchangeRate) o;

        if (id != that.id) return false;
        if (displayInList != that.displayInList) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(buyRate, that.buyRate)) return false;
        if (!Objects.equals(sellRate, that.sellRate)) return false;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (buyRate != null ? buyRate.hashCode() : 0);
        result = 31 * result + (sellRate != null ? sellRate.hashCode() : 0);
        result = 31 * result + (displayInList ? 1 : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}

