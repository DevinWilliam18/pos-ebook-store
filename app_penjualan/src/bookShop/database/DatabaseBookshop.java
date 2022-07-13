/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.database;

import bookShop.dao.BookDao;
import bookShop.dao.CustomerDao;
import bookShop.dao.OrderDao;
import bookShop.dao.OrderDetailDao;
import bookShop.impl.userDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import bookShop.dao.UserDao;
import bookShop.impl.bookDaoImpl;
import bookShop.impl.customerDaoImpl;
import bookShop.impl.orderDaoImpl;
import bookShop.impl.orderDetailImpl;


public class DatabaseBookshop {
    private static Connection conn;
    private static UserDao userDao;
    private static BookDao bookDao;
    private static CustomerDao customerDao;
    private static OrderDao orderDao;
    private static OrderDetailDao orderDetailDao;
    
    
    
    public static Connection getConnection()throws SQLException, ClassNotFoundException{
        if (conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_penjualan","root","devin1234");
        }
        
        return conn;
    }
    

    public static UserDao getUserDao() throws SQLException, ClassNotFoundException{
        if (userDao == null) {
            userDao = new userDaoImpl(getConnection());
        }
        return userDao;
    }
    

    public static BookDao getBookDao() throws SQLException, ClassNotFoundException{
        if (bookDao == null) {
            bookDao = new bookDaoImpl(getConnection());
            
        }
        return bookDao;
    }
    
    public static CustomerDao getCustomerDao() throws SQLException, ClassNotFoundException{
        if (customerDao == null) {
            customerDao = new customerDaoImpl(getConnection());
        }
        return customerDao;
    }
    
    public static OrderDao getOrderDao() throws SQLException, ClassNotFoundException{
        if (orderDao == null) {
            orderDao = new orderDaoImpl(getConnection());
        }
        return orderDao;
    }
    
    public static OrderDetailDao getOrderDetailDao() throws SQLException, ClassNotFoundException{
        if (orderDetailDao == null) {
            orderDetailDao = new orderDetailImpl(getConnection());
        }
        return orderDetailDao;
    }    
}
