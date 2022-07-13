


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.controller;


import bookShop.dao.UserDao;
import bookShop.model.UserEntity;
import bookShop.view.Login;
import bookShop.database.DatabaseBookshop;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class LoginController {
    
    
    private UserEntity userEntity;
    private Login loginView;
    
    public LoginController(Login loginView, UserEntity userEntity) {
        this.loginView = loginView;
        this.userEntity = userEntity;
    }

    public LoginController() {
        
    }
    
    
    public boolean checkLogin(String name, String pass) throws SQLException, ClassNotFoundException{
        UserDao userDao = DatabaseBookshop.getUserDao();
        userEntity = new UserEntity();
        userEntity.setUserName(name);
        userEntity.setUserPass(pass);
        
        if (userDao.loginUser(userEntity)) {
            return true;
        }
        
        return false;
    }
    
    public int getUserId(String userName) throws ClassNotFoundException, SQLException{
        UserDao userDao = DatabaseBookshop.getUserDao();
        int userId = userDao.getUserId(userName);
        return userId;
        
    }
}
