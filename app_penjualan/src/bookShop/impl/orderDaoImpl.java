/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.impl;

import bookShop.dao.OrderDao;
import bookShop.model.Invoice;
import bookShop.model.OrderEntity;
import java.sql.Connection;
import java.sql.Date;
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
public class orderDaoImpl implements OrderDao{

    private Connection conn;

    public orderDaoImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public int createNewOrder(OrderEntity orderEntity) {
        try {
            Statement st = conn.createStatement();
            String sql1 = "INSERT INTO orders (customer_id,user_id,order_date,amount) VALUES("+
                    orderEntity.getCustomerId()+
                    ", "+orderEntity.getUserId()+
                    ", CURDATE()"+
                    ", "+orderEntity.getAmount()+")";
            int i = st.executeUpdate(sql1);
            String sql2 ="SELECT LAST_INSERT_ID()";
            ResultSet rs = st.executeQuery(sql2);
            while (rs.next()) {                
                int orderId = rs.getInt(1);
                return orderId;
            }
//            if (i == 1) {
//                return true;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
//        
//        return false;
            return 0;
    }

    @Override
    public boolean updateOrderTable(int orderId, int amount) {
        try {
            Statement st = conn.createStatement();
            String sql = "UPDATE orders SET amount="+amount+" WHERE order_id="+orderId;
            
            int i = st.executeUpdate(sql);
            
            if (i == 1) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(orderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public List<Invoice> invoiceResult(int orderId) {
        try {
            Statement st = conn.createStatement();
            List<Invoice> invoices = new ArrayList<>();
            String sql = "SELECT orders.order_date, books.title, orders_detail.isbn, orders_detail.quantity, SUM(orders_detail.quantity * books.price) total "
                    + "FROM orders_detail "
                    + "INNER JOIN orders ON orders_detail.order_id = orders.order_id "
                    + "INNER JOIN books ON orders_detail.isbn = books.isbn "
                    + "WHERE orders_detail.order_id = "+orderId+" "
                    + "GROUP BY orders_detail.isbn";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Invoice data = new Invoice();
                
                Date date = rs.getDate("order_date");
                String bookTitle = rs.getString("title");
                int isbn = rs.getInt("isbn");
                int qty = rs.getInt("quantity");
                int total = rs.getInt("total");
                
                data.setOrderDate(date);
                data.setTitle(bookTitle);
                data.setIsbn(isbn);
                data.setQty(qty);
                data.setTotal(total);
                
                invoices.add(data);
                
            }
            
            return invoices;
        } catch (SQLException ex) {
            Logger.getLogger(orderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
