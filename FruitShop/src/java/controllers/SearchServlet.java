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
import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 *
 * @author PhạmVinhTiến
 */
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchKeyword = request.getParameter("keyword");
        String indexStr = request.getParameter("index");
        int index = 1; // Default value in case indexStr is null or empty

        if (indexStr != null && !indexStr.trim().isEmpty()) {
            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException e) {
                // Handle the case when the parameter cannot be parsed as an integer
                e.printStackTrace();
                // You might want to set a default value or show an error message to the user
            }
        }

        ProductDAO dao = new ProductDAO();
        // count number page
        int count = dao.count(searchKeyword);
        int endPage = 0;
        int pageSize = 3;
        endPage = count / pageSize;
        if (count % pageSize != 0) {
            endPage++;
        }

        List<Product> searchResults = null;
        List<Product> fruits = new ArrayList();
        // search
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            try {
                searchResults = dao.search(searchKeyword, index, pageSize);
            } catch (Exception ex) {
                ex.printStackTrace();
                // Handle the exception appropriately
            }

            if (!searchResults.isEmpty()) {
                request.setAttribute("searchKeyword", searchKeyword);
                request.setAttribute("searchResults", searchResults);
            } else {
                // If no results found, set an error message
                request.setAttribute("errorMessage", "No results found for the given name Fruit.");
            }
        }
        request.setAttribute("endPage", endPage);
        // request
        request.setAttribute("fruits", fruits);
        // đẩy lên jsp để lưu keyword đã nhập, sau đó ấn vào số trang thì tiếp tục trả về servlet
        request.setAttribute("searchKeyword", searchKeyword);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
