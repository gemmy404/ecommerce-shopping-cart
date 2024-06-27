<%@page import="java.text.DecimalFormat"%>
<%@page import="dbconn.DBConnection"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.Cart"%>
<%@page import="data.Cart"%>
<%@page import="data.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DecimalFormat df = new DecimalFormat("#.##");
    request.setAttribute("df", df);
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProducts = null;
    double total = 0.0;
    if (cart_list != null) {
        ProductDAO pdao = new ProductDAO(DBConnection.getConnection());
        cartProducts = pdao.getCartProducts(cart_list);
        total = pdao.getTotalCartPrice(cartProducts);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("total", total);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>Cart</title>
        <style type="text/css">
            .table tbody td {
                vartical-align: middle;
            }
            .btn-incre, .btn-decre {
                box-shadow: none;
                font-size: 25px;
            }
        </style>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div class="d-flex py-3">
                <h3>Total Price: $<%= df.format(total) %></h3>
                <a href="cart-check-out" class="mx-3 btn btn-primary">Check Out</a>
            </div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>
                        <th scope="col">Buy Now</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (cart_list != null) {
                            for (Cart c : cartProducts) { %>
                    <tr>
                        <td><%= c.getName() %></td>
                        <td><%= c.getCategory()%></td>
                        <td><%= df.format(c.getPrice()) %>$</td>
                        <td>
                            <form action="order-now" method="POST" class="form-inline">
                                <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input" />
                                <div class="form-group d-flex justify-content-between w-50">
                                    <a href="quantity-inc-dec?action=dec&id=<%= c.getId() %>" class="btn btn-sm btn-decre"><i class="fas fa-minus-square text-primary"></i></a>
                                    <input type="text" name="quantity" class="form-control w-50" value="<%= c.getQuantity() %>" readonly />
                                    <a href="quantity-inc-dec?action=inc&id=<%= c.getId() %>" class="btn btn-sm btn-incre"><i class="fas fa-plus-square text-primary"></i></a>
                                </div>
                                <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                            </form>
                        </td>
                        <td><a href="remove-from-cart?id=<%= c.getId() %>" class="btn btn-danger">Remove</a></td>
                    </tr>
                    <% }
                        }
                    %>
                </tbody>
            </table>

        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
