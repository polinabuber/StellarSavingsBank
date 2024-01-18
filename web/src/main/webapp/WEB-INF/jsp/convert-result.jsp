<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<div class="container">
    <h1 class="text-center my-4">Currency Converter</h1>
    <form action="${pageContext.request.contextPath}/exchange-rates/convert" method="post" class="mx-auto" style="max-width: 300px;">
        <div class="form-group">
            <label for="fromCurrency">From Currency:</label>
            <select id="fromCurrency" name="fromCurrency" class="form-control">
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="BYN">BYN</option>
            </select>
        </div>
        <div class="form-group">
            <label for="toCurrency">To Currency:</label>
            <select id="toCurrency" name="toCurrency" class="form-control">
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="BYN">BYN</option>
            </select>
        </div>
        <div class="form-group">
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" class="form-control">
        </div>
        <div class="form-group text-center" style="margin-top: 20px;">
            <button type="submit" class="btn btn-primary">Convert</button>
        </div>
    </form>
    <c:if test="${not empty result}">
        <p class="text-center my-4">Conversion Result: ${result}</p>
        <p class="text-center my-4">Exchange Rate: ${rate}</p>
    </c:if>
</div>




