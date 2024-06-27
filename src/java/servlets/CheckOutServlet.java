package servlets;

import DAO.OrderDAO;
import data.Cart;
import data.Order;
import data.User;
import dbconn.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth");
            
            if (cart_list != null && auth != null) {
                for (Cart c : cart_list) {
                    Order orderModel = new Order();
                    orderModel.setId(c.getId());
                    orderModel.setUid(auth.getId());
                    orderModel.setQuantity(c.getQuantity());
                    orderModel.setDate(formatter.format(date));
                    
                    OrderDAO odao = new OrderDAO(DBConnection.getConnection());
                    boolean result = odao.insertOrder(orderModel);
                    if (!result) {
                        break;
                    }
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");
            } else if (auth == null) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
