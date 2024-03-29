<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<div class="container">
    <div class="card mx-auto" style="max-width: 300px; margin-top: 50px;">
        <div class="card-header text-center">
            <h2>Currency Converter</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/exchange-rates/convert" method="post">
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
                <div class="form-group text-center" style="margin-top: 30px;">
                    <button type="submit" class="btn btn-primary" style="background-color: #4352B5; border-color: #4352B5;">Convert</button>
                </div>
            </form>
        </div>
        <c:if test="${not empty result}">
            <div class="card-footer text-center">
                <p>Conversion Result: ${result}</p>
                <p>Exchange Rate: ${rate}</p>
            </div>
        </c:if>
    </div>
</div>
