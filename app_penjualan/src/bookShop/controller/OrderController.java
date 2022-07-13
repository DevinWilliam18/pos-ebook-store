/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.controller;

import bookShop.dao.OrderDao;
import bookShop.database.DatabaseBookshop;
import bookShop.model.Invoice;
import bookShop.model.OrderEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public class OrderController {

    public OrderController() {
    }
    
    
    public int createNewOrder(OrderEntity orderEntity) throws SQLException, ClassNotFoundException{
        OrderDao orderDao = DatabaseBookshop.getOrderDao();
        
      int orderResult = orderDao.createNewOrder(orderEntity);
      
      return orderResult;
    }
    
    public boolean updateOrder(int orderId, int amount) throws SQLException, ClassNotFoundException{
        OrderDao orderDao = DatabaseBookshop.getOrderDao();
        if (orderDao.updateOrderTable(orderId, amount)){
            return true;
        }
        
        return false;
    }
    
    public List<Invoice> invoiceList(int orderId) throws SQLException, ClassNotFoundException{
        OrderDao orderDao = DatabaseBookshop.getOrderDao();
        List<Invoice> invoices = orderDao.invoiceResult(orderId);
        
        return invoices;
    }
    
}
