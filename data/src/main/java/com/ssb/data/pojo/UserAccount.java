package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.math.*;
import java.util.*;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "id_ssb_user", referencedColumnName = "id")
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (id != that.id) return false;
        if (!Objects.equals(balance, that.balance)) return false;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
