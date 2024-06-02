<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container" style="margin-top: 200px;">
    <h2 class="text-center">Update Fruit</h2>        
    <c:if test="${not empty errorMsg}">
        <h3 class="text-center text-danger">${errorMsg}</h3>
    </c:if>
    <form action="update-product" method="post">        
        <input value="${fruits.id}" type="hidden" class="form-control" name="id">
        
        <div class="form-group">
            <label for="fruitName">Name:</label>
            <input value="${fruits.name}" type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="fruitImage">Image URL:</label>
            <input value="${fruits.image}" type="text" class="form-control" id="image" name="image">
        </div>
        <div class="form-group">
            <label for="fruitCategory">Category:</label>
            <select class="form-control" id="category" name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="fruitPrice">Price ($):</label>
            <input value="${fruits.price}" type="number" step="0.01" class="form-control" id="price" name="price" required>
        </div>
        <div class="form-group">
            <label for="fruitDescription">Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${fruits.description}</textarea>
        </div>
        <div class="form-group">
            <label for="fruitWeight">Weight (kg):</label>
            <input value="${fruits.weight}" type="number" step="0.01" class="form-control" id="weight" name="weight" required>
        </div>
        <div class="form-group">
            <label for="fruitQuantity">Quantity:</label>
            <input value="${fruits.quantity}" type="number" step="1" class="form-control" id="quantity" name="quantity" required>
        </div>
        <div class="form-group">
            <label for="fruitCountry">Origin:</label>
            <input value="${fruits.origin}" type="text" class="form-control" id="origin" name="origin" required>
        </div>
        <div class="form-group">
            <label for="fruitQuality">Quality:</label>
            <select name="quality" id="quality" class="form-control">
                <option ${fruits.quality == 'A' ? 'selected' : ''} value="A">A (Perfect)</option>
                <option ${fruits.quality == 'B' ? 'selected' : ''} value="B">B</option>
                <option ${fruits.quality == 'C' ? 'selected' : ''} value="C">C</option>
                <option ${fruits.quality == 'D' ? 'selected' : ''} value="D">D</option>
                <option ${fruits.quality == 'E' ? 'selected' : ''} value="E">E</option>
            </select>
        </div>
        <div class="form-group">
            <label for="fruitTest">Test food safety:</label>
            <select name="test" class="form-control">
                <option ${!fruits.passed ? 'selected' : ''} value="true">Passed</option>
                <option ${fruits.passed ? 'selected' : ''} value="false">Not Pass</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-5">Update</button>
<!--        <a href="product" class="btn btn-primary mt-5">Back</a>-->
    </form>
</div>
