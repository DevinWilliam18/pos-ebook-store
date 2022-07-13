/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.impl;

import bookShop.dao.CustomerDao;
import bookShop.model.CustomerEntity;
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
public class customerDaoImpl implements CustomerDao{
    private Connection connection;

    private Statement statement;
    public customerDaoImpl(Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public boolean deleteCustomer(int cusId)
    {
        try {
            Statement st = connection.createStatement();
            String sql = "DELETE FROM customers WHERE customer_id="+cusId;
            int i = statement.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(customerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertCustomer(CustomerEntity customerEntity)
    {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO customers (customer_id, customer_name, customer_phone) VALUES("+
                    customerEntity.getIdCus()+
                    ", '"+customerEntity.getCusName()+"'"+
                    ", '"+customerEntity.getCusPhone()+"')";
            int i = st.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<CustomerEntity> getAllCustomers()
    {
        try {
            List<CustomerEntity> allCustomers = new ArrayList<>();
            statement = connection.createStatement();
            String query = "SELECT * FROM customers ORDER BY customer_id";
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {                
                CustomerEntity customerEntity = new CustomerEntity();
                int cusId = rs.getInt("customer_id");
                String cusName = rs.getString("customer_name");
                String cusPhone = rs.getString("customer_phone");
                
                customerEntity.setIdCus(cusId);
                customerEntity.setCusName(cusName);
                customerEntity.setCusPhone(cusPhone);
                
                
                allCustomers.add(customerEntity);
            }
            return allCustomers;
        } catch (SQLException ex) {
            Logger.getLogger(customerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String getCustomerName(int cusId) {
        try {
            String cusName;
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM customers where customer_id="+cusId;
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                cusName = rs.getString("customer_name");
                return cusName;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(customerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
