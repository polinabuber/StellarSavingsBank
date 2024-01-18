<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/ssb/static/img/MerryChristmas.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/ssb/static/img/Pig.jpg" class="d-block w-100" alt="...">
    </div>
      <div class="carousel-item">
          <img src="/ssb/static/img/FastAndEasy.jpg" class="d-block w-100" alt="...">
        </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
          <br>
          <br>
          <br>
<h1 id="exchange-rates" style="text-align: center;">Exchange rates</h1>

<br>
<div style="display: flex; justify-content: center;">
    <table style="border-collapse: collapse; margin: 25px 0; font-size: 1.5em; font-family: sans-serif; min-width: 800px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.15); text-align: center;">
        <thead>
            <tr style="background-color: #4352B5; color: #ffffff;">
                <th></th>
                <th>We Buy</th>
                <th>We Sell</th>
            </tr>
        </thead>
     <tbody>
         <c:forEach var="exchangeRate" items="${exchangeRateList}">
             <c:if test="${exchangeRate.displayInList}">
                 <tr style="border-bottom: 1px solid #dddddd;">
                     <td>
                         <c:choose>
                             <c:when test="${exchangeRate.id == 1}">
                                 <img src="/ssb/static/img/usd.jpg" alt="Флаг 1" style="width: 30px; height: 20px;">
                             </c:when>
                             <c:when test="${exchangeRate.id == 2}">
                                 <img src="/ssb/static/img/eur.jpg" alt="Флаг 2" style="width: 30px; height: 20px;">
                             </c:when>
                         </c:choose>
                         ${exchangeRate.name}
                     </td>
                     <td>${exchangeRate.buyRate}</td>
                     <td>${exchangeRate.sellRate}</td>
                 </tr>
             </c:if>
         </c:forEach>
     </tbody>

    </table>
</div>
<p style="text-align: center; font-size: 1.2em; font-family: sans-serif;">Exchange rates on <fmt:formatDate value="${exchangeRateList[0].timestamp}" pattern="dd.MM.yyyy"/>, since <fmt:formatDate value="${exchangeRateList[0].timestamp}" pattern="HH:mm"/></p>
<div style="display: flex; justify-content: center;">
    <a href="/ssb/exchange-rates/convert-result" class="btn btn-lg" style="background-color: #4352B5; color: white;">Currency Converter</a>
</div>
<br>
<br>
<div class="row row-cols-1 row-cols-md-3 g-4" style="background-image: url('/ssb/static/img/background.jpg');">
  <div class="col">
    <div class="card h-100" style="width: 70%; margin: auto;">
      <img src="/ssb/static/img/family.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Pave Your Way to Property Ownership with Stellar Savings Bank!</h5>
        <p class="card-text">We offer a unique deposit scheme specifically designed for those aspiring to become property owners. Our goal is to make your dream of owning a home a reality sooner than you think!</p>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card h-100" style="width: 70%; margin: auto;">
      <img src="/ssb/static/img/phone.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Boost Your Savings with Stellar Savings Bank!</h5>
        <p class="card-text">We offer attractive terms for savings accounts that will help you grow your savings and secure a safe future.</p>
      </div>
    </div>
  </div>
  <div class="col">
    <div class="card h-100" style="width: 70%; margin: auto;">
      <img src="/ssb/static/img/cards.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">New Debit Cards from Stellar Savings Bank!</h5>
        <p class="card-text">We are pleased to introduce our new debit cards with enhanced security features and higher limits. Get your card today!</p>
      </div>
    </div>
  </div>
</div>
<br>
<br>
 <h1 style="text-align: center; background: linear-gradient(to right, red, blue); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">
   Still have any questions?
 </h1>

<br>
<div class="text-center">
    <a href="/ssb/requestForm" class="btn btn-lg" style="background-color: #4352B5; color: white;">Request a Call</a>
</div>


<%@include file="footer.jsp"%>
