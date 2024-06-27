package servlets;

import DAO.UserDAO;
import dbconn.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("register-name");
            String email = request.getParameter("register-email");
            String password = request.getParameter("register-password");
            
            UserDAO udao = new UserDAO(DBConnection.getConnection());
            boolean result = udao.userRegister(name, email, password);
            
            if (result) {
                response.sendRedirect("login.jsp");
            } else {
                request.getSession().setAttribute("error", "register");
                response.sendRedirect("error.jsp");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
