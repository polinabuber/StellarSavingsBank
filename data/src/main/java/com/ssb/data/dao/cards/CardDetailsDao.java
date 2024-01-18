package com.ssb.data.dao.cards;


import com.ssb.data.pojo.*;

import java.util.*;

public interface CardDetailsDao {
   ArrayList<CardDetails> getAllCardDetails();
    CardDetails getCardDetails(int id);
    int addCardDetails(CardDetails cardDetails);
    void updateCardDetails(CardDetails cardDetails);
    boolean deleteCardDetails(CardDetails cardDetails);
}
