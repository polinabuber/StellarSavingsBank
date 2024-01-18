package com.ssb.web;

import com.ssb.data.model.*;
import com.ssb.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import java.io.*;
import java.util.*;
@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String getAllNews(Model model) {
        List<NewsDto> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "news";
    }

    @GetMapping("/redirect")
    public String redirectToFirstPage() {
        return "redirect:/news/1";
    }

    @GetMapping("/{page}")
    public String getNews(@PathVariable("page") int page, Model model) {
        int pageSize = 3;
        List<NewsDto> news = newsService.getNews(page, pageSize);
        model.addAttribute("news", news);
        int totalNews = newsService.countNews();
        int totalPages = (int) Math.ceil((double) totalNews / pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "news";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getNewsDetails(@PathVariable("id") int id) {
        NewsDto news = newsService.getNewsById(id);
        ModelAndView modelAndView = new ModelAndView("newsDetails");
        modelAndView.addObject("news", news);
        return modelAndView;
    }

    @GetMapping(value = "/details/{id}", produces = "image/jpeg")
    public @ResponseBody byte[] getNewsImage(@PathVariable("id") int id) {
        return newsService.getNewsImage(id);
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String showAddNewsForm(Model model) {
        model.addAttribute("news", new NewsDto());
        return "addNews";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String addNews(@ModelAttribute NewsDto newsDto, @RequestParam("image") MultipartFile image) throws IOException {
        byte[] bytes = image.getBytes();
        newsDto.setNewsImage(bytes);
        newsService.addNews(newsDto);
        return "redirect:/news/1";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/delete")
    public String deleteNews(@RequestParam("id") int id) {
       newsService.deleteNews(id);
       return "redirect:/news/1";
    }
}



