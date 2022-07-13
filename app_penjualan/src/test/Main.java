/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bookShop.dao.OrderDao;
import bookShop.dao.OrderDetailDao;
import bookShop.database.DatabaseBookshop;
import bookShop.model.Invoice;
import bookShop.model.OrderDetailEntity;
import bookShop.model.OrderEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        try {
            OrderDao orderDao = DatabaseBookshop.getOrderDao();
            
            List<Invoice> myDetails = orderDao.invoiceResult(23);
            
            for (Invoice myDetail : myDetails) {
                System.out.print(myDetail.getOrderDate()+ " ");
                System.out.print(myDetail.getTitle()+ " ");
                System.out.print(myDetail.getIsbn()+ " ");
                System.out.print(myDetail.getQty()+ " ");
                System.out.print(myDetail.getTotal()+ " ");
                System.out.println();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
