package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "deposits")
public class Deposits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "deposit_details")
    private String depositDetails;

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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getDepositDetails() {
        return depositDetails;
    }

    public void setDepositDetails(String depositDetails) {
        this.depositDetails = depositDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposits deposits = (Deposits) o;

        if (id != deposits.id) return false;
        if (Double.compare(deposits.percentage, percentage) != 0) return false;
        if (!Objects.equals(name, deposits.name)) return false;
        return Objects.equals(depositDetails, deposits.depositDetails);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(percentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (depositDetails != null ? depositDetails.hashCode() : 0);
        return result;
    }
}
