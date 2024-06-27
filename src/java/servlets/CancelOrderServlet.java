package servlets;

import DAO.OrderDAO;
import dbconn.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if (id != null) {
                OrderDAO odao = new OrderDAO(DBConnection.getConnection());
                odao.cancelOrder(Integer.parseInt(id));
            }
            response.sendRedirect("orders.jsp");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
