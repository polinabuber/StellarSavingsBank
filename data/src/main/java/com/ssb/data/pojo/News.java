package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.sql.*;
import java.util.*;

@Entity
@Table(name = "ssb_news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "news_image")
    @Lob
    private byte[] newsImage;

    public byte[] getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(byte[] newsImage) {
        this.newsImage = newsImage;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (!Objects.equals(title, news.title)) return false;
        if (!Objects.equals(text, news.text)) return false;
        if (!Objects.equals(creationDate, news.creationDate)) return false;
        return Arrays.equals(newsImage, news.newsImage);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(newsImage);
        return result;
    }
}


