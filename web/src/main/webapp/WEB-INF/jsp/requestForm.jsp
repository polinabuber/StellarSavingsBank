<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<div class="container">
    <h1 class="text-center my-4">Request a Callback</h1>
    <form action="${pageContext.request.contextPath}/requestForm" method="post" class="needs-validation" novalidate onsubmit="alert('Our managers will contact you within 15 minutes.')">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" pattern="[A-Za-zА-Яа-яЁё\s]+" title="Name should only contain letters." required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" pattern="\\+375(17|25|29|33|44)[0-9]{7}" title="Please enter the phone number in the format: +375251111111" required>
        </div>
        <button type="submit" class="btn btn-primary">Call me</button>
    </form>
    <button onclick="window.location.href='/ssb'" class="btn btn-secondary mt-3">Back to Main Page</button>
</div>
<%@include file="footer.jsp"%>
