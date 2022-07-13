/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.impl;

import bookShop.dao.OrderDetailDao;
import bookShop.model.OrderDetailEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author USER
 */
public class orderDetailImpl implements OrderDetailDao{

    private Connection conn;

    public orderDetailImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public boolean insertOrderDetail(int orderId, int isbn, int qty) {
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO orders_detail(order_id, isbn, quantity) VALUES ("+
                    orderId+
                    ", "+isbn+
                    ", "+qty+")";
            int i = st.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateOrderDetail(int orderDetailId, int isbn, int qty, int bookQty) {
        try {
            Statement st = conn.createStatement();
            String sql1 = "UPDATE orders_detail SET quantity="+bookQty+" WHERE detail_order_id="+orderDetailId;
            String sql2 = null;
            if (qty > bookQty) {
                int updateBookQty = qty-bookQty;
                sql2 = "UPDATE books SET stock=stock+"+updateBookQty+" WHERE isbn="+isbn;
                
            }else{
                int updateBookQty = bookQty-qty;
                sql2 = "UPDATE books SET stock=stock-"+updateBookQty+" WHERE isbn="+isbn;
            }
            int i = st.executeUpdate(sql1);
            int j = st.executeUpdate(sql2);
            if (i == 1 && j == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteOrderDetail(int orderDetailId,int isbn, int qty) {
        try {
            Statement st = conn.createStatement();
            String sql1 = "DELETE FROM orders_detail WHERE detail_order_id="+orderDetailId;
            String sql2 = "UPDATE books SET stock=stock+"+qty+" WHERE isbn="+isbn;
            
            
            int i = st.executeUpdate(sql1);
            int j = st.executeUpdate(sql2);
            if (i == 1 && j==1) {
                return true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<OrderDetailEntity> getAllDetails() {
    
        try {
            List<OrderDetailEntity> allDetails = new ArrayList<>();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM orders_detail";
            ResultSet rs = st.executeQuery(sql);            
            
            while (rs.next()) {
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                detailEntity.setOrderDetailId(rs.getInt(1));
                detailEntity.setOrderId(rs.getInt(2));
                detailEntity.setOrderDetailIsbn(rs.getInt(3));
                detailEntity.setOrderDetailQty(rs.getInt(4));
                
                allDetails.add(detailEntity);
                
            }
            return allDetails;
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<OrderDetailEntity> getByDetailId(int detailId) {
        try {
            Statement st = conn.createStatement();
            List<OrderDetailEntity> allDetails = new ArrayList<>();
            String sql = "SELECT * FROM orders_detail WHERE detail_order_id="+detailId;
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                int detailOrderId = rs.getInt("detail_order_id");
                int orderId = rs.getInt("order_id");
                int isbn = rs.getInt("isbn");
                int qty = rs.getInt("quantity");
                
                
                //load into entity class
                detailEntity.setOrderDetailId(detailOrderId);
                detailEntity.setOrderId(orderId);
                detailEntity.setOrderDetailIsbn(isbn);
                detailEntity.setOrderDetailQty(qty);
                
                
                
                allDetails.add(detailEntity);
                
            }
            
            return allDetails;
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<OrderDetailEntity> getByOrderId(int orderId) {
        try {
            Statement st = conn.createStatement();
            List<OrderDetailEntity> allDetails = new ArrayList<>();
            String sql = "SELECT * FROM orders_detail WHERE order_id="+orderId;
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                int detailOrderId = rs.getInt("detail_order_id");
                int ordersId = rs.getInt("order_id");
                int isbn = rs.getInt("isbn");
                int qty = rs.getInt("quantity");
                
                
                //load into entity class
                detailEntity.setOrderDetailId(detailOrderId);
                detailEntity.setOrderId(ordersId);
                detailEntity.setOrderDetailIsbn(isbn);
                detailEntity.setOrderDetailQty(qty);
                
                allDetails.add(detailEntity);
            }
            return allDetails;
        } catch (SQLException ex) {
            Logger.getLogger(orderDetailImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
           return null;
    }
    
}
