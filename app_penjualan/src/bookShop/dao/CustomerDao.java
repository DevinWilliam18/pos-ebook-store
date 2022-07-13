/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.dao;

import bookShop.model.CustomerEntity;
import java.util.List;

/**
 *
 * @author USER
 */
public interface CustomerDao {
    String getCustomerName(int cusId);
    boolean deleteCustomer(int cusId);
    boolean insertCustomer(CustomerEntity customerEntity);
    List<CustomerEntity> getAllCustomers();
}
