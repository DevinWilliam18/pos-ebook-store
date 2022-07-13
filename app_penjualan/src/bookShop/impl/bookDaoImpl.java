/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.impl;

import bookShop.dao.BookDao;
import bookShop.model.BookEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class bookDaoImpl implements BookDao{

    private Connection conn;
    private Statement statement;
    public bookDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        
        try {
            List<BookEntity> allBooks = new ArrayList<BookEntity>();
            
            statement = conn.createStatement();
            
            String query = "SELECT * FROM books ORDER BY isbn ASC";
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                BookEntity bookEntity = new BookEntity();
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                int stock = rs.getInt("stock");
                int price = rs.getInt("price");
                String author = rs.getString("author");
                int book_type = rs.getInt("book_type_id");
                
                //Masukan ke dalam model
                bookEntity.setIsbn(isbn);
                bookEntity.setBookTitle(title);
                bookEntity.setBookStock(stock);
                bookEntity.setBookPrice(price);
                bookEntity.setBookAuthor(author);
                bookEntity.setBookTypeId(book_type);
                
                
                //Tambahkan ke dalam list
                allBooks.add(bookEntity);
            }    
                return allBooks;
            
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public boolean insertBook(BookEntity bookEntity) {
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO books (isbn,title,stock,price,author,book_type_id) VALUES("+
                    bookEntity.getIsbn()+
                    ", '"+bookEntity.getBookTitle()+"'"+
                     ", "+bookEntity.getBookStock()+
                    ", "+bookEntity.getBookPrice()+
                    ", '"+bookEntity.getBookAuthor()+"'"+
                    ", "+bookEntity.getBookTypeId()+")";
            int i = statement.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteBook(int isbn) {
        try {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM books WHERE isbn="+ isbn;
            int i = statement.executeUpdate(sql);
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateBookEntity(BookEntity bookEntity) {
        
        try {
            Statement statement = conn.createStatement();
            String sql = "UPDATE books SET title='"+bookEntity.getBookTitle()+
                    "', stock="+bookEntity.getBookStock()+
                    ", price="+bookEntity.getBookPrice()+
                    ", author='"+bookEntity.getBookAuthor()+
                    "', book_type_id="+bookEntity.getBookTypeId()+
                    " WHERE isbn="+bookEntity.getIsbn();
             int rs = statement.executeUpdate(sql);
             
             if(rs == 1){
                 return true;
             }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
            return false;
    }

    @Override
    public boolean reduceBookQty(int isbn, int qtyUpdated) {
        try {
            Statement st = conn.createStatement();
            String sql = "UPDATE books SET stock="+qtyUpdated+" WHERE isbn="+isbn;
            
            int rs = statement.executeUpdate(sql);
            
            if (rs == 1) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<BookEntity> getBookId(int id) {
        try {
            List<BookEntity> getById = new ArrayList<>();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM books WHERE isbn="+id;
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {                
                BookEntity bk = new BookEntity();
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                int Stock = rs.getInt("stock");
                int Price = rs.getInt("price");
                String authors = rs.getString("author");
                int type_id = rs.getInt("book_type_id");
                
                
                bk.setIsbn(isbn);
                bk.setBookTitle(title);
                bk.setBookStock(Stock);
                bk.setBookPrice(Price);
                bk.setBookAuthor(authors);
                bk.setBookTypeId(type_id);
                
                getById.add(bk);
                
            }
            
            return getById;
            
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<BookEntity> getBookByCategory(int category) {
        
        try {
            Statement st = conn.createStatement();
            List<BookEntity> getByCategory = new ArrayList<>();
            String sql = "SELECT * FROM books WHERE book_type_id="+category;
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BookEntity bk = new BookEntity();
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                int Stock = rs.getInt("stock");
                int Price = rs.getInt("price");
                String authors = rs.getString("author");
                int type_id = rs.getInt("book_type_id");
                
                
                bk.setIsbn(isbn);
                bk.setBookTitle(title);
                bk.setBookStock(Stock);
                bk.setBookPrice(Price);
                bk.setBookAuthor(authors);
                bk.setBookTypeId(type_id);
                
                getByCategory.add(bk);
            }
            
            return getByCategory;
        } catch (SQLException ex) {
            Logger.getLogger(bookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
