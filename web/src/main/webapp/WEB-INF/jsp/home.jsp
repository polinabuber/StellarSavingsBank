<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<div class="container" style="max-width: 960px; margin: auto; padding: 2rem; background-color: #f8f9fa;">
    <h2 class="text-primary" style="font-size: 2rem; font-weight: bold;">Welcome, ${user.firstname}!</h2>
    <p class="text-success" style="font-size: 1.5rem;">Your phone number is: ${user.phoneNumber}</p>
    <p class="text-info" style="font-size: 1.5rem;">Your current balance is: ${account.balance}</p>
 <sec:authorize access="hasRole('ROLE_ADMIN')">
     <p class="text-success" style="font-size: 1.5rem;"><a href="/ssb/callback-requests">Check callback requests</a></p>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_ADMIN')">
     <p class="text-success" style="font-size: 1.5rem;"><a href="/ssb/exchange-rates">Update exchange rate</a></p>
 </sec:authorize>
               <br>
    <a href="/ssb/deposits/${userAccount.id}" class="btn btn-primary">Electronic Piggy Bank</a>
</div>
