/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.dao;

import bookShop.model.OrderDetailEntity;
import java.util.List;

/**
 *
 * @author USER
 */
public interface OrderDetailDao {
    
    boolean insertOrderDetail(int orderId, int isbn, int qty);
    boolean updateOrderDetail(int orderDetailId, int isbn, int qty,int bookQty);
    boolean deleteOrderDetail(int orderDetailId,int isbn, int qty);
    List<OrderDetailEntity> getAllDetails();
    List<OrderDetailEntity> getByDetailId(int detailId);
    List<OrderDetailEntity> getByOrderId(int orderId);
    
    
}
