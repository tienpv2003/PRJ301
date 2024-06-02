<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container" style="margin-top: 200px;">
        <h2 class="text-center">Add New Fruit</h2>
    <c:if test="${not empty errorMsg}">
        <h3 class="text-center text-danger">${errorMsg}</h2>
        </c:if>
        <form action="add-product" method="post">
            <div class="form-group">
                <label for="fruitName">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="fruitImage">Image URL:</label>
                <input type="url" class="form-control" id="image" name="image" required>
            </div>
            <div class="form-group">
                <label for="fruitCategory">Category:</label>   
                <select class="form-control" id="category" name="category">
                    <c:if test="${not empty requestScope.categories}">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="form-group">
                <label for="fruitPrice">Price ($):</label>
                <input type="number" step="0.1" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="fruitDescription">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="3" required=""></textarea>
            </div>
            <div class="form-group">
                <label for="fruitWeight">Weight (kg):</label>
                <input type="number" step="0.01" class="form-control" id="weight" name="weight" required>
            </div>
            <div class="form-group">
                <label >Quantity :</label>
                <input type="number" step="1" class="form-control" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="fruitCountry">Origin:</label>
                <input type="text" class="form-control" id="origin" name="origin" required>
            </div>
            <div class="form-group">
                <label for="fruitQuality">Quality:</label>
                <select name="quality" id="quality" class="form-control">
                    <option value="A">A ( Perfect )</option>
                    <option value="B">B ( Good )</option>
                    <option value="C">C ( OK )</option>
                    <option value="D">D ( Temporarily )</option>
                    <option value="E">E ( Bad )</option>
                </select>
            </div>
            <div class="form-group" id="test">
                <label for="fruitTest">Test food safety:</label>
                <select name="test" class="form-control">
                    <option value="true">Passed</option>
                    <option value="false">Failed</option>
                </select>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Add Fruit</button>
        </form>
</div>
