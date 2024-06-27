package DAO;

import data.Cart;
import data.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection con;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDAO(Connection con) {
        this.con = con;
    }


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        
        try {
            query = "select * from products";
            ps = this.con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                
                products.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    
    public List<Cart> getCartProducts(ArrayList<Cart> cart_list) {
        List<Cart> products = new ArrayList<>();
        try {
           if (cart_list.size() > 0) {
                for (Cart item:cart_list) {
                query = "select * from products where id = ?";
                ps = this.con.prepareStatement(query);
                ps.setInt(1, item.getId());
                rs = ps.executeQuery();
                while (rs.next()) {                        
                    Cart row = new Cart();
                    row.setId(rs.getInt("id"));
                    row.setName(rs.getString("name"));
                    row.setCategory(rs.getString("category"));
                    row.setPrice(rs.getDouble("price")*item.getQuantity());
                    row.setQuantity(item.getQuantity());
                    products.add(row);
                }
            }
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }   
    
    public double getTotalCartPrice (List<Cart> cartList) {
        double sum = 0.0;
        if (cartList.size() > 0) {
            for (Cart item:cartList) {
                sum += item.getPrice();
            }
        }
        return sum;
    }
}
