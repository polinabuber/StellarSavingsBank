package com.ssb.web;

import com.ssb.data.model.*;
import com.ssb.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {

    private final ExchangeRateService exchangeRateService;
    private final NewsService newsService;


    @Autowired
    public HomeController(ExchangeRateService exchangeRateService, NewsService newsService) {
        this.exchangeRateService = exchangeRateService;
        this.newsService = newsService;

    }

    @GetMapping({"/", "/index.jsp", "/index.html"})
    public String getHomePage(Model model) {
        List<ExchangeRateDto> exchangeRateList = exchangeRateService.getAllExchangeRates();
        model.addAttribute("exchangeRateList", exchangeRateList);

        List<NewsDto> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);

        return "index";
    }



}

