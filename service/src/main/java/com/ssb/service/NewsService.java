package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.query.*;

import java.util.*;

public interface NewsService {
    List<NewsDto> getAllNews();
    List<NewsDto> getNews(int page, int pageSize);
    NewsDto getNewsById(int id);
    int addNews(NewsDto newsDto);
    void updateNews(NewsDto newsDto);
    boolean deleteNews(int id);
    byte[] getNewsImage(int id);
    int countNews();
}
