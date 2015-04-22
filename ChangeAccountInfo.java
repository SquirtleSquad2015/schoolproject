package shoolprodject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class ChangeAccountInfo extends JFrame {
    private final String username;
    JLabel currentEmail = new JLabel("jonas.refsnes@gmail.com", JLabel.CENTER);
    JLabel currentPhoneNumber = new JLabel("41214904", JLabel.CENTER);
    JLabel oldPassword = new JLabel("Enter old password: ", JLabel.CENTER);
    JLabel newPassword = new JLabel("Enter new password: ", JLabel.CENTER);
    JLabel repeatPassword = new JLabel("Repeat new password: ", JLabel.CENTER);
    JPasswordField enterOldPassword = new JPasswordField(20);
    JPasswordField enterNewPassword = new JPasswordField(20);
    JPasswordField enterRepeatPassword = new JPasswordField(20);
    JButton changePassword = new JButton("Change password");
    JButton changeAccountInfoBack = new JButton("Back");
    JButton changeEmail = new JButton("Change email");
    JButton changePhoneNumber = new JButton("Change phone number");

    public ChangeAccountInfo(String username, String email, String tlf){
        this.username = username;
        currentEmail.setText(email);
        currentPhoneNumber.setText(tlf);
        LayoutManager changeInfoLayout = new BorderLayout();
        setTitle("Change account information - " + username);
        JPanel passwordPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel changePanel = new JPanel();
        LayoutManager passwordLayout = new GridLayout(3, 2, 3, 3);
        LayoutManager buttonLayout = new GridLayout(2, 1, 10, 3);
        LayoutManager changeLayout = new GridLayout(2, 2, 3, 3);
        changePanel.setLayout(changeLayout);
        passwordPanel.setLayout(passwordLayout);
        buttonPanel.setLayout(buttonLayout);
        changePanel.add(currentEmail);
        changePanel.add(changeEmail);
        changePanel.add(currentPhoneNumber);
        changePanel.add(changePhoneNumber);
        passwordPanel.add(oldPassword);
        passwordPanel.add(enterOldPassword);
        passwordPanel.add(newPassword);
        passwordPanel.add(enterNewPassword);
        passwordPanel.add(repeatPassword);
        passwordPanel.add(enterRepeatPassword);
        buttonPanel.add(changePassword);
        buttonPanel.add(changeAccountInfoBack);
        add(changePanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        ChangeAccountAction changeAccountAction = new ChangeAccountAction();
        changePassword.addActionListener(changeAccountAction);
        changeEmail.addActionListener(changeAccountAction);
        changePhoneNumber.addActionListener(changeAccountAction);
        changeAccountInfoBack.addActionListener(changeAccountAction);

    }
    private class ChangeAccountAction extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == changeEmail) {
                System.out.println("Email");
                String newEmail = showInputDialog(null, "Enter new email: ");
                if(newEmail.length() > 5){
                    try {
                        openConnection();
                        int check = setEmail(newEmail, username);
                        if (check == 1) {
                            currentEmail.setText(newEmail);
                            showMessageDialog(null, "Update complete");
                        }
                        closeConnection();
                    } catch (Exception e) {
                        Database.printMesssage(e, "ChangeEmail");
                    }
                }
            } else if (actionEvent.getSource() == changePhoneNumber) {
                String newPhoneNumberRead = showInputDialog(null, "Enter new phone number");
                if (newPhoneNumberRead.length() == 8) {
                    try {
                        int newPhoneNumber = Integer.parseInt(newPhoneNumberRead);
                    } catch (NumberFormatException e) {
                        showMessageDialog(null, "Incorrect phone number");
                    }
                    try {
                        openConnection();
                        int ok = setPhoneNumber(newPhoneNumberRead, username);
                        if (ok == 0) {
                            showMessageDialog(null, "Update complete");
                            currentPhoneNumber.setText(newPhoneNumberRead);
                        } else {
                            showMessageDialog(null, "Update failed! That phone number is already registered");
                        }
                        closeConnection();
                    } catch (Exception e) {
                        Database.printMesssage(e, "ChangePhoneNumber");
                    }
                }
            } else if (actionEvent.getSource() == changePassword) {
                boolean permission = false;
                char[] oldPass = enterOldPassword.getPassword();
                char[] newPass = enterNewPassword.getPassword();
                char[] repeatNewPass = enterRepeatPassword.getPassword();
                String stringOldPass = new String(oldPass);
                try {
                    openConnection();
                    int checkOldPassword = checkLogIn(username, stringOldPass);
                    if (checkOldPassword > 0) {
                        permission = true;
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "changePassword");
                }
                if (permission) {
                    if (newPass.length == repeatNewPass.length) {
                        int length = 0;
                        for (int i = 0; i < newPass.length; i++) {
                            char pass = newPass[i];
                            char repPass = repeatNewPass[i];
                            if (Character.valueOf(pass).equals(repPass) && length == i) {
                                length++;
                            }
                        }
                        if (length == newPass.length) {
                            showMessageDialog(null, "Update complete");

                        } else {
                            showMessageDialog(null, "Passwords do not match! Please try again");
                        }
                    } else {
                        enterNewPassword.setText("");
                        enterOldPassword.setText("");
                        enterRepeatPassword.setText("");
                        showMessageDialog(null, "Passwords do not match! Please try again");
                    }
                } else {
                    showMessageDialog(null, "Incorrect old password! Please try again");
                }
            } else {
                dispose();
            }
        }
    }
}

