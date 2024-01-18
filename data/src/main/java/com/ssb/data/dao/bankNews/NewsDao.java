package com.ssb.data.dao.bankNews;

import com.ssb.data.model.*;
import java.util.*;

public interface NewsDao {
    List<NewsDto> getAllNews();
    List<NewsDto> getNews(int pageNo, int pageSize);
    NewsDto getNewsById(int id);
    int addNews(NewsDto newsDto);
    void updateNews(NewsDto newsDto);
    boolean deleteNews(int id);
    byte[] getNewsImage(int id);
    int countNews();
}
