package com.ssb.data.dao.cards;

import com.ssb.data.pojo.CardDetails;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class CardDetailsDaoImpl implements CardDetailsDao {
    public static final String CARD_DETAILS_ALL = "FROM CardDetails";
    private final SessionFactory sessionFactory;

    public CardDetailsDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<CardDetails> getAllCardDetails() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<CardDetails> query = session.createQuery(CARD_DETAILS_ALL, CardDetails.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error when getting list of card details: " + e.getMessage());
            return null;
        }
    }

    @Override
    public CardDetails getCardDetails(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(CardDetails.class, id);
        } catch (HibernateException e) {
            System.out.println("Error when receiving expense: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addCardDetails(CardDetails cardDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return (int) session.save(cardDetails);
        } catch (RuntimeException e) {
            System.out.println("Error when adding card details:" + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateCardDetails(CardDetails cardDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(cardDetails);
        } catch (RuntimeException e) {
            System.out.println("Error when updating card details: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteCardDetails(CardDetails cardDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(cardDetails);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Error when deleting card details: " + e.getMessage());
            return false;
        }
    }
}
