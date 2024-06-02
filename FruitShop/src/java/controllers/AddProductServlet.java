package controllers;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import models.Product;

public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("add-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            String categories = request.getParameter("category");
            int category = Integer.parseInt(categories);
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            double weight = Double.parseDouble(request.getParameter("weight"));
            String origin = request.getParameter("origin");
            String quality = request.getParameter("quality");
            boolean isPassed = Boolean.parseBoolean(request.getParameter("test"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            ProductDAO productDAO = new ProductDAO();

            // Tạo mới sản phẩm và thiết lập các thuộc tính
            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setImage(image);
            newProduct.setCid(category);
            newProduct.setPrice(price);
            newProduct.setDescription(description);
            newProduct.setWeight(weight);
            newProduct.setOrigin(origin);
            newProduct.setQuality(quality);
            newProduct.setPassed(isPassed);
            newProduct.setQuantity(quantity);

            // Thực hiện thêm sản phẩm vào cơ sở dữ liệu
            boolean status = productDAO.insert(newProduct);

            // Xác định thông báo thành công hoặc thất bại
            if (status) {
                request.setAttribute("Msg", "Add " + name + " successful!");
            } else {
                request.setAttribute("errorMsg", "Failed to add product. Please try again!");
            }
            // Cập nhật danh sách sản phẩm và chuyển hướng về trang sản phẩm
            request.setAttribute("fruits", productDAO.getAll());
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Xử lý nếu có lỗi khi chuyển đổi số
            request.setAttribute("errorMsg", "Error: Invalid number format.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        }
    }
}
