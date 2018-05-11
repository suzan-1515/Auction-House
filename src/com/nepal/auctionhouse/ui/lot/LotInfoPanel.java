/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.ui.lot;

import com.nepal.auctionhouse.ui.lot.all.LotAllPanel;
import com.nepal.auctionhouse.entity.user.UserInfo;
import com.nepal.auctionhouse.ui.lot.assign.LotAssignedPanel;
import com.nepal.auctionhouse.ui.lot.sold.LotSoldPanel;
import com.nepal.auctionhouse.ui.lot.unassigned.LotUnassignedPanel;
import com.nepal.auctionhouse.util.ColorUtils;
import com.nepal.auctionhouse.util.ComponentUtils;
import com.nepal.auctionhouse.util.Logy;

/**
 *
 * @author Suzn
 */
public class LotInfoPanel extends javax.swing.JPanel {

    private static final int PANEL_ALL = 1;
    private static final int PANEL_UNASSIGNED = 2;
    private static final int PANEL_ASSIGNED = 3;
    private static final int PANEL_SOLD = 4;

    private LotAllPanel lotAllPanel;
    private LotUnassignedPanel lotUnassignedPanel;
    private LotAssignedPanel lotAssignedPanel;
    private LotSoldPanel lotSoldPanel;

    private final UserInfo userInfo;

    private int activePanel;

    /**
     * Creates new form LotInfoPanel
     *
     * @param userInfo
     */
    public LotInfoPanel(UserInfo userInfo) {
        super();
        initComponents();
        this.userInfo = userInfo;

        Logy.d("Admin lot panel initialized");
        ComponentUtils.addToPanel(this.centerPanel, getLotAllPanel());
        activePanel = PANEL_ALL;
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
        topPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        tabMenuPanel = new javax.swing.JPanel();
        tabsPanel = new javax.swing.JPanel();
        allMenuButton = new javax.swing.JButton();
        unassignedMenuButton = new javax.swing.JButton();
        assignedMenuButton = new javax.swing.JButton();
        soldMenuButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(300, 80));
        setLayout(new java.awt.BorderLayout());

        centerPanel.setLayout(new java.awt.CardLayout());
        add(centerPanel, java.awt.BorderLayout.CENTER);

