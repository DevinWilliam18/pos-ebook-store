/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.dao;

import bookShop.model.UserEntity;

/**
 *
 * @author USER
 */
public interface UserDao {

    public boolean loginUser(UserEntity userEntity);
    public int getUserId(String userName);
}
