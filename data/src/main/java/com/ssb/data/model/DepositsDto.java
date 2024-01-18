package com.ssb.data.model;

public class DepositsDto {
    private int id;
    private String name;
    private double percentage;
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
}