        topPanel.setBackground(new java.awt.Color(249, 249, 249));
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new java.awt.Dimension(300, 80));
        topPanel.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        titlePanel.setBackground(new java.awt.Color(255, 255, 255));
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new java.awt.Dimension(40, 80));
        titlePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel1.setOpaque(false);

        title.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        title.setText("Lot");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titlePanel.add(jPanel1);

        topPanel.add(titlePanel);

        tabMenuPanel.setOpaque(false);
        tabMenuPanel.setPreferredSize(new java.awt.Dimension(300, 70));

        tabsPanel.setBackground(new java.awt.Color(255, 255, 255));
        tabsPanel.setOpaque(false);

        allMenuButton.setBackground(selectedMenuColor);
        allMenuButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        allMenuButton.setForeground(new java.awt.Color(255, 255, 255));
        allMenuButton.setText("All");
        allMenuButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(215, 215, 215))));
        allMenuButton.setContentAreaFilled(false);
        allMenuButton.setOpaque(true);
        allMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(allMenuButton.getBackground(),selectedMenuColor)){
                    allMenuButton.setBackground(hoverMenuColor);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(allMenuButton.getBackground(),selectedMenuColor)){
                    allMenuButton.setBackground(defaultMenuColor);
                }
            }
        });
        allMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allMenuButton.setBackground(selectedMenuColor);
                allMenuButton.setForeground(selectedMenuTextColor);
                allMenuButton.setBorder(selectedMenuBorder);

                unassignedMenuButton.setBackground(defaultMenuColor);
                unassignedMenuButton.setBorder(defaultMenuBorder);
                unassignedMenuButton.setForeground(defaultMenuTextColor);
                assignedMenuButton.setBackground(defaultMenuColor);
                assignedMenuButton.setBorder(defaultMenuBorder);
                assignedMenuButton.setForeground(defaultMenuTextColor);
                soldMenuButton.setBackground(defaultMenuColor);
                soldMenuButton.setBorder(defaultMenuBorder);
                soldMenuButton.setForeground(defaultMenuTextColor);

                allMenuButtonActionPerformed(evt);
            }
        });

        unassignedMenuButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unassignedMenuButton.setForeground(new java.awt.Color(62, 40, 40));
        unassignedMenuButton.setText("Unassigned");
        unassignedMenuButton.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 51, 0)));
        unassignedMenuButton.setContentAreaFilled(false);
        unassignedMenuButton.setOpaque(true);
        unassignedMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(unassignedMenuButton.getBackground(),selectedMenuColor)){
                    unassignedMenuButton.setBackground(hoverMenuColor);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(unassignedMenuButton.getBackground(),selectedMenuColor)){
                    unassignedMenuButton.setBackground(defaultMenuColor);
                }
            }
        });
        unassignedMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unassignedMenuButton.setBackground(selectedMenuColor);
                unassignedMenuButton.setForeground(selectedMenuTextColor);
                unassignedMenuButton.setBorder(selectedMenuBorder);

                allMenuButton.setBackground(defaultMenuColor);
                allMenuButton.setBorder(defaultMenuBorder);
                allMenuButton.setForeground(defaultMenuTextColor);
                assignedMenuButton.setBackground(defaultMenuColor);
                assignedMenuButton.setBorder(defaultMenuBorder);
                assignedMenuButton.setForeground(defaultMenuTextColor);
                soldMenuButton.setBackground(defaultMenuColor);
                soldMenuButton.setBorder(defaultMenuBorder);
                soldMenuButton.setForeground(defaultMenuTextColor);

                unassignedMenuButtonActionPerformed(evt);
            }
        });

        assignedMenuButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        assignedMenuButton.setForeground(new java.awt.Color(62, 40, 40));
        assignedMenuButton.setText("Assigned");
        assignedMenuButton.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 51, 0)));
        assignedMenuButton.setContentAreaFilled(false);
        assignedMenuButton.setOpaque(true);
        assignedMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(assignedMenuButton.getBackground(),selectedMenuColor)){
                    assignedMenuButton.setBackground(hoverMenuColor);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(assignedMenuButton.getBackground(),selectedMenuColor)){
                    assignedMenuButton.setBackground(defaultMenuColor);
                }
            }
        });
        assignedMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignedMenuButton.setBackground(selectedMenuColor);
                assignedMenuButton.setForeground(selectedMenuTextColor);
                assignedMenuButton.setBorder(selectedMenuBorder);

                allMenuButton.setBackground(defaultMenuColor);
                allMenuButton.setBorder(defaultMenuBorder);
                allMenuButton.setForeground(defaultMenuTextColor);
                unassignedMenuButton.setBackground(defaultMenuColor);
                unassignedMenuButton.setBorder(defaultMenuBorder);
                unassignedMenuButton.setForeground(defaultMenuTextColor);
                soldMenuButton.setBackground(defaultMenuColor);
                soldMenuButton.setBorder(defaultMenuBorder);
                soldMenuButton.setForeground(defaultMenuTextColor);

                assignedMenuButtonActionPerformed(evt);
            }
        });

        soldMenuButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        soldMenuButton.setForeground(new java.awt.Color(62, 40, 40));
        soldMenuButton.setText("Sold");
        soldMenuButton.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 51, 0)));
        soldMenuButton.setContentAreaFilled(false);
        soldMenuButton.setOpaque(true);
        soldMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(soldMenuButton.getBackground(),selectedMenuColor)){
                    soldMenuButton.setBackground(hoverMenuColor);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!ColorUtils.isSame(soldMenuButton.getBackground(),selectedMenuColor)){
                    soldMenuButton.setBackground(defaultMenuColor);
                }
            }
        });
        soldMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soldMenuButton.setBackground(selectedMenuColor);
                soldMenuButton.setForeground(selectedMenuTextColor);
                soldMenuButton.setBorder(selectedMenuBorder);

                allMenuButton.setBackground(defaultMenuColor);
                allMenuButton.setBorder(defaultMenuBorder);
                allMenuButton.setForeground(defaultMenuTextColor);
                unassignedMenuButton.setBackground(defaultMenuColor);
                unassignedMenuButton.setBorder(defaultMenuBorder);
                unassignedMenuButton.setForeground(defaultMenuTextColor);
                assignedMenuButton.setBackground(defaultMenuColor);
                assignedMenuButton.setBorder(defaultMenuBorder);
                assignedMenuButton.setForeground(defaultMenuTextColor);

                soldMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabsPanelLayout = new javax.swing.GroupLayout(tabsPanel);
        tabsPanel.setLayout(tabsPanelLayout);
        tabsPanelLayout.setHorizontalGroup(
            tabsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(allMenuButton)
                .addGap(0, 0, 0)
                .addComponent(unassignedMenuButton)
                .addGap(0, 0, 0)
                .addComponent(assignedMenuButton)
                .addGap(0, 0, 0)
                .addComponent(soldMenuButton)
                .addContainerGap())
        );

        tabsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {allMenuButton, assignedMenuButton, soldMenuButton, unassignedMenuButton});

        tabsPanelLayout.setVerticalGroup(
            tabsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsPanelLayout.createSequentialGroup()
                .addGroup(tabsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(assignedMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unassignedMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soldMenuButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {allMenuButton, assignedMenuButton, soldMenuButton, unassignedMenuButton});

        javax.swing.GroupLayout tabMenuPanelLayout = new javax.swing.GroupLayout(tabMenuPanel);
        tabMenuPanel.setLayout(tabMenuPanelLayout);
        tabMenuPanelLayout.setHorizontalGroup(
            tabMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabMenuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tabMenuPanelLayout.setVerticalGroup(
            tabMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMenuPanelLayout.createSequentialGroup()
                .addComponent(tabsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        topPanel.add(tabMenuPanel);

        add(topPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void allMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Logy.d("Lot all menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getLotAllPanel());
        activePanel = PANEL_ALL;
        onViewActivated();
    }

    private void unassignedMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Logy.d("Lot unassigned menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getLotUnassignedPanel());
        activePanel = PANEL_UNASSIGNED;
        onViewActivated();
    }

    private void assignedMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Logy.d("Lot assigned menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getLotAssignedPanel());
        activePanel = PANEL_ASSIGNED;
        onViewActivated();
    }

    private void soldMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Logy.d("Lot sold menu clicked");
        ComponentUtils.addToPanel(this.centerPanel, getLotSoldPanel());
        activePanel = PANEL_SOLD;
        onViewActivated();
    }

    private LotAllPanel getLotAllPanel() {
        if (lotAllPanel == null) {
            Logy.d("Creating lot all panel instance");
            lotAllPanel = new LotAllPanel(userInfo);
        }

        return lotAllPanel;
    }

    private LotUnassignedPanel getLotUnassignedPanel() {
        if (lotUnassignedPanel == null) {
            Logy.d("Creating lot unassigned panel instance");
            lotUnassignedPanel = new LotUnassignedPanel(userInfo);
        }

        return lotUnassignedPanel;
    }

    private LotAssignedPanel getLotAssignedPanel() {
        if (lotAssignedPanel == null) {
            Logy.d("Creating lot assigned panel instance");
            lotAssignedPanel = new LotAssignedPanel(userInfo);
        }

        return lotAssignedPanel;
    }

    private LotSoldPanel getLotSoldPanel() {
        if (lotSoldPanel == null) {
            Logy.d("Creating lot sold panel instance");
            lotSoldPanel = new LotSoldPanel(userInfo);
        }

        return lotSoldPanel;
    }

    public void onViewActivated() {
        Logy.d("Lotinfo panel onViewActivated");

        switch (activePanel) {
            case PANEL_ALL:
                lotAllPanel.onViewActivated();
                break;
            case PANEL_UNASSIGNED:
                lotUnassignedPanel.onViewActivated();
                break;
            case PANEL_ASSIGNED:
                lotAssignedPanel.onViewActivated();
                break;
            case PANEL_SOLD:
                lotSoldPanel.onViewActivated();
                break;
            default:
                break;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allMenuButton;
    private java.awt.Color selectedMenuColor = new java.awt.Color(149, 149, 149);
    private java.awt.Color hoverMenuColor = new java.awt.Color(234,84,68);
    private java.awt.Color defaultMenuColor = new java.awt.Color(214,217,223);
    private java.awt.Color defaultMenuTextColor = new java.awt.Color(1, 1, 1);
    private java.awt.Color selectedMenuTextColor = new java.awt.Color(255, 255, 255);
    private javax.swing.border.Border selectedMenuBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(215, 215, 215)));
    private javax.swing.border.Border defaultMenuBorder = javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 51, 0));
    private javax.swing.JButton assignedMenuButton;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton soldMenuButton;
    private javax.swing.JPanel tabMenuPanel;
    private javax.swing.JPanel tabsPanel;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton unassignedMenuButton;
    // End of variables declaration//GEN-END:variables

}
