/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.auction;

import com.nepal.auctionhouse.bll.auction.AuctionBLL;
import com.nepal.auctionhouse.entity.Auction;
import com.nepal.auctionhouse.exception.RecordNotFoundException;
import com.nepal.auctionhouse.util.Utils;
import com.nepal.auctionhouse.validation.auction.AuctionValidation;
import com.nepal.auctionhouse.widget.Alert;
import com.nepal.auctionhouse.util.Logy;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Suzn
 */
public class AuctionUpdateDialog extends javax.swing.JDialog {

    private final AuctionValidation validation;
    private ItemUpdatedListener itemUpdatedListener;

    private final Auction auction;

    private void notifyDataSetChanged(Auction auction) {
        if (getItemUpdatedListener() != null) {
            getItemUpdatedListener().onItemUpdated(auction);
        }
    }

    private void resetFields() {
        auctionDateChooser.setSelectedDate(null);
        slotComboBox.setSelectedIndex(0);
        venueTextArea.setText(null);
    }

    private void fillFields() {
        auctionDateChooser.setSelectedDate(Utils.sqlToCalandarDate(auction.getDate()));
        slotComboBox.setSelectedItem(auction.getSlot().toUpperCase());
        venueTextArea.setText(auction.getVenue());
    }

    public interface ItemUpdatedListener {

        void onItemUpdated(Auction auction);
    }

    /**
     * Creates new form AuctionInsertDialog
     *
     * @param parent
     * @param modal
     * @param auction
     */
    public AuctionUpdateDialog(java.awt.Frame parent, boolean modal, Auction auction) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.auction = auction;
        this.fillFields();
        validation = new AuctionValidation(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auctionDateChooser = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        venueTextArea = new javax.swing.JTextArea();
        slotComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        rootPanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(67, 160, 71));
        topPanel.setPreferredSize(new java.awt.Dimension(482, 200));
        topPanel.setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setOpaque(false);
        topPanel.add(jPanel1);

        jPanel2.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(153, 153, 153)));
        jPanel3.setPreferredSize(new java.awt.Dimension(320, 100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Update Auction");
        jLabel1.setPreferredSize(new java.awt.Dimension(113, 50));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        topPanel.add(jPanel2);

        rootPanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        centerPanel.setBackground(new java.awt.Color(214, 215, 217));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel4.setPreferredSize(new java.awt.Dimension(380, 326));

        jPanel5.setOpaque(false);

        jLabel2.setText("Date");

        jLabel3.setText("Slot");

        jLabel4.setText("Venue");

        auctionDateChooser.setSelectedDate(null);
        auctionDateChooser.setCalendarPreferredSize(new java.awt.Dimension(340, 180));
        try {
            auctionDateChooser.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2018, 4, 10),
                new java.util.GregorianCalendar(2018, 4, 10))));
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    auctionDateChooser.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    venueTextArea.setColumns(20);
    venueTextArea.setRows(3);
    jScrollPane1.setViewportView(venueTextArea);

    slotComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(auctionDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(slotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(215, 215, 215))))
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {auctionDateChooser, jScrollPane1, slotComboBox});

    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(auctionDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(slotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {auctionDateChooser, jLabel2});

    jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, slotComboBox});

    jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(255, 51, 0)));
    jPanel6.setForeground(new java.awt.Color(255, 255, 255));
    jPanel6.setOpaque(false);

    cancelButton.setText("Cancel");
    cancelButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelButtonActionPerformed(evt);
        }
    });

    saveButton.setText("Save");
    saveButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    saveButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
            .addContainerGap(171, Short.MAX_VALUE)
            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(36, 36, 36))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(22, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 363, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
    centerPanel.setLayout(centerPanelLayout);
    centerPanelLayout.setHorizontalGroup(
        centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
            .addContainerGap(90, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(90, Short.MAX_VALUE))
    );
    centerPanelLayout.setVerticalGroup(
        centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(centerPanelLayout.createSequentialGroup()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 73, Short.MAX_VALUE))
    );

    rootPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

    getContentPane().add(rootPanel, "card2");

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (validation.isAuctionFormValid(auctionDateChooser.getSelectedDate(), venueTextArea.getText())) {

            auction.setDate(new Date(auctionDateChooser.getSelectedDate().getTimeInMillis()));
            auction.setSlot((String) slotComboBox.getSelectedItem());
            auction.setVenue(venueTextArea.getText());

            try {
                AuctionBLL.updateAuction(auction);
                notifyDataSetChanged(auction);
                Alert.showInformation(this, "Auction updated successfully!");

                this.dispose();
            } catch (RecordNotFoundException | SQLException ex) {
                Logy.e(ex);
                Alert.showError(this, ex.getMessage());
            }

        }
    }//GEN-LAST:event_saveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo auctionDateChooser;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> slotComboBox;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextArea venueTextArea;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the itemUpdatedListener
     */
    public ItemUpdatedListener getItemUpdatedListener() {
        return itemUpdatedListener;
    }

    /**
     * @param itemUpdatedListener the itemUpdatedListener to set
     */
    public void setItemUpdatedListener(ItemUpdatedListener itemUpdatedListener) {
        this.itemUpdatedListener = itemUpdatedListener;
    }
}
