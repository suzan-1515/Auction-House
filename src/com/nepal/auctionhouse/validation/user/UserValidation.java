/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nepal.auctionhouse.validation.user;

import com.nepal.auctionhouse.validation.BaseValidation;
import com.nepal.auctionhouse.widget.Alert;
import com.nepal.auctionhouse.util.Logy;
import java.awt.Component;

/**
 *
 * @author Suzn
 */
public class UserValidation extends BaseValidation {

    public UserValidation(Component component) {
        super(component);
    }

    public boolean isUserFormValid(String name, String username, String password) {
        Logy.d("Validating user insert form");
        if (isStringEmptyOrNull(name)) {
            Logy.d("User name not valid");
            Alert.showError(component, "Name field cannot be empty.");
            return false;
        }

        return isUserFormValid(username, password);
    }

    public boolean isUserFormValid(String username, String password) {
        String s = username.trim();
        if (isStringEmptyOrNull(s)) {
            Logy.d("User username not valid");
            Alert.showError(component, "Username field cannot be empty.");
            return false;
        } else if (s.length() < 6 || s.length() > 10) {
            Logy.d("User username not valid");
            Alert.showError(component, "Username must be of between 6 to 10 characters");
            return false;
        }

        s = password.trim();
        if (isStringEmptyOrNull(s)) {
            Logy.d("User password not valid");
            Alert.showError(component, "Password field cannot be empty.");
            return false;
        } else if (s.length() < 8 || s.length() > 18) {
            Logy.d("User password not valid");
            Alert.showError(component, "Password number must be of between 8 to 18 characters");
            return false;
        }

        return true;
    }

    public boolean isLoginValid(String user, String pass) {
        Logy.d("Validating user login form");
        if (isStringEmptyOrNull(user)) {
            Logy.d("User username not valid");
            Alert.showError(component, "Username field cannot be empty.");
            return false;
        }

        if (isStringEmptyOrNull(pass)) {
            Logy.d("User password not valid");
            Alert.showError(component, "Password field cannot be empty.");
            return false;
        }

        return true;
    }

    public boolean isPasswordChangeValid(String pass, String confirmPass) {
        if (isStringEmptyOrNull(pass)) {
            Logy.d("User password not valid");
            Alert.showError(component, "Password field cannot be empty.");
            return false;
        }

        if (!pass.equals(confirmPass)) {
            Logy.d("User password do not match");
            Alert.showError(component, "Password do not match");
            return false;
        } else if (pass.length() < 8 || pass.length() > 18) {
            Logy.d("User password not valid");
            Alert.showError(component, "Password number must be of between 8 to 18 characters");
            return false;
        }

        return true;
    }

}
