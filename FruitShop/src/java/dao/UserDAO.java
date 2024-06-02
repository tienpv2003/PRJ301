package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;
import utils.DBContext;

public class UserDAO extends DBContext implements BaseDAO<User> {

    @Override
    public boolean insert(User newUser) {
        try {
            Connection conn = getConnection();
            // Hash the password using SHA-256
            String hashedPassword = hashPassword(newUser.getPasswordHash());
            System.out.println(hashedPassword);
            String sql = "INSERT INTO users (first_name, last_name, phone_number, email, passwordHash, address) VALUES (?, ?, ?, ?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newUser.getFirstName());
                pstmt.setString(2, newUser.getLastName());
                pstmt.setString(3, newUser.getPhoneNumber());
                pstmt.setString(4, newUser.getEmail());
                pstmt.setString(5, hashedPassword);
                pstmt.setString(6, newUser.getAddress());

                // Execute the insert statement
                int rowsAffected = pstmt.executeUpdate();

                // Check if the insertion was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            // Set the email parameter
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id"); 
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String password = rs.getString("passwordHash");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    String address = rs.getString("address");
                    // Create and return the User object
                    User user = new User();
                    user.setId(id);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPasswordHash(password);
                    user.setIsAdmin(isAdmin);
                    user.setAddress(address);
                    // Set other user properties
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return null;
    }

    @Override
    public User findById(int id) {
        UserDAO dao = new UserDAO();
        for (User u : dao.getAll()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select * from users";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setEmail(rs.getString("email"));
                u.setPasswordHash(rs.getString("passwordHash"));
                u.setIsAdmin(rs.getBoolean("is_admin"));
                u.setAddress(rs.getString("address"));
                users.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    public void updateUser(String firstName, String lastName, String phoneNumber, String email, String passwordHash, boolean isAdmin,String address, int id) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, email = ?, passwordHash = ?, is_admin = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setString(5, passwordHash);
            statement.setBoolean(6, isAdmin);
            statement.setString(7, address);
            statement.setInt(8, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(String id) {
        String query = "delete from users where id = " + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //check
    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        System.out.println(u.getAll());
    }
}
