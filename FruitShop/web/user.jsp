
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

<c:if test="${not empty Msg}">
    <h3 class="text-center text-danger">${Msg}</h2>
    </c:if>
<div style="margin: 200px 0 0 100px;">
    <h2 class="text-center">User list</h3>
</div>
<div class="container mt-3">
    <div class="container">
        <a href="admin" class="btn btn-primary">Back</a>
    </div>
</div>

<div    
    <div class="container mt-3">
    <div class="container ">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <p class="mb-0 mt-4">${user.id}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.firstName}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.lastName}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.phoneNumber}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.address}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.email}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.passwordHash}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${user.isAdmin ? 'Admin' : 'User'}</p>
                            </td>
                            <td>
                                <div class="input-group-btn"> 
                                    <a href = "delete-user?id=${user.id}" >
                                        <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                            <i class="bi bi-trash">Delete</i>
                                        </button>
                                    </a>                                            
                                </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>