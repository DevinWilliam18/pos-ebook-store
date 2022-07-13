/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.dao;

import bookShop.model.BookEntity;
import java.util.List;

/**
 *
 * @author USER
 */
public interface BookDao {
    public List<BookEntity> getAllBooks();
    public boolean insertBook(BookEntity bookEntity);
    public boolean deleteBook(int isbn);
    public boolean updateBookEntity(BookEntity bookEntity);
    public boolean reduceBookQty(int isbn, int qtyUpdated);
    public List<BookEntity> getBookId(int id);
    public List<BookEntity> getBookByCategory(int category);
    
}
