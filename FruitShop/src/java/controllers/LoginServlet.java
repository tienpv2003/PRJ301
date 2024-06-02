/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.UserDAO;
import java.io.IOException;
import java.security.MessageDigest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = authenticateUser(email, password);
        if (user != null) {
            String remember = request.getParameter("remember");
            if (remember != null) {
                Cookie cRemember = new Cookie("cookrem", remember.trim());
                Cookie cEmail = new Cookie("cookuser", user.getEmail());
                Cookie cPassword = new Cookie("cookpass", password);
                cEmail.setMaxAge(60 * 60 * 24 * 15);  // 15 days
                cPassword.setMaxAge(60 * 60 * 24 * 15);
                cRemember.setMaxAge(60 * 60 * 24 * 15);
                response.addCookie(cEmail);
                response.addCookie(cPassword);
                response.addCookie(cRemember);
            }
            session.setAttribute("userObj", user);
            if (user.isIsAdmin()) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("home");
            }
        } else {
            request.setAttribute("errorMessage", "Username or password may be wrong !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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

    //check account
    private User authenticateUser(String email, String password) {
        UserDAO udi = new UserDAO();
        User user = udi.findByEmail(email);

        try {
            String passwordHashed = hashPassword(password);
            if (user != null && user.getPasswordHash().equals(passwordHashed)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //check
    public static void main(String[] args) {
        LoginServlet loginServlet = new LoginServlet();
        try {
            UserDAO udi = new UserDAO();
            User user = udi.findByEmail("abc@gmail.com");
            String hashedPassword = loginServlet.hashPassword("123");
            System.out.println("Hashed password: " + hashedPassword);
            System.out.println(user.getPasswordHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
