package com.ssb.data.dao.cards;

import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
@Transactional
public class CardDaoImpl implements CardDao {
    public static final String CARDS_ALL = "FROM Card";
    private final SessionFactory sessionFactory;

    public CardDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Card> getAllCards() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Card> query = session.createQuery(CARDS_ALL, Card.class);
            return new ArrayList<>(query.list());
        } catch (HibernateException e) {
            System.out.println("Error when getting list of cards: " + e.getMessage());
            return null;
        }
    }


    @Override
    public Card getCard(int id) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.get(Card.class, id);
        } catch (Exception e) {
            System.out.println("Error when receiving card: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int addCard(Card card) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            int id = (int) session.save(card);
            return id;
        } catch (Exception e) {
            System.out.println("Error when adding card:" + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateCard(Card card) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(card);
        } catch (RuntimeException e) {
            System.out.println("Error when updating card: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteCard(Card card) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(card);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Error when deleting card: " + e.getMessage());
            return false;
        }
    }
}
