<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>FruitShop Website</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="https://www.google.com/maps/place/Tr%C6%B0%E1%BB%9Dng+%C4%90%E1%BA%A1i+h%E1%BB%8Dc+FPT/@21.0123829,105.5246665,920m/data=!3m1!1e3!4m6!3m5!1s0x3135abc60e7d3f19:0x2be9d7d0b5abcbf4!8m2!3d21.0124167!4d105.5252892!16s%2Fm%2F02rsytm?hl=vi-VN&entry=ttu" target="_blank" class="text-white">Thạch Thất, Hà Nội, Việt Nam</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="https://mail.google.com/mail/u/0/#inbox?compose=CllgCJZfRmgxjBDlZZmzZkgrdHZRNCDdcBVtzSxWWgdkQlcWNkxVTwcqQbpKbvwDMWPrdlHXHCg" target="_blank" class="text-white">vinhtienofficial@gmail.com</a></small>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <c:if test="${not userObj.isAdmin}">
                    <a href="home" class="navbar-brand"><h1 class="text-primary display-6">FruitShop</h1></a>
                    </c:if>
                    <c:if test="${userObj.isAdmin}">
                    <a href="admin" class="navbar-brand"><h1 class="text-primary display-6">FruitShop</h1></a>
                    </c:if>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <c:if test="${not userObj.isAdmin}">
                            <div class="navbar-nav mx-auto">
                                <a href="home" class="nav-item nav-link active">Home</a>
                                <a href="shop" class="nav-item nav-link">Shop</a>
                                <a href="cart" class="nav-item nav-link">Cart</a>
                                <a href="contact" class="nav-item nav-link">Contact</a>
                            </div>  
                        </c:if> 
                        <c:if test="${userObj.isAdmin}">
                            <div class="navbar-nav mx-auto">
                                <a href="admin" class="nav-item nav-link">Home</a>
                                <a href="product" class="nav-item nav-link">Manager Product</a>
                                <a href="user" class="nav-item nav-link">Manager User</a>
                                <a href="order" class="nav-item nav-link">Manager Order</a>
                            </div>  
                        </c:if>
                        <div class="d-flex m-3 me-0">
                                <a href="cart" class="position-relative me-4 my-auto">
                                    <i class="fa fa-shopping-bag fa-2x"></i>
                                    <c:if test="${not empty cart}">
                                        <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">
                                            ${size}
                                        </span>                               
                                    </c:if>
                                </a>
                            <c:if test="${not empty userObj}">
                                <div class="dropdown">
                                    <button id="dLabel" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Welcome ${userObj.lastName}
                                        <span class="caret"></span>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dLabel">
                                        <c:if test="${not userObj.isAdmin}">
                                        <a class="dropdown-item" href="update-user?id=${userObj.id}">Your Profile</a>
                                        </c:if>
                                        <a class="dropdown-item" href="logout">Logout</a>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${empty userObj}">
                                <div class="navbar-nav mx-auto">
                                    <a href="login" class="nav-item"style="margin-right: 10px" >Login</a><!-- comment -->
                                    <a href="register" class="nav-item">Register</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->
        <script src="js/main.js"></script>