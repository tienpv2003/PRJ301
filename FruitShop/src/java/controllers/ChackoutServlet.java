/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cart;
import models.User;

/**
 *
 * @author PhạmVinhTiến
 */
public class ChackoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object order = session.getAttribute("cart");

        if (order != null) {
            cart = (Cart) order;
        } else {
            cart = new Cart();
        }
        User user = (User) session.getAttribute("userObj");
        int total = cart.getTotalMoney();
        session.setAttribute("total", total);
        if (user != null) {
            user = (User) user;
            OrderDAO oDAO = new OrderDAO();
            
            try {
                oDAO.addOrder(user, cart);
            } catch (Exception ex) {
                Logger.getLogger(ChackoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.removeAttribute("cart");
            session.setAttribute("size", 0);
            response.sendRedirect("home");
        } else {
            response.sendRedirect("login");
        }
    }

}
