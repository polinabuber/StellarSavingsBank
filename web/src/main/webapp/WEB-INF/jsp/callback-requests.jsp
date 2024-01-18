<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<div class="container">
    <h1 class="text-center my-4">Callback Requests</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th><a href="?sort=date">Date</a></th>
                <th><a href="?sort=processed">Processed</a></th>
                <th>Processed Time</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="request" items="${requests}">
               <c:choose>
                   <c:when test="${request.processed}">
                       <tr style="background-color: lime;">
                   </c:when>
                   <c:otherwise>
                       <tr>
                   </c:otherwise>
               </c:choose>
                   <td>${request.id}</td>
                   <td>${request.name}</td>
                   <td>${request.phoneNumber}</td>
                   <td><fmt:formatDate value="${request.timestamp}" pattern="M/d/yyyy HH:mm" /></td>
                   <td>
                       <form action="${pageContext.request.contextPath}/updateRequest" method="post">
                           <input type="hidden" name="id" value="${request.id}" />
                           <select class="form-select" name="isProcessed" onchange="this.form.submit()">
                               <option ${!request.processed ? 'selected' : ''} value="false">False</option>
                               <option ${request.processed ? 'selected' : ''} value="true">True</option>
                           </select>
                       </form>
                   </td>
                   <td><fmt:formatDate value="${request.processedTimestamp}" pattern="M/d/yyyy HH:mm" /></td>
               </tr>
           </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary" onclick="location.reload();">Update</button>
</div>
<%@include file="footer.jsp"%>
