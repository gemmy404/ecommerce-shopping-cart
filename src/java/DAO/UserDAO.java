package DAO;

import data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private Connection con;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAO(Connection con) {
        this.con = con;
    }
    
    public boolean userRegister(String name, String email, String password) {
        boolean result = false;
        try {
            query = "insert into users (name, email, password) values (?,?,?)";
            ps = this.con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    public User userLogin(String email, String password) {
        User user = null;
        try {
            query = "select * from users where email = ? and password = ?";
            ps = this.con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
}
