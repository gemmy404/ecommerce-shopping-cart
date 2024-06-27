package servlets;

import DAO.UserDAO;
import data.User;
import dbconn.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");
            try {
                UserDAO udao = new UserDAO(DBConnection.getConnection());
                User user = udao.userLogin(email, password);
                if (user != null) {
                    request.getSession().setAttribute("auth", user);
                    response.sendRedirect("index.jsp");
                } else {
                    request.getSession().setAttribute("error", "login");
                    response.sendRedirect("error.jsp");                    
//                    out.println("user login failed");
                }
                        } catch (ClassNotFoundException | SQLException ex) {
                            System.out.println(ex.getMessage());
            }
        }
    }

}
