/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.lot.all;

import com.nepal.auctionhouse.bll.lot.LotBLL;
import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.LotParams;
import com.nepal.auctionhouse.ui.BaseUserPanel;
import com.nepal.auctionhouse.util.Logy;
import com.nepal.auctionhouse.util.Utils;
import com.nepal.auctionhouse.view.LotView;
import com.nepal.auctionhouse.widget.Alert;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suzn
 */
public final class LotAllPanel extends BaseUserPanel implements LotView<Lot> {

    private final UserInfo userInfo;

    /**
     * Creates new form LotPanel
     *
     * @param userInfo
     */
    public LotAllPanel(UserInfo userInfo) {
        initComponents();
        this.userInfo = userInfo;
        setupUserView(userInfo);
        Logy.d("lot all panel initialized");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centerPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        centerSubPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        addLotButton = new javax.swing.JButton();
        updateLotButton = new javax.swing.JButton();
        deleteLotButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        centerPanel.setLayout(new java.awt.CardLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        searchPanel.setBackground(new java.awt.Color(249, 249, 249));
        searchPanel.setPreferredSize(new java.awt.Dimension(367, 70));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        searchPanel.setLayout(flowLayout1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 65));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_blue.png"))); // NOI18N

        searchTextField.setBackground(new java.awt.Color(249, 249, 249));
        searchTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchTextField.setToolTipText("Search for lot number,description,type..");
        searchTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(39, 26, 252)));
        searchTextField.setMinimumSize(new java.awt.Dimension(200, 15));
        searchTextField.setOpaque(false);
        searchTextField.setPreferredSize(new java.awt.Dimension(200, 15));
        searchTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                String text = searchTextField.getText();
                System.out.println(text);
                if (text.trim().length() == 0) {
                    ((javax.swing.table.TableRowSorter<javax.swing.table.TableModel>)table.getRowSorter()).setRowFilter(null);
                } else {
                    ((javax.swing.table.TableRowSorter<javax.swing.table.TableModel>)table.getRowSorter()).setRowFilter(javax.swing.RowFilter.regexFilter("^(?i)" + text));
                }
                table.repaint();
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                String text = searchTextField.getText();
                if (text.trim().length() == 0) {
                    ((javax.swing.table.TableRowSorter<javax.swing.table.TableModel>)table.getRowSorter()).setRowFilter(null);
                } else {
                    ((javax.swing.table.TableRowSorter<javax.swing.table.TableModel>)table.getRowSorter()).setRowFilter(javax.swing.RowFilter.regexFilter("^(?i)" + text));
                }
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                //not needed: throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        searchPanel.add(jPanel2);

        jPanel4.add(searchPanel, java.awt.BorderLayout.PAGE_START);

        centerSubPanel.setLayout(new java.awt.CardLayout());

        javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        tableModel.addColumn(LotParams.ID.toUpperCase());
        tableModel.addColumn(LotParams.DESCRIPTION.toUpperCase());
        tableModel.addColumn(LotParams.TYPE.toUpperCase());
        tableModel.addColumn(LotParams.RESERVE_PRICE.toUpperCase());
        tableModel.addColumn(LotParams.HAMMER_PRICE.toUpperCase());
        tableModel.addColumn(LotParams.STATE.toUpperCase());
        table.setModel(tableModel);
        table.setRowHeight(26);
        table.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(table);
        javax.swing.table.TableRowSorter<javax.swing.table.TableModel> rowSorter = new javax.swing.table.TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        centerSubPanel.add(jScrollPane1, "card2");

        jPanel4.add(centerSubPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jPanel3.setOpaque(false);

        addLotButton.setText("Add New");
        addLotButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addLotButton.setPreferredSize(new java.awt.Dimension(80, 40));
        addLotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLotButtonActionPerformed(evt);
            }
        });
        jPanel3.add(addLotButton);

        updateLotButton.setText("Update");
        updateLotButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateLotButton.setPreferredSize(new java.awt.Dimension(80, 40));
        updateLotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLotButtonActionPerformed(evt);
            }
        });
        jPanel3.add(updateLotButton);

        deleteLotButton.setText("Delete");
        deleteLotButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteLotButton.setPreferredSize(new java.awt.Dimension(80, 40));
        deleteLotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLotButtonActionPerformed(evt);
            }
        });
        jPanel3.add(deleteLotButton);

        bottomPanel.add(jPanel3);

        jPanel4.add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        centerPanel.add(jPanel4, "card2");

        add(centerPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void updateLotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLotButtonActionPerformed
        Logy.d("update lot button clicked");
        int row = table.getSelectedRow();
        if (row > -1) {
            try {
                Lot u = getBeanFromRow(table.getRowSorter().convertRowIndexToModel(row));
                if (u != null) {
                    u.setUser(userInfo);
                    if (LotBLL.isLotAddedByUser(u)) {
                        if (!LotBLL.isLotSold(u)) {
                            LotUpdateDialog lotUpdateDialog = new LotUpdateDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                                    true, u);
                            lotUpdateDialog.setItemUpdatedListener((Lot lot) -> {
                                updateLotRowData(lot, row);
                            });
                            lotUpdateDialog.setVisible(true);

                        } else {
                            Logy.d("Sold lot cannot be updated");
                            Alert.showWarning(this, "Sold lot cannot be modified.");
                        }
                    } else {
                        Logy.d("Cannot update this lot.");
                        Alert.showWarning(this, "You does not have permission to update this lot.");
                    }
                }
            } catch (SQLException ex) {
                Logy.e(ex);
                Alert.showError(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_updateLotButtonActionPerformed

    private void addLotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLotButtonActionPerformed
        Logy.d("add lot button clicked");
        LotInsertDialog lotInsertDialog = new LotInsertDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, userInfo);
        lotInsertDialog.setItemAddedListener((Lot lot) -> {
            onLotRowDataAdd(lot);
        });

        lotInsertDialog.setVisible(true);
    }//GEN-LAST:event_addLotButtonActionPerformed

    private void deleteLotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteLotButtonActionPerformed
        Logy.d("delete lot button clicked");
        if (Utils.isTableRowSelected(table)) {
            if (Alert.showDeleteConfirmDialog(this) == JOptionPane.YES_OPTION) {
                int row = table.getSelectedRow();
                int id = Utils.getIdFromTable(table, table.getRowSorter().convertRowIndexToModel(row));
                try {
                    Lot lot = new Lot(id);
                    lot.setUser(userInfo);
                    if (LotBLL.isLotAddedByUser(lot)) {
                        try {
                            LotBLL.deleteLot(lot);
                            removeLotData(lot, row);

                        } catch (RecordNotFoundException | SQLException ex) {
                            Logy.e(ex);
                            Alert.showError(this, ex.getMessage());
                        }
                    } else {
                        Logy.d("Cannot update this lot.");
                        Alert.showWarning(this, "You does not have permission to update this lot.");
                    }
                } catch (SQLException ex) {
                    Logy.e(ex);
                    Alert.showError(this, ex.getMessage());
                }

            }
        }
    }//GEN-LAST:event_deleteLotButtonActionPerformed

    @Override
    public final void loadTableData() {
        SwingUtilities.invokeLater(() -> {
            Logy.d("Loading lot from database for first Time");
            try {
                List<Lot> lotList = LotBLL.getAllLot();
                this.fillTableData(lotList);

            } catch (SQLException ex) {
                Logy.e(ex);
                Alert.showError(this, ex.getMessage());
            }

        });
    }

    @Override
    public final void fillTableData(List<Lot> list
    ) {
        createTableModel();
        list.stream().forEach((lot) -> {
            onLotRowDataAdd(lot);
        });
    }

    @Override
    public void onLotRowDataAdd(Lot lot
    ) {
        ((DefaultTableModel) table.getModel()).insertRow(0, new Object[]{
            lot.getId(),
            lot.getDescription(),
            lot.getType().getTitle(),
            lot.getReservePrice(),
            lot.getState().getTitle()
        });
    }

    private void updateLotRowData(Lot lot, int row) {
        ((DefaultTableModel) table.getModel()).setValueAt(lot.getDescription(), row, 1);
        ((DefaultTableModel) table.getModel()).setValueAt(lot.getType().getTitle(), row, 2);
        ((DefaultTableModel) table.getModel()).setValueAt(lot.getReservePrice(), row, 3);
        ((DefaultTableModel) table.getModel()).setValueAt(lot.getState().getTitle(), row, 4);
    }

    private void removeLotData(Lot u, int row) {
        onLotDataRemove(u);
        ((DefaultTableModel) table.getModel()).removeRow(row);
    }

    @Override
    public void onLotDataRemove(Lot u) {

    }

    private Lot getBeanFromRow(int row) throws SQLException {
        int id = (int) table.getModel().getValueAt(row, 0);
        return LotBLL.getLotById(id);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLotButton;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerSubPanel;
    private javax.swing.JButton deleteLotButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable table;
    private javax.swing.JButton updateLotButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onViewActivated() {
        this.loadTableData();
    }

    @Override
    public void createTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        tableModel.addColumn(LotParams.LotAllTableHeader.ID);
        tableModel.addColumn(LotParams.LotAllTableHeader.DESCRIPTION);
        tableModel.addColumn(LotParams.LotAllTableHeader.TYPE);
        tableModel.addColumn(LotParams.LotAllTableHeader.RESERVE_PRICE);
        tableModel.addColumn(LotParams.LotAllTableHeader.STATE);

        table.setModel(tableModel);
        javax.swing.table.TableRowSorter<javax.swing.table.TableModel> rowSorter = new javax.swing.table.TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
    }

    @Override
    protected void setupAdminView() {
    }

    @Override
    protected void setupUserView() {
    }

}
