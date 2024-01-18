package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.sql.*;
import java.util.*;

@Entity
@Table(name = "payments")
public class SsbPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private Timestamp payment_date;

    @ManyToOne
    @JoinColumn(name="user_account_id", nullable=false)
    private UserAccount userAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SsbPayment that = (SsbPayment) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(amount, that.amount)) return false;
        if (!Objects.equals(payment_date, that.payment_date)) return false;
        return Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (payment_date != null ? payment_date.hashCode() : 0);
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        return result;
    }
}
