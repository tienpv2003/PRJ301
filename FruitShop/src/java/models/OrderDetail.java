/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    private Order oID;
    private Product pID;
    private int quantity;
    private long price;

    public OrderDetail() {
    }

    public OrderDetail(Order oID, Product pID, int quantity, long price) {
        this.oID = oID;
        this.pID = pID;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getoID() {
        return oID;
    }

    public void setoID(Order oID) {
        this.oID = oID;
    }

    public Product getpID() {
        return pID;
    }

    public void setpID(Product pID) {
        this.pID = pID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "oID=" + oID + ", pID=" + pID + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
