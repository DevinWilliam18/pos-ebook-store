/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.view;

import bookShop.controller.BooksController;
import bookShop.controller.CustomerController;
import bookShop.controller.LoginController;
import bookShop.controller.OrderController;
import bookShop.controller.OrderDetailController;
import bookShop.model.BookEntity;
import bookShop.model.Invoice;
import bookShop.model.OrderDetailEntity;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author USER
 */
public class Transaction extends javax.swing.JFrame {

    /**
     * Creates new form Transaction
     */
   
    private BooksController booksController;
    private BookEntity bookEntity;
    private int isbnOrder;
    private int qtyBook;
    private int detail_order_id;
    private int order_id;
    private int isbn;
    private int qty;
    private int qtyBookUpdate;
    static final String fileName = "src/Report/BookReport.jasper";
    static final String outFile = "src/Report/BookReports.pdf";
    
    
    public Transaction() {
        
        initComponents();
        books();
        booksForTransaction();
        detailTable();
        
        
    }
    public void setUserName(String userName){
        try {
            LoginController lgController = new LoginController();
            int userId = lgController.getUserId(userName);
            
            UserIdPane.setText(String.valueOf(userId));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUserId(int userId){
        
            UserIdPane.setText(String.valueOf(userId));
        
    }
    
    public void setOrderId(int orderId){
       
            txtOrderId.setText(String.valueOf(orderId));
        
    }
    
    public void setCustomerName(int cusId){
        try {
            CustomerController cusControl = new CustomerController();
            
            String nameResult = cusControl.cusName(cusId);
            txtCustomerName.setText(nameResult);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method untuk ambil data dari controller
    
    public void tableInvoice(){
        try {
            DefaultTableModel model1 = (DefaultTableModel) invoiceTable.getModel();
            OrderController orderController = new OrderController();
            int orderId = Integer.parseInt(txtOrderId.getText());
            List<Invoice> invoiceResultList = orderController.invoiceList(orderId);
            List<Integer> totalPrice = new ArrayList<>();
            Vector row = new Vector();
            for (Invoice invoice : invoiceResultList) {
                Vector vec = new Vector();
                vec.add(invoice.getOrderDate());
                vec.add(invoice.getTitle());
                vec.add(invoice.getIsbn());
                vec.add(invoice.getQty());
                vec.add(invoice.getTotal());
                row.add(vec);
                totalPrice.add(invoice.getTotal());
            }
            
            for (int i = 0; i < row.size(); i++) {
                model1.addRow((Vector) row.get(i));
            }
            int sum = 0;
            for (int i = 0; i < totalPrice.size(); i++) {
                sum+=totalPrice.get(i);
            }
            labelTotal.setText("Rp "+String.valueOf(sum)+",00");
            
            OrderController odController = new OrderController();
            odController.updateOrder(orderId, sum);
            
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void books(){
        DefaultTableModel tableModel = new DefaultTableModel();
        booksController = new BooksController();
        try {
            tableModel = (DefaultTableModel) booksTable.getModel();
            Vector row = new Vector();
            List<BookEntity> bookList = booksController.getAllBooks();
            for (BookEntity bookEntity1 : bookList) {
                Vector vec = new Vector();
                vec.add(bookEntity1.getIsbn());
                vec.add(bookEntity1.getBookTitle());
                vec.add(bookEntity1.getBookStock());
                vec.add(bookEntity1.getBookPrice());
                vec.add(bookEntity1.getBookAuthor());
                vec.add(bookEntity1.getBookTypeId());
                row.add(vec);
                
            }
            
            for (int i = 0; i < row.size(); i++) {
                tableModel.addRow((Vector) row.get(i));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void booksForTransaction(){
        DefaultTableModel tableModel = new DefaultTableModel();
        booksController = new BooksController();
        try {
            tableModel = (DefaultTableModel) bookTransactionsTable.getModel();
            Vector row = new Vector();
            List<BookEntity> bookList = booksController.getAllBooks();
            for (BookEntity bookEntity1 : bookList) {
                Vector vec = new Vector();
                vec.add(bookEntity1.getIsbn());
                vec.add(bookEntity1.getBookTitle());
                vec.add(bookEntity1.getBookStock());
                vec.add(bookEntity1.getBookPrice());
                vec.add(bookEntity1.getBookAuthor());
                vec.add(bookEntity1.getBookTypeId());
                row.add(vec);
                
            }
            
            for (int i = 0; i < row.size(); i++) {
                tableModel.addRow((Vector) row.get(i));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void booksForSearchByIsbn(){
        try {
            // TODO add your handling code here:
            int bookIdSearch = Integer.valueOf(bookSearch.getText().toString());
            BooksController booksController = new BooksController();
            
            
            DefaultTableModel myModel = new DefaultTableModel();
            
            myModel = (DefaultTableModel) bookTransactionsTable.getModel();
            myModel.setRowCount(0);
            
            
            
            Vector row = new Vector();
            List<BookEntity> listBook = booksController.getBookByIsbn(bookIdSearch);
            
            for (BookEntity bookEntity1 : listBook) {
                Vector vec = new Vector();
                vec.add(bookEntity1.getIsbn());
                vec.add(bookEntity1.getBookTitle());
                vec.add(bookEntity1.getBookStock());
                vec.add(bookEntity1.getBookPrice());
                vec.add(bookEntity1.getBookAuthor());
                vec.add(bookEntity1.getBookTypeId());
                row.add(vec);
            }
            for (int i = 0; i < row.size(); i++) {
                myModel.addRow((Vector) row.get(i));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void detailTable(){
        try {
            DefaultTableModel tableModel = new DefaultTableModel();
            OrderDetailController orderDetailController = new OrderDetailController();
            
            tableModel = (DefaultTableModel) orderDetailTable.getModel();
            Vector row = new Vector();
            List<OrderDetailEntity> detailList = orderDetailController.getAllDetails();
            
            for (OrderDetailEntity orderDetailEntity : detailList) {
                Vector vector = new Vector();
                vector.add(orderDetailEntity.getOrderDetailId());
                vector.add(orderDetailEntity.getOrderId());
                vector.add(orderDetailEntity.getOrderDetailIsbn());
                vector.add(orderDetailEntity.getOrderDetailQty());
                
                row.add(vector);
            }
            for (int i = 0; i < row.size(); i++) {
                tableModel.addRow((Vector) row.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchOrderId(){
        try {
            int orderId = Integer.valueOf(txtOrderId.getText());
            OrderDetailController detailController = new OrderDetailController();
            List<OrderDetailEntity> detailResults = detailController.getAllByOrderId(orderId);
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel = (DefaultTableModel) orderDetailTable.getModel();
            tableModel.setRowCount(0);
            Vector row = new Vector();
            
            for (OrderDetailEntity detailEntity1 : detailResults) {
                Vector vec = new Vector();
                vec.add(detailEntity1.getOrderDetailId());
                vec.add(detailEntity1.getOrderId());
                vec.add(detailEntity1.getOrderDetailIsbn());
                vec.add(detailEntity1.getOrderDetailQty());
                
                row.add(vec);
                
            }
            for (int i = 0; i < row.size(); i++) {
                tableModel.addRow((Vector) row.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchOrderDetailId(){
        try {
            int orderDetailId = Integer.valueOf(txtOrderDetailId.getText());
            OrderDetailController detailController = new OrderDetailController();
            List<OrderDetailEntity> detailResults = detailController.getAllByDetailsId(orderDetailId);
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel = (DefaultTableModel) orderDetailTable.getModel();
            tableModel.setRowCount(0);
            Vector row = new Vector();
            
            for (OrderDetailEntity detailEntity1 : detailResults) {
                Vector vec = new Vector();
                vec.add(detailEntity1.getOrderDetailId());
                vec.add(detailEntity1.getOrderId());
                vec.add(detailEntity1.getOrderDetailIsbn());
                vec.add(detailEntity1.getOrderDetailQty());
                
                row.add(vec);
                
            }
            for (int i = 0; i < row.size(); i++) {
                tableModel.addRow((Vector) row.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortBookByCategory(){
        try {
            // TODO add your handling code here:
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put(categoryBookOrder.getItemAt(0), 1);
            map.put(categoryBookOrder.getItemAt(1), 2);
            map.put(categoryBookOrder.getItemAt(2), 3);
            map.put(categoryBookOrder.getItemAt(3), 4);
            map.put(categoryBookOrder.getItemAt(4), 5);
            
            int value = map.get(categoryBookOrder.getSelectedItem());
            BooksController booksController = new BooksController();
            List<BookEntity> results = booksController.getBookByCategory(value);
            Vector row = new Vector();
            DefaultTableModel model1 = (DefaultTableModel) bookTransactionsTable.getModel();
            model1.setRowCount(0);
            
            for (BookEntity result : results) {
                Vector vec = new Vector();
                vec.add(result.getIsbn());
                vec.add(result.getBookTitle());
                vec.add(result.getBookStock());
                vec.add(result.getBookPrice());
                vec.add(result.getBookAuthor());
                vec.add(result.getBookTypeId());
                
                row.add(vec);
            }
            
            for (int i = 0; i < row.size(); i++) {
                model1.addRow((Vector) row.get(i));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HomeTabbedPane = new javax.swing.JTabbedPane();
        TransactionPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        TransCartPanel = new javax.swing.JPanel();
        newOrderFrame = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bookTransactionsTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        categoryBookOrder = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderDetailTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        btnOrderIdSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtOrderDetailId = new javax.swing.JTextField();
        btnSearchOrderDetail = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        spinnerQty = new javax.swing.JSpinner();
        btnPrintInvoice = new javax.swing.JButton();
        btnAddBooksOrder = new javax.swing.JButton();
        btnUpdateDetail = new javax.swing.JButton();
        txtCustomerName = new javax.swing.JTextField();
        bookSearch = new javax.swing.JTextField();
        btnBookSearch = new javax.swing.JButton();
        btnDeleteDetail = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtInvoiceOrderId = new javax.swing.JLabel();
        txtInvoiceCustomerName = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtInvoiceUserId = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        BookListPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtIsbn = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtAuthors = new javax.swing.JTextField();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        saveBooks = new javax.swing.JButton();
        deleteBooks = new javax.swing.JButton();
        updateBooks = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        booksTable = new javax.swing.JTable();
        btnReport = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UserIdPane = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        HomeTabbedPane.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));

        TransCartPanel.setBackground(new java.awt.Color(204, 204, 255));

        newOrderFrame.setText("New Order");
        newOrderFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderFrameActionPerformed(evt);
            }
        });

        bookTransactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "isbn", "Title", "Stock", "Price", "Author", "Type_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookTransactionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTransactionsTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(bookTransactionsTable);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel10.setText("Books");

        categoryBookOrder.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        categoryBookOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Humor", "Novel", "Science", "Education", "Technology" }));
        categoryBookOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBookOrderActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("Category");

        orderDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "detail_order_id", "order_id", "isbn", "quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        orderDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderDetailTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(orderDetailTable);

        jLabel12.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 16)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/shopping-cart.png"))); // NOI18N
        jLabel12.setText("Cart");

        jPanel2.setBackground(new java.awt.Color(246, 245, 245));

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setText("Order Id");

        btnOrderIdSearch.setBackground(new java.awt.Color(255, 204, 0));
        btnOrderIdSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnOrderIdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnOrderIdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderIdSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrderIdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOrderIdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Order detail");

        btnSearchOrderDetail.setBackground(new java.awt.Color(255, 204, 0));
        btnSearchOrderDetail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSearchOrderDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnSearchOrderDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchOrderDetailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Qty");

        spinnerQty.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        btnPrintInvoice.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPrintInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printer.png"))); // NOI18N
        btnPrintInvoice.setText("Print");
        btnPrintInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintInvoiceActionPerformed(evt);
            }
        });

        btnAddBooksOrder.setBackground(new java.awt.Color(0, 0, 255));
        btnAddBooksOrder.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnAddBooksOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnAddBooksOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/shopping-cart.png"))); // NOI18N
        btnAddBooksOrder.setText("Add");
        btnAddBooksOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBooksOrderActionPerformed(evt);
            }
        });

        btnUpdateDetail.setBackground(new java.awt.Color(0, 204, 51));
        btnUpdateDetail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnUpdateDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        btnUpdateDetail.setText("Update");
        btnUpdateDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDetailActionPerformed(evt);
            }
        });

        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        btnBookSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnBookSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookSearchActionPerformed(evt);
            }
        });

        btnDeleteDetail.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteDetail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnDeleteDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Delete.png"))); // NOI18N
        btnDeleteDetail.setText("Delete");
        btnDeleteDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransCartPanelLayout = new javax.swing.GroupLayout(TransCartPanel);
        TransCartPanel.setLayout(TransCartPanelLayout);
        TransCartPanelLayout.setHorizontalGroup(
            TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                        .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newOrderFrame)
                            .addGroup(TransCartPanelLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TransCartPanelLayout.createSequentialGroup()
                                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(bookSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBookSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(categoryBookOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddBooksOrder))))
                        .addContainerGap(241, Short.MAX_VALUE))
                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                        .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransCartPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                                        .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtOrderDetailId))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearchOrderDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(TransCartPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(spinnerQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUpdateDetail))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TransCartPanelLayout.createSequentialGroup()
                                            .addComponent(btnPrintInvoice)
                                            .addGap(23, 23, 23)
                                            .addComponent(btnDeleteDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        TransCartPanelLayout.setVerticalGroup(
            TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransCartPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(newOrderFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBookSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookSearch)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryBookOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddBooksOrder))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransCartPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransCartPanelLayout.createSequentialGroup()
                                .addComponent(txtOrderDetailId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(spinnerQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(TransCartPanelLayout.createSequentialGroup()
                                .addComponent(btnSearchOrderDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateDetail)
                                .addGap(18, 18, 18)
                                .addGroup(TransCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDeleteDetail)
                                    .addComponent(btnPrintInvoice)))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cart", new javax.swing.ImageIcon(getClass().getResource("/Icons/Basket.png")), TransCartPanel); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Title", "ISBN", "Quantity", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(invoiceTable);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        txtInvoiceOrderId.setBackground(new java.awt.Color(0, 0, 0));
        txtInvoiceOrderId.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtInvoiceOrderId.setForeground(new java.awt.Color(255, 255, 0));
        txtInvoiceOrderId.setText("jLabel17");

        txtInvoiceCustomerName.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtInvoiceCustomerName.setForeground(new java.awt.Color(255, 255, 0));
        txtInvoiceCustomerName.setText("jLabel18");

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        txtInvoiceUserId.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        txtInvoiceUserId.setText("jLabel16");

        jLabel15.setText("USER ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtInvoiceUserId))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtInvoiceUserId)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel16.setText("ORDER ID");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel17.setText("Customer name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtInvoiceOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtInvoiceCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInvoiceCustomerName)
                            .addComponent(txtInvoiceOrderId))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 51));
        jLabel18.setText("TOTAL");

        labelTotal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(51, 51, 51));
        labelTotal.setText("300.900");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotal)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(173, 173, 173))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );

        jTabbedPane1.addTab("Invoice", new javax.swing.ImageIcon(getClass().getResource("/Icons/invoice.png")), jPanel4); // NOI18N

        javax.swing.GroupLayout TransactionPanelLayout = new javax.swing.GroupLayout(TransactionPanel);
        TransactionPanel.setLayout(TransactionPanelLayout);
        TransactionPanelLayout.setHorizontalGroup(
            TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        TransactionPanelLayout.setVerticalGroup(
            TransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 161, Short.MAX_VALUE))
        );

        HomeTabbedPane.addTab("Transaction", new javax.swing.ImageIcon(getClass().getResource("/Icons/money-transfer.png")), TransactionPanel); // NOI18N

        BookListPanel.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(246, 245, 245));

