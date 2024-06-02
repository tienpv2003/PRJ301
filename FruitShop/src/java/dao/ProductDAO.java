package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import utils.DBContext;

public class ProductDAO extends DBContext implements BaseDAO<Product> {

    @Override
    public Product findById(int id) {

        String query = "select * from products where id = ?";
        try {

            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String name = rs.getString("name");
                String image = rs.getString("image");
                int categoryId = rs.getInt("c_id");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String origin = rs.getString("origin");
                String quality = rs.getString("quality");
                boolean isPassed = rs.getBoolean("isPassed");
                int quantity = rs.getInt("quantity");
                return (new Product(id, name, image, categoryId, price, description, price, origin, quality, isPassed, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getAllByCategoryId(int cid) {
        List<Product> fruits = new ArrayList<>();
        CategoryDAO cdi = new CategoryDAO();
        String query = "select * from products where c_id = ?";
        try {

            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int categoryId = rs.getInt("c_id");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String origin = rs.getString("origin");
                String quality = rs.getString("quality");
                boolean isPassed = rs.getBoolean("isPassed");
                int quantity = rs.getInt("quantity");
                fruits.add(new Product(id, name, image, categoryId, price, description, price, origin, quality, isPassed, quantity));
            }
            return fruits;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> fruits = new ArrayList<>();
        String query = "select * from products";
        try {

            PreparedStatement ps = getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int categoryId = rs.getInt("c_id");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String origin = rs.getString("origin");
                String quality = rs.getString("quality");
                boolean isPassed = rs.getBoolean("isPassed");
                int quantity = rs.getInt("quantity");
                fruits.add(new Product(id, name, image, categoryId, price, description, price, origin, quality, isPassed, quantity));
            }
            return fruits;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public boolean insert(Product product) {
        String query = "INSERT INTO products (name, image, c_id, price,description, origin, quality, isPassed, quantity,weight) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setInt(3, product.getCid());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getOrigin());
            ps.setString(7, product.getQuality());
            ps.setBoolean(8, product.isPassed());
            ps.setInt(9, product.getQuantity());
            ps.setDouble(10, product.getWeight());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Insert failed
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    public void updateProduct(String name, int categoryId, String imagePath, double price,
            double weight, String origin, String quality, boolean isPassed, int quantity, String description, int id) {
        String sql = "UPDATE products SET name = ?, c_id = ?, image = ?, price = ?, weight = ?, origin = ?, quality = ?, isPassed = ?, quantity = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, categoryId);
            statement.setString(3, imagePath);
            statement.setDouble(4, price);
            statement.setDouble(5, weight);
            statement.setString(6, origin);
            statement.setString(7, quality);
            statement.setBoolean(8, isPassed);
            statement.setInt(9, quantity);
            statement.setString(10, description);
            statement.setInt(11, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(String id) {
        String query = "delete from products where id = " + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalProductByCagegoryId(int id) {
        String query = "select count(*) from products where c_id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //search all product
    public List<Product> searchFruitsByName(String name) throws Exception {
        List<Product> searchResults = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products WHERE name LIKE ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + name + "%");  // Using % to match any substring
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                // Populate the Product object with data from the ResultSet
                p.setId(rs.getInt("id"));
                p.setCid(rs.getInt("c_id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setPrice(rs.getFloat("price"));
                p.setWeight(rs.getFloat("weight"));
                p.setOrigin(rs.getString("origin"));
                p.setQuality(rs.getString("quality"));
                p.setPassed(rs.getBoolean("isPassed"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDescription(rs.getString("description"));

                // Add the populated Product object to the list
                searchResults.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    //count number search page
    public int count(String name) {
        try {
            String sql = "select count(*) from products where name like ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //search theo phân trang
    public List<Product> search(String name, int index, int size) throws Exception {
        List<Product> searchResults = new ArrayList<>();
        try {
            String sql = "with Page as(select ROW_NUMBER() over (order by id asc ) as rowNumber\n"
                    + ", * from products where name like ?)\n"
                    + "select * from Page where rowNumber between ?*3-2 and ?*3 ";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + name + "%");  // Using % to match any substring
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                // Populate the Product object with data from the ResultSet
                p.setId(rs.getInt("id"));
                p.setCid(rs.getInt("c_id"));
                p.setName(rs.getString("name"));
                p.setImage(rs.getString("image"));
                p.setPrice(rs.getDouble("price"));
                p.setWeight(rs.getDouble("weight"));
                p.setOrigin(rs.getString("origin"));
                p.setQuality(rs.getString("quality"));
                p.setPassed(rs.getBoolean("isPassed"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDescription(rs.getString("description"));

                // Add the populated Product object to the list
                searchResults.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    //dem so luong product
    public int getNumberTotalProduct() {
        String sql = "select count(*) from products";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getFourProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id "
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, (index - 1) * 4); // Thay đổi chỉ số của offset
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("c_id"), 
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getFloat("weight"),
                        rs.getString("origin"),
                        rs.getString("quality"),
                        rs.getBoolean("isPassed"),
                        rs.getInt("quantity"));

                // Add the populated Product object to the list
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để xác định vấn đề cụ thể
        }

        return list; // Trả về danh sách đã được tạo
    }

    //test
    public static void main(String[] args) throws Exception {
//        String name = "a";
//        Product product = new Product("A", "ádfsdf", 3, 2, "sđfsf", 1, "VN", "A", true, 2);
        ProductDAO productDAO = new ProductDAO();
//        List<Product> list = productDAO.search(name, 1, 3);
//        System.out.println(productDAO.insert(product));
//        for (Product p : list) {
        System.out.println(productDAO.findById(65));
//        }
    }
}
