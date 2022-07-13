/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.model;

/**
 *
 * @author USER
 */
public class OrderDetailEntity {
    private int orderDetailId;
    private int orderId;
    private int orderDetailIsbn;
    private int orderDetailQty;

    public OrderDetailEntity() {
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderDetailIsbn() {
        return orderDetailIsbn;
    }

    public void setOrderDetailIsbn(int orderDetailIsbn) {
        this.orderDetailIsbn = orderDetailIsbn;
    }

    public int getOrderDetailQty() {
        return orderDetailQty;
    }

    public void setOrderDetailQty(int orderDetailQty) {
        this.orderDetailQty = orderDetailQty;
    }
    
    
}