        cbCategory.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel2.setText("ISBN");

        jLabel3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel3.setText("Stock");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel4.setText("Price");

        jLabel5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel5.setText("Authors");

        jLabel6.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel6.setText("Title");

        jLabel7.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel7.setText("Type");

        saveBooks.setBackground(new java.awt.Color(0, 0, 255));
        saveBooks.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        saveBooks.setForeground(new java.awt.Color(255, 255, 255));
        saveBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Add.png"))); // NOI18N
        saveBooks.setText("Save");
        saveBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBooksActionPerformed(evt);
            }
        });

        deleteBooks.setBackground(new java.awt.Color(255, 0, 0));
        deleteBooks.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        deleteBooks.setForeground(new java.awt.Color(255, 255, 255));
        deleteBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Delete.png"))); // NOI18N
        deleteBooks.setText("Delete");
        deleteBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBooksActionPerformed(evt);
            }
        });

        updateBooks.setBackground(new java.awt.Color(0, 204, 51));
        updateBooks.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        updateBooks.setForeground(new java.awt.Color(255, 255, 255));
        updateBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        updateBooks.setText("Update");
        updateBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBooksActionPerformed(evt);
            }
        });

        booksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Title", "Stock", "Price", "Author", "book_type_id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        booksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                booksTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(booksTable);

        btnReport.setBackground(new java.awt.Color(204, 153, 0));
        btnReport.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printer.png"))); // NOI18N
        btnReport.setText("Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrice)
                                    .addComponent(txtStock)
                                    .addComponent(txtAuthors, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(txtTitle)))
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(deleteBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtIsbn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReport)
                    .addComponent(saveBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(deleteBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateBooks)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel8.setText("Books");

        jScrollPane2.setViewportView(UserIdPane);

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel14.setText("User ID");

        javax.swing.GroupLayout BookListPanelLayout = new javax.swing.GroupLayout(BookListPanel);
        BookListPanel.setLayout(BookListPanelLayout);
        BookListPanelLayout.setHorizontalGroup(
            BookListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookListPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(BookListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookListPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(BookListPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        BookListPanelLayout.setVerticalGroup(
            BookListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookListPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(BookListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );

        HomeTabbedPane.addTab("BookList", new javax.swing.ImageIcon(getClass().getResource("/Icons/book.png")), BookListPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HomeTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(HomeTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoryBookOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBookOrderActionPerformed
        sortBookByCategory();
        
    }//GEN-LAST:event_categoryBookOrderActionPerformed

    private void newOrderFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderFrameActionPerformed
        // TODO add your handling code here:
        NewOrder newOrder = new NewOrder();
        newOrder.setUserId(Integer.valueOf(UserIdPane.getText()));
        newOrder.setVisible(true);
    }//GEN-LAST:event_newOrderFrameActionPerformed

    private void saveBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBooksActionPerformed
        // TODO add your handling code here:
        BooksController booksController = new BooksController();
        BookEntity bk = new BookEntity();
        
        bk.setIsbn((Integer.parseInt(txtIsbn.getText())));
        bk.setBookTitle((txtTitle.getText()));
        bk.setBookStock((Integer.parseInt(txtStock.getText())));
        bk.setBookPrice((Integer.parseInt(txtPrice.getText())));
        bk.setBookAuthor((txtAuthors.getText()));
        bk.setBookTypeId((Integer.parseInt((String) cbCategory.getSelectedItem())));
        if (booksController.checkBookInserted(bk)) {
                javax.swing.JOptionPane.showMessageDialog(this, "You have successfully inserted item");
                DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
                model.setRowCount(0);
                books();
                
                DefaultTableModel model2 = (DefaultTableModel) bookTransactionsTable.getModel();
                model2.setRowCount(0);
                booksForTransaction();
        }else{
            javax.swing.JOptionPane.showMessageDialog(this, "Failed!");
        }
    }//GEN-LAST:event_saveBooksActionPerformed

    private void updateBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBooksActionPerformed
        try {
            // TODO add your handling code here:
            BooksController booksController = new BooksController();
            BookEntity bk = new BookEntity();
            
            
            bk.setIsbn((Integer.parseInt(txtIsbn.getText())));
            bk.setBookTitle((txtTitle.getText()));
            bk.setBookStock((Integer.parseInt(txtStock.getText())));
            bk.setBookPrice((Integer.parseInt(txtPrice.getText())));
            bk.setBookAuthor((txtAuthors.getText()));
            bk.setBookTypeId((Integer.parseInt((String) cbCategory.getSelectedItem())));
            
            if (booksController.checkBookUpdated(bk)) {
                javax.swing.JOptionPane.showMessageDialog(this, "You have successfully updated item");
                DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
                model.setRowCount(0);
                books();
                
                DefaultTableModel model2 = (DefaultTableModel) bookTransactionsTable.getModel();
                model2.setRowCount(0);
                booksForTransaction();
            
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "Failed!");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_updateBooksActionPerformed

    private void booksTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksTableMouseClicked
        // TODO add your handling code here:
        int index = booksTable.getSelectedRow();
        TableModel model = booksTable.getModel();
        
        int isbn = Integer.parseInt(model.getValueAt(index, 0).toString());
        String title = model.getValueAt(index, 1).toString();
        int stock = Integer.parseInt(model.getValueAt(index, 2).toString());
        int price = Integer.parseInt(model.getValueAt(index, 3).toString());
        String author = model.getValueAt(index, 4).toString();
        int type = Integer.parseInt(model.getValueAt(index, 5).toString());
        txtIsbn.setText(String.valueOf(isbn));
        txtTitle.setText(title);
        txtStock.setText(String.valueOf(stock));
        txtPrice.setText(String.valueOf(price));
        txtAuthors.setText(author);
        
    }//GEN-LAST:event_booksTableMouseClicked

    private void deleteBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBooksActionPerformed
        // TODO add your handling code here:
        int isbn = Integer.parseInt(txtIsbn.getText());
        BooksController booksController = new BooksController();
        if (booksController.checkBookDeleted(isbn)) {
            javax.swing.JOptionPane.showMessageDialog(this, "You have successfully deleted item");
            DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
            model.setRowCount(0);
            books();
            
            DefaultTableModel model2 = (DefaultTableModel) bookTransactionsTable.getModel();
            model2.setRowCount(0);
            booksForTransaction();
                
        }else{
                javax.swing.JOptionPane.showMessageDialog(this, "Failed!");
                
        }
    }//GEN-LAST:event_deleteBooksActionPerformed

    private void bookTransactionsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTransactionsTableMouseClicked
        // TODO add your handling code here:
        int index = bookTransactionsTable.getSelectedRow();
        TableModel model = bookTransactionsTable.getModel();
        isbnOrder = (int) model.getValueAt(index, 0);
        qtyBook = (int) model.getValueAt(index, 2);
        
        
        
    }//GEN-LAST:event_bookTransactionsTableMouseClicked

    private void btnAddBooksOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBooksOrderActionPerformed
        try {
            // TODO add your handling code here:      
            BooksController booksController = new BooksController();
            OrderDetailController orderDetailController = new OrderDetailController();
            
            if (booksController.checkReducedBookQty(isbnOrder, qtyBook)) {
                if (orderDetailController.checkInsertDetail(Integer.parseInt(txtOrderId.getText().toString()), isbnOrder, 1)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "You have successfully inserted item");
                    
                    DefaultTableModel model = (DefaultTableModel) bookTransactionsTable.getModel();
                    model.setRowCount(0);
                    booksForTransaction();
                    
                    DefaultTableModel model2 = (DefaultTableModel) orderDetailTable.getModel();
                    model2.setRowCount(0);
                    detailTable();
                    
                    DefaultTableModel model3 = (DefaultTableModel) booksTable.getModel();
                    model3.setRowCount(0);
                    books();
                    
                }  
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "You failed!");
            }
            
            // reduce theBookQty = 1
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAddBooksOrderActionPerformed

    private void btnUpdateDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDetailActionPerformed
        
        try {
            
            OrderDetailController detailController = new OrderDetailController();
            qtyBookUpdate = Integer.parseInt(spinnerQty.getValue().toString());
            if (qty == qtyBookUpdate) {
                
                javax.swing.JOptionPane.showMessageDialog(this, "The item has not chaged");
            }else if(detailController.checkUpdateDetail(detail_order_id, isbn, qty, qtyBookUpdate)){
                searchOrderId();
                
                DefaultTableModel model1 = (DefaultTableModel) booksTable.getModel();
                model1.setRowCount(0);
                books();
                
                DefaultTableModel model2 = (DefaultTableModel) bookTransactionsTable.getModel();
                model2.setRowCount(0);
                booksForTransaction();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btnUpdateDetailActionPerformed

    private void btnBookSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookSearchActionPerformed
        booksForSearchByIsbn();
        
    }//GEN-LAST:event_btnBookSearchActionPerformed

    private void btnSearchOrderDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchOrderDetailActionPerformed
        searchOrderDetailId();
    }//GEN-LAST:event_btnSearchOrderDetailActionPerformed

    private void btnOrderIdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderIdSearchActionPerformed
        searchOrderId();
    }//GEN-LAST:event_btnOrderIdSearchActionPerformed

    private void btnDeleteDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDetailActionPerformed
        try {
            // TODO add your handling code here:
            
            OrderDetailController detailController = new OrderDetailController();
            qtyBookUpdate = Integer.parseInt(spinnerQty.getValue().toString());
            if (detailController.checkDeleteDetail(detail_order_id, isbn, qty)) {
                
                searchOrderId();
                
                DefaultTableModel model1 = (DefaultTableModel) booksTable.getModel();
                model1.setRowCount(0);
                books();
                
                DefaultTableModel model2 = (DefaultTableModel) bookTransactionsTable.getModel();
                model2.setRowCount(0);
                booksForTransaction();
                
                javax.swing.JOptionPane.showMessageDialog(this, "You have successfully deleted item");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteDetailActionPerformed

    private void orderDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderDetailTableMouseClicked
        // TODO add your handling code here:
        int index = orderDetailTable.getSelectedRow();
        TableModel model = orderDetailTable.getModel();
        
        
        txtOrderId.setText("");
        txtOrderDetailId.setText("");
        detail_order_id = Integer.parseInt(model.getValueAt(index, 0).toString());
        order_id = Integer.parseInt(model.getValueAt(index, 1).toString());
        isbn = Integer.parseInt(model.getValueAt(index, 2).toString());
        qty = Integer.parseInt(model.getValueAt(index, 3).toString());
        
        txtOrderDetailId.setText(String.valueOf(detail_order_id));
        txtOrderId.setText(String.valueOf(order_id));
        
        
    }//GEN-LAST:event_orderDetailTableMouseClicked

    private void btnPrintInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintInvoiceActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) invoiceTable.getModel();
        model2.setRowCount(0);
        tableInvoice();
        txtInvoiceUserId.setText(UserIdPane.getText());
        txtInvoiceCustomerName.setText(txtCustomerName.getText());
        txtInvoiceOrderId.setText(txtOrderId.getText());
        
        
        
    }//GEN-LAST:event_btnPrintInvoiceActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        try {
            // TODO add your handling code here:
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_penjualan","root","devin1234");
            
            
            File file = new File(fileName);
            JasperReport jasperDesign = (JasperReport) JRLoader.loadObject(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, null,conn);
            JasperViewer.viewReport(jasperPrint,false);
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btnReportActionPerformed

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaction().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BookListPanel;
    private javax.swing.JTabbedPane HomeTabbedPane;
    private javax.swing.JPanel TransCartPanel;
    private javax.swing.JPanel TransactionPanel;
    private javax.swing.JTextPane UserIdPane;
    private javax.swing.JTextField bookSearch;
    private javax.swing.JTable bookTransactionsTable;
    private javax.swing.JTable booksTable;
    private javax.swing.JButton btnAddBooksOrder;
    private javax.swing.JButton btnBookSearch;
    private javax.swing.JButton btnDeleteDetail;
    private javax.swing.JButton btnOrderIdSearch;
    private javax.swing.JButton btnPrintInvoice;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSearchOrderDetail;
    private javax.swing.JButton btnUpdateDetail;
    private javax.swing.JComboBox<String> categoryBookOrder;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JButton deleteBooks;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JButton newOrderFrame;
    private javax.swing.JTable orderDetailTable;
    private javax.swing.JButton saveBooks;
    private javax.swing.JSpinner spinnerQty;
    private javax.swing.JTextField txtAuthors;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JLabel txtInvoiceCustomerName;
    private javax.swing.JLabel txtInvoiceOrderId;
    private javax.swing.JLabel txtInvoiceUserId;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtOrderDetailId;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JButton updateBooks;
    // End of variables declaration//GEN-END:variables
}
