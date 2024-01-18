//package com.ssb.data.dao.cards;
//
//import com.ssb.*;
//import com.ssb.data.pojo.Card;
//import org.junit.*;
//import org.junit.runner.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.test.context.*;
//import org.springframework.test.context.junit4.*;
//
//import java.sql.*;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestDataConfiguration.class)
//@TestPropertySource(value = "classpath:test.liquibase.properties")
//public class CardDaoImplTest {
//    @Autowired
//    private CardDao cardDao;
//    Connection conn;
//    @Before
//    public void setUp() throws Exception {
//        conn = SSBTestDataSource.getConnection();
//        if (conn != null) {
//            Statement statement = conn.createStatement();
//            statement.executeUpdate("DELETE FROM cards_details WHERE id_card IN (SELECT id FROM cards);");
//            statement.executeUpdate("DELETE FROM cards;");
//        }
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//        cardDao = null;
//    }
//
//
//    @Test
//    public void testGetAllCards() {
//        // Given
//        List<Card> expectedCards = new ArrayList<>();
//
//        // When
//        List<Card> actualCards = cardDao.getAllCards();
//
//        // Then
//        assertNotNull(actualCards);
//        assertEquals(expectedCards, actualCards);
//    }
//
//    @Test
//    public void testGetCard() {
//        // Given
//        Card expectedCard = new Card();
//        expectedCard.setName("Test Card");
//        int id = cardDao.addCard(expectedCard);
//
//        // When
//        Card actualCard = cardDao.getCard(id);
//
//        // Then
//        assertNotNull(actualCard);
//        assertEquals(expectedCard.getName(), actualCard.getName());
//    }
//
//
//    @Test
//    public void testAddCard() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//
//        // When
//        int id = cardDao.addCard(card);
//
//        // Then
//        Card addedCard = cardDao.getCard(id);
//        assertNotNull(addedCard);
//        assertEquals(card.getName(), addedCard.getName());
//    }
//
//
//    @Test
//    public void testUpdateCard() {
//        // Given
//        Card card = new Card();
//        card.setName("Old Name");
//        int id = cardDao.addCard(card);
//        card.setId(id);
//        card.setName("New Name");
//
//        // When
//        cardDao.updateCard(card);
//
//        // Then
//        Card updatedCard = cardDao.getCard(id);
//        assertEquals(card.getName(), updatedCard.getName());
//    }
//
//
//    @Test
//    public void testDeleteCard() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//        int id = cardDao.addCard(card);
//
//        // When
//        boolean result = cardDao.deleteCard(card);
//
//        // Then
//        assertNull(cardDao.getCard(id));
//    }
//}
