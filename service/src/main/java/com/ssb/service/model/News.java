package com.ssb.service.model;

public class News {
    private int id;
    private String title;
    private String text;
    private long createdAt;

    public News(int id, String title, String text, long createdAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
