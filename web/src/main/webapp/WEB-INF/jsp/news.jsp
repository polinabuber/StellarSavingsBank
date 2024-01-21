<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp"%>

<div class="container mt-4">
    <h2 class="mb-4" style="font-weight: bold; color: black;">News</h2>
   <sec:authorize access="hasRole('ROLE_ADMIN')">
       <button class="btn btn-primary mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addNewsForm" aria-expanded="false" aria-controls="addNewsForm" style="background-color: #4352B5; color: white;">
           New news
       </button>
   </sec:authorize>

    <div class="collapse mb-4" id="addNewsForm">
        <div class="card card-body">
            <form action="/ssb/news/add" method="post" enctype="multipart/form-data">
                <input type="text" name="title" placeholder="Title" required class="form-control mb-2">
                <textarea name="text" placeholder="Text" required class="form-control mb-2"></textarea>
                <input type="file" name="image" accept="image/*" class="form-control mb-2">
                <button type="submit" class="btn btn-primary mt-2" style="background-color: #4352B5; color: white;">Add News</button>
            </form>
        </div>
    </div>


   <c:forEach var="news" items="${news}">
      <div class="card mb-3" style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);">
          <div class="card-body">
              <h5 class="card-title" style="font-weight: bold;">${news.title}</h5>
               <h6 class="card-subtitle mb-2 text-muted" style="font-size: 14px;">
                      <fmt:formatDate value="${news.creationDate}" pattern="dd.MM.yyyy"/>,  <fmt:formatDate value="${news.creationDate}" pattern="HH:mm"/>
                  </h6>
              <img src="/ssb/news/details/${news.id}" alt="News Image" class="img-fluid" style="max-width: 100%; height: auto;">
              <div class="d-flex align-items-center">
                  <a href="/ssb/news/details/${news.id}" class="btn btn-primary mr-2" style="background-color: #4352B5; color: white;">Read more</a>
                  <sec:authorize access="hasRole('ROLE_ADMIN')">
                      <form action="/ssb/news/delete" method="post" enctype="multipart/form-data">
                          <input type="hidden" name="id" value="${news.id}">
                          <button type="submit" class="btn btn-danger" style="background-color: #dc3545; border-color: #dc3545;">Delete</button>
                      </form>
                  </sec:authorize>
              </div>
          </div>
      </div>
   </c:forEach>



<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center" style="margin:20px 0;">
        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
            <a class="page-link" href="/ssb/news/${currentPage - 1 > 0 ? currentPage - 1 : 1}" aria-label="Previous">
                <span aria-hidden="true">«</span>
            </a>
        </li>
        <c:forEach begin="1" end="${totalPages}" var="page">
            <li class="page-item ${currentPage == page ? 'active' : ''}"><a class="page-link" href="/ssb/news/${page}" style="background-color: ${currentPage == page ? '#4352B5' : '#f8f9fa'}; color: ${currentPage == page ? 'white' : '#4352B5'};">${page}</a></li>
        </c:forEach>
        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
            <a class="page-link" href="/ssb/news/${currentPage + 1 <= totalPages ? currentPage + 1 : totalPages}" aria-label="Next">
                <span aria-hidden="true">»</span>
            </a>
        </li>
    </ul>
</nav>

</div>

<%@include file="footer.jsp"%>
