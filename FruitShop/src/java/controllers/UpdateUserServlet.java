/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import models.User;

/**
 *
 * @author PhạmVinhTiến
 */
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDAO = new UserDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        User users = uDAO.findById(id);
        if (users != null) {
            request.setAttribute("users", users);
        }
        request.getRequestDispatcher("update-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO uDAO = new UserDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String phonenum = request.getParameter("phonenum");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String password = request.getParameter("pass");
            String passconfirm = request.getParameter("passconfirm");
            if (password.equals(passconfirm)) {
                // Hash mật khẩu
                String hashedPassword = hashPassword(password);

                // Cập nhật người dùng với mật khẩu đã hash
                uDAO.updateUser(firstname, lastname, phonenum, email, hashedPassword, false, address, id);

                // Chuyển hướng về trang danh sách người dùng sau khi cập nhật
                response.sendRedirect("home");
            } else {
                // Nếu mật khẩu và mật khẩu xác nhận không khớp, chuyển hướng lại trang cập nhật với thông báo lỗi
                request.setAttribute("errorMsg", "Error: Passwords do not match.");
                request.getRequestDispatcher("update-user.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "Error: Invalid number format.");
            request.getRequestDispatcher("update-user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //hash password
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
}
