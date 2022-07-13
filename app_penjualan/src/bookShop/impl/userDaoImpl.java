/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.impl;

import bookShop.dao.UserDao;
import bookShop.model.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class userDaoImpl implements UserDao{

    private Connection conn;
    
    public userDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean loginUser(UserEntity userEntity) {
        try {
            PreparedStatement statement = conn.prepareCall("select user_name, user_pass from users where user_name=? and user_pass=?");
            statement.setString(1, userEntity.getUserName());
            statement.setString(2, userEntity.getUserPass());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getUserId(String userName) {
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE user_name='"+userName+"'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                return userId;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
