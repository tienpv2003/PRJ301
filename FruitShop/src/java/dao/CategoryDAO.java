package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import utils.DBContext;

public class CategoryDAO extends DBContext implements BaseDAO<Category> {
    
    @Override
    public Category findById(int id) {
        String query = "select * from categories where id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();        
        String query = "select * from categories";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category newCategory = new Category(rs.getInt(1), rs.getString(2));
                categories.add(newCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
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

    //check
    public static void main(String[] args) {
        CategoryDAO d = new CategoryDAO();
        System.out.println(d.getAll());
    }
    
}
