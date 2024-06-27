<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="data.Cart"%>
<%@page import="data.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProducts = null;
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>Shopping Cart Login</title>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">User Login</div>
                <div class="card-body">
                    <form action="user-login" method="POST">
                        <div class="form-group">
                            <label>Email Address</label>
                            <input type="email" name="login-email" placeholder="Enter Your Email" class="form-control" required />
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="login-password" placeholder="********" class="form-control" required />
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
