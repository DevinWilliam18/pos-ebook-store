/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.controller;

import bookShop.dao.CustomerDao;
import bookShop.database.DatabaseBookshop;
import bookShop.model.CustomerEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CustomerController {

    public CustomerController() {
    }
    
    public List<CustomerEntity> getAllCus()
    {
        try {
            CustomerDao cusDao = DatabaseBookshop.getCustomerDao();
            List<CustomerEntity> customerResulList = cusDao.getAllCustomers();
            
            return customerResulList;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean checkCustomerDeleted(int cusId){
        try {
            CustomerDao customerDao = DatabaseBookshop.getCustomerDao();
            
            if (customerDao.deleteCustomer(cusId)) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean checkCustomerInserted(CustomerEntity customerEntity){
        try {
            CustomerDao customerDao = DatabaseBookshop.getCustomerDao();
            if (customerDao.insertCustomer(customerEntity)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String cusName(int customerId) throws ClassNotFoundException, SQLException{
        CustomerDao cusDao = DatabaseBookshop.getCustomerDao();
        
        String name = cusDao.getCustomerName(customerId);
            
        return name;
        
    }
    
}
