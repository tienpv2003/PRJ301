/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Cart;
import models.Category;
import models.Item;
import models.Order;
import models.User;
import utils.DBContext;

/**
 *
 * @author PhạmVinhTiến
 */
public class OrderDAO extends DBContext implements BaseDAO<Category> {

    //add order in DB
    public void addOrder(User u, Cart cart) throws Exception {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            //add bang order
            String sql = "INSERT INTO orders (u_id, order_date, total_price) VALUES(?,?,?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.setString(2, date);
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();
            //lay order id cua order vua insert vao DB
            String sql1 = "select top 1 id from orders order by id desc";
            PreparedStatement ps1 = getConnection().prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                //them san pham vao order-details
                for (Item i : cart.getItems()) {
                    String sql2 = "INSERT INTO order_details (o_id, p_id, quantity, price)VALUES(?,?,?,?)";
                    PreparedStatement ps2 = getConnection().prepareStatement(sql2);
                    ps2.setInt(1, oid);
                    ps2.setInt(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setDouble(4, i.getPrice());
                    ps2.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        UserDAO dao = new UserDAO();
        for (User u : dao.getAll()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        
        try {
            String sql = "select * from orders";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int uid = rs.getInt(2);
                User user = getUserById(uid);
                String orderDate = rs.getString(3);
                long totalPrice = rs.getLong(4);
                list.add(new Order(id, user, orderDate, totalPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Category> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(Category obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Category obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
