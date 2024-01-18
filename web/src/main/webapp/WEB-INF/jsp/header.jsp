<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Stellar Savings Bank</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
.nav-item {
  flex: 1 1 auto;
  text-align: center;
}
</style>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="/ssb">
      <img src="/ssb/static/img/Logo.jpg" width="70" height="70">
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav d-flex flex-fill mb-2 mb-lg-0">
        <li class="nav-item dropdown flex-fill">
          <a class="nav-link dropdown-toggle" href="/ssb" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cards
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/ssb/search">Type 1</a></li>
            <li><a class="dropdown-item" href="#">Type 2</a></li>
            <li><a class="dropdown-item" href="#">Type 3</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown flex-fill">
          <a class="nav-link dropdown-toggle" href="/ssb" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Deposits
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/ssb/search">Type 1</a></li>
            <li><a class="dropdown-item" href="#">Type 2</a></li>
            <li><a class="dropdown-item" href="#">Type 3</a></li>
          </ul>
        </li>
        <li class="nav-item flex-fill">
          <a class="nav-link" href="#exchange-rates">Exchange rates</a>
        </li>
        <li class="nav-item flex-fill">
          <a class="nav-link" href="/ssb/news/1">News</a>
        </li>
        <li class="nav-item flex-fill">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item flex-fill">
        <sec:authorize access="isAuthenticated()">
        <a class="nav-link" href="/ssb/home"><b>My SSB</b></a>
        </sec:authorize>
        </li>
        <li class="nav-item flex-fill">
          <sec:authorize access="isAuthenticated()">
            <a class="nav-link" href="/ssb/logout">Logout</a>
          </sec:authorize>
        </li>
        <li class="nav-item flex-fill">
          <sec:authorize access="!isAuthenticated()">
            <a class="nav-link" href="/ssb/login">Sign in</a>
          </sec:authorize>
        </li>
        <li class="nav-item flex-fill">
          <sec:authorize access="!isAuthenticated()">
            <a class="nav-link" href="/ssb/register">Sign up</a>
          </sec:authorize>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
