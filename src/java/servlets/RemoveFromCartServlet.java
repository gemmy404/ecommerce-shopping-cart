package servlets;

import data.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (id != null && cart_list != null) {
                for (Cart c : cart_list) {
                    if (c.getId() == Integer.parseInt(id)) {
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
                response.sendRedirect("cart.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
        }
        
    }
}
