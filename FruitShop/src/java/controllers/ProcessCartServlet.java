/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Cart;
import models.Item;
import models.Product;

/**
 *
 * @author PhạmVinhTiến
 */
public class ProcessCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String id_raw = request.getParameter("id");
        String num_raw = request.getParameter("number");
        int id, num;
        id = Integer.parseInt(id_raw);
        ProductDAO pDAO = new ProductDAO();
        try {
            num = Integer.parseInt(num_raw);
            if ((num == -1) && (cart.getQuantityById(id) <= 1)) {
                cart.removeItem(id);
            } else {
                Product p = pDAO.findById(id);
                double price = p.getPrice();
                Item item = new Item(p, num, price);
                cart.addItem(item);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        double total = 0;
        List<Item> list = cart.getItems();
        for (Item item : list) {
            total += (item.getPrice() * item.getQuantity());
        }
        request.setAttribute("total", total);
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        response.sendRedirect("cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String id_raw = request.getParameter("pid");
        int id = Integer.parseInt(id_raw);
        try {
            cart.removeItem(id);
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("size", list.size());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        double total = 0;
        Item itemToRemove = cart.getItemById(id);
        List<Item> list = cart.getItems();
        for (Item item : list) {
            total += (item.getPrice() * item.getQuantity());
            if (item == itemToRemove) {
                total -= (item.getPrice() * item.getQuantity());
            }
        }
        list.remove(itemToRemove);
        request.setAttribute("total", total);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

}
