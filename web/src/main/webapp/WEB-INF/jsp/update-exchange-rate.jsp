<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp"%>
<form action="${contextPath}/exchange-rates/update" method="post">
  <input type="hidden" name="id" value="${exchangeRate.id}">
  <input type="text" name="name" value="${exchangeRate.name}">
  <label for="buyRate">Buy Rate:</label>
  <input type="number" id="buyRate" name="buyRate" value="${exchangeRate.buyRate}" step="0.0001">
  <label for="sellRate">Sell Rate:</label>
  <input type="number" id="sellRate" name="sellRate" value="${exchangeRate.sellRate}" step="0.0001">
  <input type="submit" value="Update">
</form>
<%@include file="footer.jsp"%>
