package models;
import dao.CategoryDAO;
@lombok.Getter
@lombok.Setter 
@lombok.AllArgsConstructor 
@lombok.NoArgsConstructor
@lombok.Data 

public class Product {
    private int id;
    private String name;
    private String image;
    private int cid;
    private double price;
    private String description;
    private double weight;
    private String origin;
    private String quality;
    private boolean passed;
    private int quantity;

    public Product(String name, String image, int cid, double price, String description, double weight, String origin, String quality, boolean isPassed, int quantity) {
        this.name = name;
        this.image = image;
        this.cid = cid;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.origin = origin;
        this.quality = quality;
        this.passed = isPassed;
        this.quantity = quantity;
    }
    
    
    
    public Category getCategory(){
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.findById(cid);
    }
}


