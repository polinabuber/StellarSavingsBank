<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<div class="container" style="max-width: 960px; margin: auto; padding: 2rem; background-color: #f8f9fa;">
    <h2 class="text-primary" style="font-size: 2rem; font-weight: bold;">My deposits</h2>
    <c:forEach var="deposit" items="${deposits}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${deposit.name}</h5>
                <p class="card-text">Balance: ${deposit.balance}</p>
            </div>
        </div>
    </c:forEach>
    <form action="/addDeposit" method="post">
        <input type="hidden" name="userId" value="${userAccount.userId}">
        <div class="form-group">
            <label for="depositId">Deposit ID</label>
            <input type="number" class="form-control" id="depositId" name="depositId" required>
        </div>
        <div class="form-group">
            <label for="amount">Amount</label>
            <input type="number" class="form-control" id="amount" name="amount" required>
        </div>
        <button type="submit" class="btn btn-primary">Add to deposit</button>
    </form>
</div>
