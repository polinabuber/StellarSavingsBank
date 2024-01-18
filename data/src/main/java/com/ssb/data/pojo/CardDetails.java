package com.ssb.data.pojo;

import jakarta.persistence.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "cards_details")
public class CardDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "info")
    private String info;

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private Card card;

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDetails that = (CardDetails) o;

        if (id != that.id) return false;
        if (!Objects.equals(info, that.info)) return false;
        return Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        return result;
    }
}
