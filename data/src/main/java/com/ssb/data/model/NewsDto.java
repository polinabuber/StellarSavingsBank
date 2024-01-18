package com.ssb.data.model;

import java.io.*;
import java.sql.*;

public final class NewsDto implements Serializable {

    private int id;
    private String title;
    private String text;
    private Timestamp creationDate;
    private byte[] newsImage;

    public NewsDto(int id, String title, String text, Timestamp creationDate, byte[] newsImage) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.newsImage = newsImage;
    }

    public byte[] getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(byte[] newsImage) {
        this.newsImage = newsImage;
    }

    public NewsDto() {

    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}