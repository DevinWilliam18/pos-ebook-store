/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.controller;

import bookShop.dao.BookDao;
import bookShop.database.DatabaseBookshop;
import java.util.List;
import bookShop.model.BookEntity;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class BooksController {

    public BooksController() {
    }
    
    
    public List<BookEntity> getAllBooks() throws SQLException, ClassNotFoundException{
        BookDao bookDao = DatabaseBookshop.getBookDao();
        
        List<BookEntity> results = bookDao.getAllBooks();
        
        return results;
    }
    public boolean checkBookUpdated(BookEntity bookEntity) throws SQLException, ClassNotFoundException{
        BookDao bookDao = DatabaseBookshop.getBookDao();
        if (bookDao.updateBookEntity(bookEntity)) {
            return true;
        }
        
        return false;
        
    }
    
    public boolean checkBookDeleted(int isbn){
        try {
            BookDao bookDao = DatabaseBookshop.getBookDao();
            if (bookDao.deleteBook(isbn)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean checkBookInserted(BookEntity bookEntity)
    {
        try {
            BookDao bookDao = DatabaseBookshop.getBookDao();
            if (bookDao.insertBook(bookEntity)) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean checkReducedBookQty(int isbn, int qty) throws SQLException, ClassNotFoundException{
        int qtyUpdated = qty-1;
        BookDao bookDao = DatabaseBookshop.getBookDao();
        if (bookDao.reduceBookQty(isbn, qtyUpdated)) {
            return true;
        }
        return false;
    }
    
    public List<BookEntity> getBookByIsbn(int bookIsbn) throws SQLException, ClassNotFoundException{
        BookDao bookDao = DatabaseBookshop.getBookDao();
        
        List<BookEntity> allBooksByIsbn = bookDao.getBookId(bookIsbn);
        
        return allBooksByIsbn;
        
    }
    
    public List<BookEntity> getBookByCategory(int categoryId) throws ClassNotFoundException, SQLException{
        BookDao bookDao = DatabaseBookshop.getBookDao();
        
        List<BookEntity> allBookEntity = bookDao.getBookByCategory(categoryId);
        
        return allBookEntity;
    }
}
