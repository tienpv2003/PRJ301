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
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double total = 0;
        HttpSession session = request.getSession();
        Cart cart;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        List<Item> list = cart.getItems();
        for (Item item : list) {
            total += (item.getPrice() * item.getQuantity());
        }
        request.setAttribute("total", total);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String tnum = request.getParameter("number");
        String tid = request.getParameter("id");
        int num, id;
        num = Integer.parseInt(tnum);
        id = Integer.parseInt(tid);
        String addtocart = request.getParameter("addtocart");
        ProductDAO pDAO = new ProductDAO();
        if (addtocart != null) {
            Product p = pDAO.findById(id);
            double price = p.getPrice();
            Item t = new Item(p, num, price);
            cart.addItem(t);
            List<Item> list = cart.getItems();
            double total = 0;
            for (Item item : list) {
                total += (item.getPrice() * item.getQuantity());
            }
            request.setAttribute("total", total); //tong gia tien
            session.setAttribute("cart", cart);  //product ordered
            session.setAttribute("size", list.size());  //So luong ordered
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
