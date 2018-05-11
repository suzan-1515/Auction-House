/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.lot.assign;

import com.nepal.auctionhouse.bll.lot.LotBLL;
import com.nepal.auctionhouse.entity.Lot;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.params.LotParams;
import com.nepal.auctionhouse.util.Logy;
import com.nepal.auctionhouse.view.LotView;
import com.nepal.auctionhouse.widget.Alert;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suzn
 */
public final class LotAssignedPanel extends JPanel implements LotView<Lot> {

    /**
     * Creates new form LotAssignedPanel
     *
     * @param userInfo
     */
    public LotAssignedPanel(UserInfo userInfo) {
        initComponents();
        Logy.d("Admin lotAssigned panel initialized");
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

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        tableModel.addColumn(LotParams.LotAssignedTableHeader.ID);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.DESCRIPTION);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.TYPE);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.RESERVE_PRICE);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.AUCTION);
        table.setModel(tableModel);
        table.setRowHeight(26);
        table.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(table);
        javax.swing.table.TableRowSorter<javax.swing.table.TableModel> rowSorter = new javax.swing.table.TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        centerSubPanel.add(jScrollPane1, "card2");

        jPanel4.add(centerSubPanel, java.awt.BorderLayout.CENTER);

        centerPanel.add(jPanel4, "card2");

        add(centerPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public final void loadTableData() {
        SwingUtilities.invokeLater(() -> {
            Logy.d("Loading lotAssigned from database");
            try {
                List<Lot> lotAssignedList = LotBLL.getAssignedLot();
                this.fillTableData(lotAssignedList);
            } catch (SQLException ex) {
                Logy.e(ex);
                Alert.showError(this, ex.getMessage());
            }

        });
    }

    @Override
    public final void fillTableData(List<Lot> list) {

        createTableModel();

        list.stream().forEach((lotAssigned) -> {
            onLotRowDataAdd(lotAssigned);
        });
    }

    @Override
    public void onLotRowDataAdd(Lot lotAssigned) {
        ((DefaultTableModel) table.getModel()).insertRow(0, new Object[]{
            lotAssigned.getId(),
            lotAssigned.getDescription(),
            lotAssigned.getType().getTitle(),
            lotAssigned.getReservePrice(),
            lotAssigned.getAuction().getId()
        });
    }

    @Override
    public void onLotDataRemove(Lot u) {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerSubPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable table;
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

        tableModel.addColumn(LotParams.LotAssignedTableHeader.ID);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.DESCRIPTION);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.TYPE);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.RESERVE_PRICE);
        tableModel.addColumn(LotParams.LotAssignedTableHeader.AUCTION);

        table.setModel(tableModel);
        javax.swing.table.TableRowSorter<javax.swing.table.TableModel> rowSorter = new javax.swing.table.TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
    }

}