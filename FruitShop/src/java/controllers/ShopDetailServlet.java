/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Product;

/**
 *
 * @author PhạmVinhTiến
 */
public class ShopDetailServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Product> fruits = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        String id = request.getParameter("id");
        try {
            int idNum = Integer.parseInt(id);
            Product fd = productDAO.findById(idNum);
            request.setAttribute("fruit", fd);
            List<Category> categories = categoryDAO.getAll();
            fruits = productDAO.getAllByCategoryId(fd.getId());
            request.setAttribute("categories", categories);
            request.setAttribute("fruits", fruits);
            RequestDispatcher rd = request.getRequestDispatcher("shop-detail.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    } 
}
