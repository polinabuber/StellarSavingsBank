//package com.ssb.data.dao.cards;
//
//import com.ssb.*;
//import com.ssb.data.pojo.Card;
//import com.ssb.data.pojo.CardDetails;
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
//public class CardDetailsDaoImplTest {
//    @Autowired
//    private CardDetailsDao cardDetailsDao;
//    @Autowired
//    private CardDao cardDao;
//    Connection conn;
//
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
//        Statement statement = conn.createStatement();
//        statement.executeUpdate("DELETE FROM cards_details WHERE id_card IN (SELECT id FROM cards);");
//        statement.executeUpdate("DELETE FROM cards;");
//        conn.close();
//        cardDetailsDao = null;
//        cardDao = null;
//    }
//
//    @Test
//    public void testGetAllCardDetails() {
//        // Given
//        List<CardDetails> expectedCardDetails = new ArrayList<>();
//
//        // When
//        List<CardDetails> actualCardDetails = cardDetailsDao.getAllCardDetails();
//
//        // Then
//        assertNotNull(actualCardDetails);
//        assertEquals(expectedCardDetails, actualCardDetails);
//    }
//
//    @Test
//    public void testGetCardDetails() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//        int cardId = cardDao.addCard(card);
//
//        CardDetails expectedCardDetails = new CardDetails();
//        expectedCardDetails.setInfo("Test Info");
//        expectedCardDetails.setCard(cardDao.getCard(cardId));
//        int id = cardDetailsDao.addCardDetails(expectedCardDetails);
//
//        // When
//        CardDetails actualCardDetails = cardDetailsDao.getCardDetails(id);
//
//        // Then
//        assertNotNull(actualCardDetails);
//        assertEquals(expectedCardDetails.getInfo(), actualCardDetails.getInfo());
//    }
//
//    @Test
//    public void testAddCardDetails() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//        int cardId = cardDao.addCard(card);
//
//        CardDetails cardDetails = new CardDetails();
//        cardDetails.setInfo("Test Info");
//        cardDetails.setCard(cardDao.getCard(cardId));
//
//        // When
//        int id = cardDetailsDao.addCardDetails(cardDetails);
//
//        // Then
//        CardDetails addedCardDetails = cardDetailsDao.getCardDetails(id);
//        assertNotNull(addedCardDetails);
//        assertEquals(cardDetails.getInfo(), addedCardDetails.getInfo());
//    }
//
//    @Test
//    public void testUpdateCardDetails() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//        int cardId = cardDao.addCard(card);
//
//        CardDetails cardDetails = new CardDetails();
//        cardDetails.setInfo("Old Info");
//        cardDetails.setCard(cardDao.getCard(cardId));
//        int id = cardDetailsDao.addCardDetails(cardDetails);
//
//        cardDetails.setInfo("New Info");
//
//        // When
//        cardDetailsDao.updateCardDetails(cardDetails);
//
//        // Then
//        CardDetails updatedCardDetails = cardDetailsDao.getCardDetails(id);
//        assertEquals(cardDetails.getInfo(), updatedCardDetails.getInfo());
//    }
//
//    @Test
//    public void testDeleteCardDetails() {
//        // Given
//        Card card = new Card();
//        card.setName("Test Card");
//        int cardId = cardDao.addCard(card);
//
//        CardDetails cardDetails = new CardDetails();
//        cardDetails.setInfo("Test Info");
//        cardDetails.setCard(cardDao.getCard(cardId));
//        int id = cardDetailsDao.addCardDetails(cardDetails);
//
//        // When
//        boolean result = cardDetailsDao.deleteCardDetails(cardDetails);
//
//        // Then
//        assertNull(cardDetailsDao.getCardDetails(id));
//    }
//}
