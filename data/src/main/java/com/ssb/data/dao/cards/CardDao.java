package com.ssb.data.dao.cards;

import com.ssb.data.pojo.*;

import java.util.*;

public interface CardDao {
    ArrayList<Card> getAllCards();
    Card getCard(int id);
    int addCard(Card card);
    void updateCard(Card card);
    boolean deleteCard(Card card);
}
