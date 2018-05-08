/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.util;

import com.sujan.lms.common.util.Logy;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Suzn
 */
public class ComponentUtils {

    public static void addToPanel(JPanel root, JPanel child) {
        for (Component component : root.getComponents()) {
            if (component.equals(child)) {
                Logy.d("panel already attacted");
                return;
            }
        }

        Logy.d("Attaching panel");
        root.removeAll();
        root.add(child);
        root.revalidate();
        root.repaint();
    }

}
