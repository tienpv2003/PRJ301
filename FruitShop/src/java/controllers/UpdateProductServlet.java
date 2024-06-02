package controllers;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;

public class UpdateProductServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cDAO = new CategoryDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = pdao.findById(id);
        if (product != null) {
            request.setAttribute("fruits", product);
        }
        request.setAttribute("categories", cDAO.getAll());
        request.getRequestDispatcher("update-product.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {        
            ProductDAO pdao = new ProductDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            double weight = Double.parseDouble(request.getParameter("weight"));
            String origin = request.getParameter("origin");
            String quality = request.getParameter("quality");
            boolean isPassed = Boolean.parseBoolean(request.getParameter("isPassed"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            pdao.updateProduct(name, categoryId, image, price, weight, origin, quality, isPassed, quantity, description, id);
            // Chuyển hướng về trang chi tiết sản phẩm đã được cập nhật
            response.sendRedirect("product");
        } catch (NumberFormatException e) {            
            request.setAttribute("errorMsg", "Error: Invalid number format.");
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
        }
    }
}
