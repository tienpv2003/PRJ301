<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container" style="margin-top: 200px;">
    <h2 class="text-center">Update Fruit</h2>        
    <c:if test="${not empty errorMsg}">
        <h3 class="text-center text-danger">${errorMsg}</h3>
    </c:if>
    
        <form action="update-user" method="post">        
        <input value="${users.id}" type="hidden" class="form-control" name="id">
        
        <div class="form-group">
            <label for="fruitName">First Name:</label>
            <input value="${users.firstName}" type="text" class="form-control" id="firstname" name="firstname" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Last Name:</label>
            <input value="${users.lastName}" type="text" class="form-control" id="lastname" name="lastname" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Phone Number:</label>
            <input value="${users.phoneNumber}" type="text" class="form-control" id="phonenum" name="phonenum" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Address:</label>
            <input value="${users.address}" type="text" class="form-control" id="address" name="address" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Email:</label>
            <input value="${users.email}" type="text" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Password:</label>
            <input value="" type="password" class="form-control" id="pass" name="pass" required>
        </div>
        <div class="form-group">
            <label for="fruitName">Confirm Password:</label>
            <input value="" type="password" class="form-control" id="passconfirm" name="passconfirm" required>
        </div>
        <button type="submit" class="btn btn-primary mt-5">Update</button>
        <a href="home" class="btn btn-primary mt-5">Back</a>
    </form>
</div>
