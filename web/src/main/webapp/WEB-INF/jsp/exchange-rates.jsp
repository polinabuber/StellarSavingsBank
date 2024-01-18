<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">We Buy</th>
      <th scope="col">We Sell</th>
      <th scope="col">Update</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="exchangeRate" items="${exchangeRateList}">
      <c:if test="${exchangeRate.displayInList}">
        <tr>
          <td>${exchangeRate.name}</td>
          <td>${exchangeRate.buyRate}</td>
          <td>${exchangeRate.sellRate}</td>
          <td>
              <form action="./exchange-rates/update" method="get">
                  <input type="hidden" name="id" value="${exchangeRate.id}">
                  <input type="submit" value="Update">
              </form>
          </td>
        </tr>
      </c:if>
    </c:forEach>
  </tbody>
</table>

<p>Exchange rates on <fmt:formatDate value="${exchangeRateList[0].timestamp}" pattern="dd.MM.yyyy"/>, since <fmt:formatDate value="${exchangeRateList[0].timestamp}" pattern="HH:mm"/></p>



<%@include file="footer.jsp"%>