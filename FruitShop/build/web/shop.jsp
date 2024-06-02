<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Shop</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="home">Home</a></li>
            <li class="breadcrumb-item"><a href="home">Pages</a></li>
            <li class="breadcrumb-item active text-white">Shop</li>
        </ol>
    </div>
    <!-- Single Page Header End -->


    <!-- Fruits Shop Start-->
    <div class="container-fluid fruite py-5">
        <div class="container py-5">
            <h1 class="mb-4">Fresh fruits shop</h1>
            <div class="row g-4">
                <div class="col-lg-12">
                    <div class="row g-4">
                        <div class="col-xl-3">
                            <div class="input-group w-100 mx-auto d-flex">
                                <form action="search?index=1" method="get" style="display: flex">
                                    <input type="text" name="keyword" class="form-control p-3" placeholder="${searchKeyword}" aria-describedby="search-icon-1" required="">
                                <input type="submit" value="Search" class="btn" />
                            </form>
                        </div>
                    </div>
                    <div class="col-6"></div>
                </div>
                <div class="row g-4">
                    <div class="col-lg-3">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4>Categories</h4>
                                    <ul class="list-unstyled fruite-categorie">
                                        <c:forEach items="${categories}" var="category">
                                            <li>
                                                <div class="d-flex justify-content-between fruite-name ${category.id eq cid ? 'bg-primary text-white' : '' }">
                                                    <a class="${category.id eq cid ? 'text-white' : '' }" href="shop?cid=${category.id}"><i class="fas fa-apple-alt me-2"></i>${category.name}</a>
                                                    <span>${category.getTotalProduct()}</span>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="position-relative">
                                    <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="">
                                    <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                        <h3 class="text-secondary fw-bold">Fresh <br> Fruits</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  

                    <div class="col-lg-9">
                        <div class="row g-4 justify-content-center">
                            <c:if test="${not empty requestScope.errorMessage}">
                                <p style="color: red;">${requestScope.errorMessage}</p>
                                <a href="shop" class="rounded" style="text-align: center">Back To Shop</a>
                            </c:if>
                            <c:if test="${not empty requestScope.searchResults}">
                                <c:forEach var="fruit" items="${requestScope.searchResults}">
                                    <div class="col-md-6 col-lg-6 col-xl-4">
                                        <div class="rounded position-relative fruite-item">
                                            <form action="shop-detail" method="GET">
                                                <div class="fruite-img">
                                                    <!-- Use a submit button inside the form to trigger the request -->
                                                    <button type="submit" name="id" value="${fruit.id}" class="img-btn" style="border:0">
                                                        <img src="${fruit.image}" class="img-fluid w-100 rounded-top" alt="">
                                                    </button>
                                                </div>
                                            </form>
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${fruit.category.name}</div>
                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                <h4>${fruit.name}</h4>
                                                <p>${fruit.description}</p>
                                                <div class="d-flex justify-content-between flex-lg-wrap">
                                                    <p class="text-dark fs-5 fw-bold mb-0">${fruit.price} $/kg</p>
                                                    <form action="add-to-cart">
                                                        <input hidden type="text" value="${fruit.id}" name="id"/>
                                                        <button type="submit" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i>Order Detail</button>
                                                        <p class="text-success">${msg}</p>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if> 

                            <c:if test="${empty requestScope.searchResults}">
                                <c:forEach items="${fruits}" var="fruit">
                                    <div class="col-md-6 col-lg-6 col-xl-4">
                                        <div class="rounded position-relative fruite-item">
                                            <form action="shop-detail" method="GET">
                                                <div class="fruite-img">
                                                    <!-- Use a submit button inside the form to trigger the request -->
                                                    <button type="submit" name="id" value="${fruit.id}" class="img-btn" style="border:0">
                                                        <img src="${fruit.image}" class="img-fluid w-100 rounded-top" alt="">
                                                    </button>
                                                </div>
                                            </form>
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${fruit.category.name}</div>
                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                <h4>${fruit.name}</h4>
                                                <p>${fruit.description}</p>
                                                <div class="d-flex justify-content-between flex-lg-wrap">
                                                    <p class="text-dark fs-5 fw-bold mb-0">${fruit.price} $/kg</p>
                                                    <form action="shop-detail">
                                                        <input hidden type="text" value="${fruit.id}" name="id"/>
                                                        <button type="submit" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Order Detail</button>
                                                        <p class="text-success">${msg}</p>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <div class="col-12">
                                <div class="pagination d-flex justify-content-center mt-5">
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <a href="search?index=${i}&keyword=${searchKeyword}" class="rounded">
                                            ${i}
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

