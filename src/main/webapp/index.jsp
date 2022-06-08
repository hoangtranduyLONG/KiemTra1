<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08/06/2022
  Time: 9:10 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<form method="post">
  <input type="text" name="name" placeholder="Nhập tên cần tìm">
  <input type="hidden" name="action" value="search">
  <input type="submit" value="tìm kiếm">
</form>
<br>
<form method="post">
  <input type="number" name="start" placeholder="Nhập giá bắt đầu">
  <input type="number" name="end" placeholder="Nhập giá kết thúc">
  <input type="hidden" name="action" value="searchByPrice">
  <input type="submit" value="tìm kiếm">
</form>
<H1>Danh sách sản phẩm</H1>
<h3>mã sản phẩm, tên sản phẩm, giá, số lượng</h3>
<c:forEach var="item" items="${products}">
  <h3>${item.name}, ${item.price}</h3>
    <c:if test="${item.price > 200}">
      <h3>${item.name}- Khuyễn mãi 10%</h3>
    </c:if>
    <c:if test="${item.price < 200}">
      <h3>${item.name}- Khuyễn mãi 20%</h3>
    </c:if>
</c:forEach>
</body>
</html>