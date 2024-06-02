
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!-- Modal Search End -->
    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Cart</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="home">Home</a></li>
            <li class="breadcrumb-item"><a href="shop-detail">Pages</a></li>
            <li class="breadcrumb-item active text-white">Cart</li>
        </ol>
    </div>
    <!-- Single Page Header End -->
    <!-- Cart Page Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Image</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Handle</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:set var="o" value="${sessionScope.cart}"></c:set>
                    <c:set var="t" value="0"/>
                    <c:forEach items="${o.items}" var="i">
                        <c:set var="t" value="${t+1}"/>
                        <tr>                                
                            <td>${t}</td>
                            <th scope="row">
                                <div class="d-flex align-items-center">
                                    <img src="${i.product.image}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                </div>
                            </th>
                            <td>
                                <p class="mb-0 mt-4">${i.product.name}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${i.price}</p>
                            </td>
                            <td style="padding-top: 30px">
                                <a href="process?number=-1&id=${i.product.id}" class="btn-sm btn-minus rounded-circle bg-light border"><i class="fa fa-minus"></i></a>
                                <input type="text" readonly value="${i.getQuantity()}" style="width: 20%; padding-left: 5%">
                                <a href="process?number=1&id=${i.product.id}" class="btn-sm btn-plus rounded-circle bg-light border"><i class="fa fa-plus"></i></a>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${i.price * i.quantity} $</p>
                            </td>
                            <td>
                                <form action="process" method="post">
                                    <input type="hidden" name="pid" value="${i.product.id}">
                                    <button type="submit" class="btn-md rounded-circle bg-light border mt-4">
                                        <i class="fa fa-times text-danger"></i>
                                    </button>
                                </form>
                            </td>

                        </tr>
                    </c:forEach>                    

                </tbody>
            </table>
        </div>
        <div class="text-right mt-4">
            <label style="font-family: fantasy; font-size: larger" class="md-btn-flat mt-2 mr-3">Total price:</label>
            <p class="mb-0 mt-4">${total} $</p>
        </div>
        <form action="chackout" method="post">
            <div class="float-right">
                <button type="button" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3"><a href="shop">Back to shopping</a></button>
                <button type="submit" class="btn btn-lg btn-primary mt-2">Checkout</button>
            </div>
        </form>
    </div>
</div>
<!-- Cart Page End -->
<!-- Footer Start -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- Back to Top -->
<a href="" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>

</html>
