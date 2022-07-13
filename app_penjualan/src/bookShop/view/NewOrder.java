/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookShop.view;

import bookShop.controller.CustomerController;
import bookShop.controller.OrderController;
import bookShop.dao.OrderDao;
import bookShop.model.BookEntity;
import bookShop.model.CustomerEntity;
import bookShop.model.OrderEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author USER
 */
public class NewOrder extends javax.swing.JFrame {

    private int userId;
    public NewOrder() {
        initComponents();
        customers();
    }

    public void setUserId(int userId){
        this.userId = userId;
        
    }
    public void customers(){
        DefaultTableModel model = new DefaultTableModel();
        CustomerController customerController = new CustomerController();
        
        
        model = (DefaultTableModel) CustomerTables.getModel();
        
        Vector row = new Vector();
        List<CustomerEntity> customerList = customerController.getAllCus();
        
        for (CustomerEntity customerEntity : customerList) {
            Vector vector= new Vector();
            vector.add(customerEntity.getIdCus());
            vector.add(customerEntity.getCusName());
            vector.add(customerEntity.getCusPhone());
            row.add(vector);
            
        }
        for (int i = 0; i < row.size(); i++) {
                model.addRow((Vector) row.get(i));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTables = new javax.swing.JTable();
        txtCusId = new javax.swing.JTextField();
        txtCusName = new javax.swing.JTextField();
        txtCusPhone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/avatar.png"))); // NOI18N
        jLabel2.setText("NEW ORDER");

        CustomerTables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Id", "Customer Name", "Customer Phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CustomerTables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerTablesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CustomerTables);

        txtCusId.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtCusName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtCusPhone.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel1.setText("Customer Id");

        jLabel3.setText("Customer Name");

        jLabel4.setText("Customer Phone");

        addButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Add.png"))); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Delete.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        createButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        createButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/create.png"))); // NOI18N
        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close.png"))); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(createButton)
                        .addGap(18, 18, 18)
                        .addComponent(closeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCusId)
                                .addComponent(txtCusName, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addComponent(txtCusPhone)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCusId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void CustomerTablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerTablesMouseClicked
        // TODO add your handling code here:
        int index = CustomerTables.getSelectedRow();
        TableModel model = CustomerTables.getModel();
        
        int customerId = (int) model.getValueAt(index,0);
        String customerName = model.getValueAt(index, 1).toString();
        String customerPhone = model.getValueAt(index, 2).toString();
        
        txtCusId.setText(String.valueOf(customerId));
        txtCusName.setText(customerName);
        txtCusPhone.setText(customerPhone);
        
    }//GEN-LAST:event_CustomerTablesMouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        CustomerController customerController = new CustomerController();
        CustomerEntity customerEntity = new CustomerEntity();
        int cusId = Integer.valueOf(txtCusId.getText());
        String customerName = txtCusName.getText();
        String customerPhone = txtCusPhone.getText();
        
        customerEntity.setIdCus(cusId);
        customerEntity.setCusName(customerName);
        customerEntity.setCusPhone(customerPhone);
        
        if (customerController.checkCustomerInserted(customerEntity)) {
            javax.swing.JOptionPane.showMessageDialog(this, "You have successfully inserted a customer");
            DefaultTableModel tableModel = (DefaultTableModel) CustomerTables.getModel();
            tableModel.setRowCount(0);
            customers();
        }else{
            javax.swing.JOptionPane.showMessageDialog(this, "You failed!");
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        CustomerController customerController = new CustomerController();
        int cusId = Integer.valueOf(txtCusId.getText());
        if (customerController.checkCustomerDeleted(cusId)) {
            javax.swing.JOptionPane.showMessageDialog(this, "You have successfully deleted a customer");
            DefaultTableModel tableModel = (DefaultTableModel) CustomerTables.getModel();
            tableModel.setRowCount(0);
            customers();
        }else{
            javax.swing.JOptionPane.showMessageDialog(this, "You failed!");
        }
        
        
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        try {
            // TODO add your handling code here:
            int tempId = userId;
            OrderController orderController = new OrderController();
            OrderEntity orderEntity = new OrderEntity();
            int customerId = Integer.valueOf(txtCusId.getText());
            
            orderEntity.setCustomerId(customerId);
            orderEntity.setUserId(tempId);
            orderEntity.setAmount(0);
            int orderId = orderController.createNewOrder(orderEntity);
            this.setVisible(false);
            
            Transaction trans = new Transaction();
            trans.setCustomerName(customerId);
            trans.setUserId(tempId);
            trans.setOrderId(orderId);
            trans.setVisible(true);            
                
            
        } catch (SQLException ex) {
            Logger.getLogger(NewOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
    }//GEN-LAST:event_createButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CustomerTables;
    private javax.swing.JButton addButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCusId;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtCusPhone;
    // End of variables declaration//GEN-END:variables
}
