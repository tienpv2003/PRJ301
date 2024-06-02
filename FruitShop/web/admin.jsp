<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>


    <!-- Hero Start -->
    <div class="container-fluid py-5 mb-5 hero-header">
        <div class="container py-5">
            <div class="row g-5 align-items-center">
                <div class="col-md-12 col-lg-7">
                    <h4 class="mb-3 text-secondary">100% Organic Foods</h4>
                    <h1 class="mb-5 display-3 text-primary">Organic Veggies & Fruits Foods</h1>

                </div>
                <div class="col-md-12 col-lg-5">
                    <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel">
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active rounded">
                                <img src="img/hero-img-1.png" class="img-fluid w-100 h-100 bg-secondary rounded" alt="First slide">
                                <a href="" class="btn px-4 py-2 text-white rounded">Fruits</a>
                            </div>
                            <div class="carousel-item rounded">
                                <img src="img/hero-img-2.jpg" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                                <a href="" class="btn px-4 py-2 text-white rounded">Vegetables</a>
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Hero End -->

    <!-- Fruits Shop Start-->
    <div class="container-fluid fruite py-5">
        <div class="container py-5">
            <div class="tab-class text-center">
                <div class="row g-4">
                    <div class="col-lg-12 text-start">
                        <h1 style="text-align: center; color: red">Welcome Admin</h1>
                    </div>
                    <div class="col-lg-2 text-end">
                        <ul class="list-unstyled fruite-categorie">
                        <c:forEach items="${categories}" var="category">
                            <li>
                                <div class="d-flex justify-content-between fruite-name ${category.id eq cid ? 'bg-primary text-white' : '' }">
                                    <a class="${category.id eq cid ? 'text-white' : '' }" href="home?cid=${category.id}"><i class="fas fa-apple-alt me-2"></i>${category.name}</a>
                                    <span>${category.getTotalProduct()}</span>
                                </div>
                            </li>
                        </c:forEach>


                    </ul>
                </div>
            </div>
            <div class="tab-content">
                <div id="tab-1" class="tab-pane fade show p-0 active">
                    <div class="row g-4">
                        <div class="col-lg-12">
                            <div class="row g-4">
                                <c:forEach items="${fruits}" var="fruit">
                                    <div class="col-md-6 col-lg-4 col-xl-3">
                                        <div class="rounded position-relative fruite-item">
                                            <form action="shop-detail" method="GET">
                                                <div class="fruite-img">
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
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>     
        <div class="col-12">
            <div class="pagination d-flex justify-content-center mt-5">
                <c:forEach begin="1" end="${endPage}" var="i">
                    <a href="admin?index=${i}" class="rounded">
                        ${i}
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>