/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.dao;

import bookShop.model.Invoice;
import bookShop.model.OrderEntity;
import java.util.List;

/**
 *
 * @author USER
 */
public interface OrderDao {
    int createNewOrder(OrderEntity orderEntity);
    boolean updateOrderTable(int orderId, int amount);
    List<Invoice> invoiceResult(int orderId);
}
