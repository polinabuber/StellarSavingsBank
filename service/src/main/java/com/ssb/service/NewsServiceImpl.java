package com.ssb.service;


import com.ssb.data.dao.bankNews.*;
import com.ssb.data.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public List<NewsDto> getNews(int page, int pageSize) {
        try {
            return newsDao.getNews(page, pageSize);
        } catch (Exception e) {
            throw new RuntimeException("Error getting news for page " + page, e);
        }
    }

    @Override
    public NewsDto getNewsById(int id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public int countNews() {
        return newsDao.countNews();
    }

    @Override
    public int addNews(NewsDto newsDto) {
        return newsDao.addNews(newsDto);
    }

    @Override
    public void updateNews(NewsDto newsDto) {
        newsDao.updateNews(newsDto);
    }

    @Override
    public boolean deleteNews(int id) {
        try {
            return newsDao.deleteNews(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("News with id " + id + " not found", e);
        }
    }

    @Override
    public byte[] getNewsImage(int id) {
        return newsDao.getNewsImage(id);
    }
}
