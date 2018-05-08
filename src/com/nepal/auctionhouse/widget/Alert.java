/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sujan.lms.common.widget;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Suzn
 */
public class Alert {

    public static void showInformation(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Information?", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Error?", JOptionPane.ERROR_MESSAGE);
    }

    public static int showDeleteConfirmDialog(Component component) {
        return JOptionPane.showConfirmDialog(component, "Are you sure you want to delete?", "Confirm?", JOptionPane.YES_NO_OPTION);
    }

}
