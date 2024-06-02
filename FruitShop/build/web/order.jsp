<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <c:if test="${not empty Msg}">
    <h3 class="text-center text-danger">${Msg}</h2>
    </c:if>
<div style="margin: 200px 0 0 100px;">
    <h2 class="text-center">Order list</h3>
</div>
<div class="container mt-3">
    <div class="container ">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Order Date</th>
                        <th>Total Price</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>
                                <p class="mb-0 mt-4">${list.id}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${list.getUserID().firstName} ${list.getUserID().lastName}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${list.getUserID().phoneNumber}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${list.getUserID().address}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${list.date} </p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${list.total} $</p>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>