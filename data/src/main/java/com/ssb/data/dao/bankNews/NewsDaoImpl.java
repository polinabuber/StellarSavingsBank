package com.ssb.data.dao.bankNews;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;


import java.sql.*;
import java.util.*;

@Repository
@Transactional
public class NewsDaoImpl implements NewsDao {
    private final SessionFactory sessionFactory;

    public NewsDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<NewsDto> getAllNews() {
        List<News> newsList = getNewsList();
        return convertToDtoList(newsList);
    }

    @Override
    public List<NewsDto> getNews(int pageNo, int pageSize) {
        List<News> news = getNewsList(pageNo, pageSize);
        if (news.isEmpty()) {
            return new ArrayList<>();
        }
        return convertToDtoList(news);
    }

    @Override
    public NewsDto getNewsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        News news = session.get(News.class, id);
        if (news != null) {
            return convertToDto(news);
        }
        return null;
    }

    @Override
    public int countNews() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(n) FROM News n", Long.class);
        Long count = query.uniqueResult();
        return count.intValue();
    }

    @Override
    public int addNews(NewsDto newsDto) {
        Session session = this.sessionFactory.getCurrentSession();
        News news = convertToEntity(newsDto);
        Integer id = (Integer) session.save(news);
        return id;
    }

    @Override
    public void updateNews(NewsDto newsDto) {
        Session session = sessionFactory.getCurrentSession();
        News news = session.get(News.class, newsDto.getId());
        if (news != null) {
            news = updateNewsEntity(news, newsDto);
            session.saveOrUpdate(news);
        }
    }

    @Override
    public boolean deleteNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        News news = session.get(News.class, id);
        if (news != null) {
            session.delete(news);
            return true;
        } else {
            throw new IllegalArgumentException("News with id " + id + " not found");
        }
    }

    @Override
    public byte[] getNewsImage(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        News news = session.get(News.class, id);
        if (news != null) {
            return news.getNewsImage();
        } else {
            throw new IllegalArgumentException("News with id " + id + " not found");
        }
    }

    private List<News> getNewsList() {
        Session session = sessionFactory.getCurrentSession();
        Query<News> query = session.createQuery("FROM News ORDER BY creationDate DESC", News.class);
        return query.list();
    }

    private List<News> getNewsList(int pageNo, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<News> query = session.createQuery("FROM News ORDER BY creationDate DESC", News.class);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    private NewsDto convertToDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setCreationDate(news.getCreationDate());
        newsDto.setNewsImage(news.getNewsImage());
        return newsDto;
    }

    private ArrayList<NewsDto> convertToDtoList(List<News> newsList) {
        ArrayList<NewsDto> newsDtoList = new ArrayList<>();
        for (News news : newsList) {
            newsDtoList.add(convertToDto(news));
        }
        return newsDtoList;
    }

    private News convertToEntity(NewsDto newsDto) {
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setCreationDate(new Timestamp(System.currentTimeMillis()));
        news.setNewsImage(newsDto.getNewsImage());
        return news;
    }

    private News updateNewsEntity(News news, NewsDto newsDto) {
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setCreationDate(new Timestamp(System.currentTimeMillis()));
        news.setNewsImage(newsDto.getNewsImage());
        return news;
    }
}


