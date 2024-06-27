package DAO;

import data.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {

    private Connection con;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDAO(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;

        query = "insert into orders(p_id, u_id, o_quantity, o_date) values (?,?,?,?)";
        try {
            ps = this.con.prepareStatement(query);
            ps.setInt(1, model.getId());
            ps.setInt(2, model.getUid());
            ps.setInt(3, model.getQuantity());
            ps.setString(4, model.getDate());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public List<Order> userOrders(int userId) {
        List<Order> list = new ArrayList<>();
        try {
            query = "SELECT orders.o_id, p_id, products.name, products.category, "
                    + "products.price * orders.o_quantity AS total_price, "
                    + "orders.o_quantity, orders.o_date "
                    + "FROM orders "
                    + "JOIN products ON orders.p_id = products.id "
                    + "WHERE orders.u_id = ?";

            ps = this.con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("o_id"));
                order.setId(rs.getInt("p_id"));
                order.setName(rs.getString("name"));
                order.setCategory(rs.getString("category"));
                order.setPrice(rs.getDouble("total_price"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));

                list.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void cancelOrder(int orderId) {
        try {
            query = "delete from orders where o_id = ?";
            ps = this.con.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
