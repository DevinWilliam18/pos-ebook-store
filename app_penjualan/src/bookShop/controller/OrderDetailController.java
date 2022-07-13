/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.controller;

import bookShop.dao.OrderDetailDao;
import bookShop.database.DatabaseBookshop;
import bookShop.model.OrderDetailEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public class OrderDetailController {

    public OrderDetailController() {
    }
    
    public List<OrderDetailEntity> getAllDetails() throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        List<OrderDetailEntity> detailResults = orderDetailDao.getAllDetails();
        
        return detailResults;
    }
    public List<OrderDetailEntity> getAllByDetailsId(int detailsId) throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        List<OrderDetailEntity> detailResults = orderDetailDao.getByDetailId(detailsId);
        
        return detailResults;
    }
    public List<OrderDetailEntity> getAllByOrderId(int orderId) throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        List<OrderDetailEntity> detailResults = orderDetailDao.getByOrderId(orderId);
        
        return detailResults;
    }
    public boolean checkUpdateDetail(int orderDetailId, int isbn, int qty, int bookQty) throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        boolean result = orderDetailDao.updateOrderDetail(orderDetailId, isbn, qty, bookQty);
        
        return result;
    }
    public boolean checkInsertDetail(int orderId,int isbn, int qty) throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        if (orderDetailDao.insertOrderDetail(orderId, isbn, qty)) {
            return true;
        }
        return false;
    }
    
    public boolean checkDeleteDetail(int orderDetailId,int isbn, int qty) throws SQLException, ClassNotFoundException{
        OrderDetailDao orderDetailDao = DatabaseBookshop.getOrderDetailDao();
        boolean result = orderDetailDao.deleteOrderDetail(orderDetailId, isbn, qty);
        
        return result;
    }
}
