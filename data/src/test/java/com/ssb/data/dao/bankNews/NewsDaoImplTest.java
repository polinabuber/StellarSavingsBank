package com.ssb.data.dao.bankNews;

import com.ssb.*;
import com.ssb.data.model.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class NewsDaoImplTest {
    @Autowired
    private NewsDao newsDao;
    Connection conn;
    @Before
    public void setUp() throws Exception {
        conn = SSBTestDataSource.getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM ssb_news;");
        }
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        newsDao = null;
    }

    @Test
    public void testGetAllNews() {
        // Given
        List<NewsDto> expectedNews = new ArrayList<>();

        // When
        List<NewsDto> actualNews = newsDao.getAllNews();

        // Then
        assertNotNull(actualNews);
        assertEquals(expectedNews.size(), actualNews.size());
    }

    @Test
    public void testGetNews() {
        // Given
        NewsDto expectedNews = new NewsDto();
        expectedNews.setTitle("Test News");
        expectedNews.setText("This is a test news.");
        int id = newsDao.addNews(expectedNews);

        // When
        NewsDto actualNews = newsDao.getNewsById(id);

        // Then
        assertNotNull(actualNews);
        assertEquals(expectedNews.getTitle(), actualNews.getTitle());
        assertEquals(expectedNews.getText(), actualNews.getText());
    }

    @Test
    public void testAddNews() {
        // Given
        NewsDto news = new NewsDto();
        news.setTitle("Test News");
        news.setText("This is a test news.");

        // When
        int id = newsDao.addNews(news);

        // Then
        NewsDto addedNews = newsDao.getNewsById(id);
        assertNotNull(addedNews);
        assertEquals(news.getTitle(), addedNews.getTitle());
        assertEquals(news.getText(), addedNews.getText());
    }

    @Test
    public void testDeleteNews() {
        // Given
        NewsDto news = new NewsDto();
        news.setTitle("Test News");
        news.setText("This is a test news.");
        int id = newsDao.addNews(news);

        // When
        boolean result = newsDao.deleteNews(id);

        // Then
        assertNull(newsDao.getNewsById(id));
    }
}








