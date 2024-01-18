<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>
<div class="container mt-4" style="font-family: Arial, sans-serif;">
        <h2 class="mb-4" style="font-weight: bold;">${news.title}</h2>

        <h6 class="card-subtitle mb-2 text-muted" style="font-size: 14px;">
            <fmt:formatDate value="${news.creationDate}" pattern="dd.MM.yyyy"/>, <fmt:formatDate value="${news.creationDate}" pattern="HH:mm"/>
        </h6>

        <p class="news-text" style="font-size: 18px; margin-bottom: 20px;">${news.text}</p>
        <img src="/news/details/${news.id}" alt="News Image" class="img-fluid" style="max-width: 100%; height: auto;">

        <a href="/ssb/news/1" class="btn btn-primary mt-3" style="background-color: #4352B5; color: white;">Back to News</a>
    </div>

<%@include file="footer.jsp"%>
