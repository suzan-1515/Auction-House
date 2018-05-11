/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.auction;

import com.nepal.auctionhouse.bll.auction.AuctionBLL;
import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.params.AuctionParams;
import com.nepal.auctionhouse.ui.BaseUserPanel;
import com.nepal.auctionhouse.util.Utils;
import com.nepal.auctionhouse.view.AuctionView;
import com.nepal.auctionhouse.widget.Alert;
import com.nepal.auctionhouse.util.Logy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suzn
 */
public final class AuctionPanel extends BaseUserPanel
        implements AuctionView<Auction> {

    private List<Auction> auctionList;
    private final UserInfo userInfo;

    /**
     * Creates new form AuctionPanel
     *
     * @param userInfo
     */
    public AuctionPanel(UserInfo userInfo) {
        initComponents();
        this.userInfo = userInfo;
        setupUserView(userInfo);
        auctionList = new ArrayList<>();
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
        addAuctionButton = new javax.swing.JButton();
        updateAuctionButton = new javax.swing.JButton();
        deleteAuctionButton = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();

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
        searchTextField.setToolTipText("Search for auction no,date,slot,state..");
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

        tableModel.addColumn(AuctionParams.ID.toUpperCase());
        tableModel.addColumn(AuctionParams.DATE.toUpperCase());
        tableModel.addColumn(AuctionParams.SLOT.toUpperCase());
        tableModel.addColumn(AuctionParams.VENUE.toUpperCase());
        tableModel.addColumn(AuctionParams.STATE.toUpperCase());
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

        addAuctionButton.setText("Add New");
        addAuctionButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addAuctionButton.setPreferredSize(new java.awt.Dimension(80, 40));
        addAuctionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAuctionButtonActionPerformed(evt);
            }
        });
        jPanel3.add(addAuctionButton);

        updateAuctionButton.setText("Update");
        updateAuctionButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updateAuctionButton.setPreferredSize(new java.awt.Dimension(80, 40));
        updateAuctionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAuctionButtonActionPerformed(evt);
            }
        });
        jPanel3.add(updateAuctionButton);

        deleteAuctionButton.setText("Delete");
        deleteAuctionButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteAuctionButton.setPreferredSize(new java.awt.Dimension(80, 40));
        deleteAuctionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAuctionButtonActionPerformed(evt);
            }
        });
        jPanel3.add(deleteAuctionButton);

        bottomPanel.add(jPanel3);

        jPanel4.add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        centerPanel.add(jPanel4, "card2");

        add(centerPanel, java.awt.BorderLayout.CENTER);

        topPanel.setBackground(new java.awt.Color(249, 249, 249));
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new java.awt.Dimension(367, 80));
        topPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new java.awt.Dimension(120, 60));

        title.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        title.setText("Auction");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        topPanel.add(titlePanel);

        add(topPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addAuctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAuctionButtonActionPerformed
        AuctionInsertDialog auctionInsertDialog = new AuctionInsertDialog((JFrame) SwingUtilities.getWindowAncestor(this), true);
        auctionInsertDialog.setItemAddedListener((Auction auction) -> {
            appendAuctionData(auction);
        });
        auctionInsertDialog.setVisible(true);
    }//GEN-LAST:event_addAuctionButtonActionPerformed

    private void updateAuctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAuctionButtonActionPerformed
        int row = table.getSelectedRow();
        if (row > -1) {
            Auction s = getBeanFromRow(table.getRowSorter().convertRowIndexToModel(row));
            if (s != null) {
                try {
                    if (AuctionBLL.isUpcomingAuction(s)) {
                        AuctionUpdateDialog auctionUpdateDialog = new AuctionUpdateDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                                true, s);
                        auctionUpdateDialog.setItemUpdatedListener((Auction auction) -> {
                            updateAuctionData(auction, row);
                        });
                        auctionUpdateDialog.setVisible(true);
                    } else {
                        Logy.d("Past auction cannot be modified.");
                        Alert.showInformation(this, "Past auction cannot be modified.");
                    }
                } catch (SQLException ex) {
                    Logy.e(ex);
                    Alert.showError(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_updateAuctionButtonActionPerformed

    private void deleteAuctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAuctionButtonActionPerformed
        if (Utils.isTableRowSelected(table)) {
            if (Alert.showDeleteConfirmDialog(this) == JOptionPane.YES_OPTION) {
                int row = table.getSelectedRow();
                int id = Utils.getIdFromTable(table, table.getRowSorter().convertRowIndexToModel(row));
                try {
                    Auction auction = new Auction(id);
                    AuctionBLL.deleteAuction(auction);
                    removeAuctionData(auction, row);
                } catch (RecordNotFoundException | SQLException ex) {
                    Logy.e(ex);
                    Alert.showError(this, ex.getMessage());
                }
            }

        }
    }//GEN-LAST:event_deleteAuctionButtonActionPerformed

    @Override
    public final void loadTableData() {
        SwingUtilities.invokeLater(() -> {
            if (auctionList == null || auctionList.isEmpty()) {
                Logy.d("Loading auction from database for first Time");
                try {
                    auctionList = AuctionBLL.getAllAuction();
                    this.fillTableData(auctionList);
                } catch (SQLException ex) {
                    Logy.e(ex);
                    Alert.showError(this, ex.getMessage());
                }

            } else {
                Logy.d("auction already loaded");
            }
        });

    }

    /**
     *
     * @param auctionInfoList
     */
    @Override
    public final void fillTableData(List<Auction> auctionInfoList) {
        auctionInfoList.stream().forEach((auctionInfo) -> {
            onAuctionRowDataAdd(auctionInfo);
        });

    }

    @Override
    public void onAuctionRowDataAdd(Auction auction) {
        ((DefaultTableModel) table.getModel()).insertRow(0, new Object[]{
            auction.getId(),
            auction.getDate(),
            auction.getSlot(),
            auction.getVenue(),
            AuctionBLL.getAuctionState(auction.getDate())
        });
    }

    private void appendAuctionData(Auction auction) {
        auctionList.add(auction);
        onAuctionRowDataAdd(auction);
    }

    private void removeAuctionData(Auction a, int row) {
        onAuctionDataRemove(a);
        ((DefaultTableModel) table.getModel()).removeRow(row);
    }

    /**
     *
     * @param a
     */
    @Override
    public void onAuctionDataRemove(Auction a) {
        for (Auction auth : auctionList) {
            if (auth.getId() == a.getId()) {
                auctionList.remove(auth);
                break;
            }
        }
    }

    private void updateAuctionData(Auction s, int row) {
        for (Auction auction : auctionList) {
            if (auction.getId() == s.getId()) {
                auction.setDate(s.getDate());
                auction.setSlot(s.getSlot());
                auction.setVenue(s.getVenue());
                break;
            }
        }

        updateAuctionRowData(s, row);
    }

    private void updateAuctionRowData(Auction auction, int row) {
        ((DefaultTableModel) table.getModel()).setValueAt(auction.getDate(), row, 1);
        ((DefaultTableModel) table.getModel()).setValueAt(auction.getSlot(), row, 2);
        ((DefaultTableModel) table.getModel()).setValueAt(auction.getVenue(), row, 3);
        ((DefaultTableModel) table.getModel()).setValueAt(AuctionBLL.getAuctionState(auction.getDate()), row, 4);
    }

    private Auction getBeanFromRow(int row) {
        int id = (int) table.getModel().getValueAt(row, 0);
        return auctionList.stream()
                .filter((auction) -> auction.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAuctionButton;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerSubPanel;
    private javax.swing.JButton deleteAuctionButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable table;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton updateAuctionButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onViewActivated() {
        loadTableData();
    }

    @Override
    protected void setupAdminView() {
        
    }

    @Override
    protected void setupUserView() {
        addAuctionButton.setVisible(false);
        updateAuctionButton.setVisible(false);
        deleteAuctionButton.setVisible(false);
    }

}
