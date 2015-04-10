package shoolprodject;

import DatabasePackage.Database;
import DatabasePackage.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;


public class SignUp extends JFrame {
    // Masterpanel 1 - start ----------------------------------------------------------------------------------------------------------------------
    JLabel username = new JLabel("Username: ", JLabel.CENTER);
    JLabel password = new JLabel("Password: (Minimum 5 letters)", JLabel.CENTER);
    JLabel repeatPassword = new JLabel("Repeat password: ", JLabel.CENTER);
    JLabel telephone = new JLabel("Telephone:", JLabel.CENTER);
    JTextField i_username = new JTextField(20);
    JPasswordField i_password = new JPasswordField(20);
    JPasswordField i_repeatPassword = new JPasswordField(20);
    JTextField i_telephone = new JTextField(20);
    JRadioButton centerManager = new JRadioButton("Center Manager");
    JRadioButton storeManager = new JRadioButton("Store Manager");
    JRadioButton customerService = new JRadioButton("Customer Service");
    JButton exit = new JButton("Exit");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel masterPanel = new JPanel();
    // Masterpanel 1 - slutt --------------------------------------------------------------------------------------------------------------------

    public SignUp() {
        super("Sign up");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Masterpanel 1 - Start ----------------------------------------------------------------------------------------------------------------
        LayoutManager layout1 = new GridLayout(6, 2, 3, 3);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(1, 1, 3, 3);
        panel2.setLayout(layout2);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);
        ButtonGroup choices = new ButtonGroup();
        choices.add(centerManager);
        choices.add(storeManager);
        choices.add(customerService);
        JButton signup = new JButton("Next");
        panel1.add(username);
        panel1.add(i_username);
        panel1.add(password);
        panel1.add(i_password);
        panel1.add(repeatPassword);
        panel1.add(i_repeatPassword);
        panel1.add(telephone);
        panel1.add(i_telephone);
        panel1.add(centerManager);
        panel1.add(storeManager);
        panel1.add(customerService);
        panel1.add(signup);
        panel2.add(exit);
        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        // Masterpanel 1 - Slutt --------------------------------------------------------------------------------------------------------

        add(masterPanel);

        pack();
        JRootPane rootPane = SwingUtilities.getRootPane(signup);
        rootPane.setDefaultButton(signup);

        KnappeLytter lytter = new KnappeLytter();
        exit.addActionListener(lytter);
        signup.addActionListener(lytter);

    }

    private class KnappeLytter extends DatabaseConnection implements ActionListener {

        public void actionPerformed(ActionEvent hendelse) {
            boolean equalPassword = false;
            boolean userNameCheck = true;
            String userType = "";
            String name = i_username.getText();
            String telephoneLest = i_telephone.getText();
            if (hendelse.getSource() == exit) {
                dispose();
            }
            char[] password = i_password.getPassword();
            char[] repeatPassword = i_repeatPassword.getPassword();
            if (!name.equals("") && password.length >= 5 && telephoneLest.length() == 8 && (storeManager.isSelected()
                    || centerManager.isSelected() || customerService.isSelected())) {
                try {
                    int telephone = Integer.parseInt(telephoneLest);
                } catch (NumberFormatException e) {
                    System.out.print("test");
                    showMessageDialog(null, "Incorrect telephone number! Please try again");
                    i_telephone.setText("");
                }
                try {
                    openConnection();
                    userNameCheck = checkUsername(name);
                    closeConnection();
                    if (userNameCheck) {
                        showMessageDialog(null, "Sorry, that username is already taken");
                        i_username.setText("");
                    }
                } catch (Exception e) {
                    Database.printMesssage(e, "Check userName");
                }
                if (password.length == repeatPassword.length) {
                    int length = 0;
                    for (int i = 0; i < password.length; i++) {
                        char pass = password[i];
                        char repPass = repeatPassword[i];
                        if (Character.valueOf(pass).equals(Character.valueOf(repPass)) && length == i) {
                            length++;
                        }
                    }
                    if (length == password.length) {
                        equalPassword = true;
                    }
                } else {
                    i_password.setText("");
                    i_repeatPassword.setText("");
                    showMessageDialog(null, "Passwords do not match! Please try again");
                }
                if (storeManager.isSelected() && equalPassword) {
                    userType = "StoreManager";
                } else if (centerManager.isSelected() && equalPassword) {
                    userType = "CenterManager";
                } else {
                    userType = "CustomerService";
                }


            } else {
                i_password.setText("");
                i_repeatPassword.setText("");
                i_telephone.setText("");
                showMessageDialog(null, "Incorrect information! Please try again");
            }

            if (equalPassword && userNameCheck == false) {
                SignUp2 signUp2 = new SignUp2(name, telephoneLest, password, userType);
                signUp2.setLocationRelativeTo(null);
                signUp2.setVisible(true);
                dispose();
            }
        }
    }
}

