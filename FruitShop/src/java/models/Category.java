package models;


import dao.ProductDAO;

@lombok.Getter
@lombok.Setter 
@lombok.AllArgsConstructor 
@lombok.NoArgsConstructor
@lombok.Data 
public class Category {
    private int id;
    private String name;
    
    public int getTotalProduct(){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getTotalProductByCagegoryId(this.id);
    }
}
