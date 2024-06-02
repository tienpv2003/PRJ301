/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.CategoryDAO;
import dao.ProductDAO;
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
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String indexPage = request.getParameter("index");

        int index = 1; //defaul value
        int cidNum = 3;

        if (indexPage != null && !indexPage.trim().isEmpty()) {
            try {
                index = Integer.parseInt(indexPage);
            } catch (NumberFormatException e) {
                // Xử lý trường hợp khi tham số không thể được chuyển đổi thành số nguyên
                e.printStackTrace();
                // Bạn có thể đặt giá trị mặc định hoặc hiển thị thông báo lỗi cho người dùng
            }
        }
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        List<Product> fruits = new ArrayList<>();

        int count = productDAO.getNumberTotalProduct();
        int endPage = count / 4;

        if (count % 4 != 0) {
            endPage++;
        }

        if (cid != null) {
            cidNum = Integer.parseInt(cid);
            fruits = productDAO.getAllByCategoryId(cidNum);
            request.setAttribute("cid", cid);
        } else {
            fruits = productDAO.getFourProduct(index);
        }

        request.setAttribute("fruits", fruits);
        request.setAttribute("endPage", endPage);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
