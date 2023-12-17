package com.ssb.data.pojo.cards;

import jakarta.persistence.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "cards_details")
public class CardDetails implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "info")
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDetails that = (CardDetails) o;

        if (id != that.id) return false;
        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
