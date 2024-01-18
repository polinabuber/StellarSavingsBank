package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.math.*;
import java.util.*;

@Entity
@Table(name = "user_deposits")
public class UserDeposits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "deposit_id", referencedColumnName = "id")
    private Deposits deposit;
    @Column(name = "balance")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Deposits getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposits deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDeposits that = (UserDeposits) o;

        if (id != that.id) return false;
        if (!Objects.equals(userAccount, that.userAccount)) return false;
        if (!Objects.equals(deposit, that.deposit)) return false;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
