<%@page import="data.Order"%>
<%@page import="DAO.OrderDAO"%>
<%@page import="dbconn.DBConnection"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.Cart"%>
<%@page import="data.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DecimalFormat df = new DecimalFormat("#.##");
    request.setAttribute("df", df);
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if (auth != null) {
        request.setAttribute("auth", auth);
        orders = new OrderDAO(DBConnection.getConnection()).userOrders(auth.getId());
    } else {
        response.sendRedirect("login.jsp");
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
        <title>Orders</title>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>

        <div class="container">
            <div class="card-header my-3">All Orders</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (orders != null) {
                            for (Order o : orders) { %>
                            <tr>
                                <td><%= o.getDate() %></td>
                                <td><%= o.getName()%></td>
                                <td><%= o.getCategory()%></td>
                                <td><%= o.getQuantity()%></td>
                                <td><%= df.format(o.getPrice())%></td>
                                <td><a href="cancel-order?id=<%= o.getOrderId()%>" class="btn btn-danger btn-sm">Cancel</a></td>
                            </tr>
                            <%}
                        }
                    %>
                </tbody>
            </table>

        </div>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
