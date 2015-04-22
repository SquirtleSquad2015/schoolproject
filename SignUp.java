package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;


public class SignUp extends JFrame {
    private String userType = "";
    private String userName;
    private String phoneNumber;
    private char[] passwordTyped;
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
    JButton next = new JButton("Next");
    JButton exit = new JButton("Exit");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel masterPanel = new JPanel();
    // JFrame - signUp part 2 ------------------------
    JFrame signUp2 = new JFrame();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel masterPanel2 = new JPanel();
    JLabel userNameTitle = new JLabel("Username: ");
    JLabel currentUserName = new JLabel("Username: ", JLabel.CENTER);
    JLabel phoneNumberTitle = new JLabel("Phone number: ");
    JLabel currentPhoneNumber = new JLabel("Telephone: ", JLabel.CENTER);
    JLabel title = new JLabel("Title: ");
    JLabel currentTitle = new JLabel("Title: ", JLabel.CENTER);
    JLabel realName = new JLabel("Enter name: ", JLabel.CENTER);
    JLabel email = new JLabel("Enter email: ", JLabel.CENTER);
    JLabel chooseCenter = new JLabel("Select your center:", JLabel.CENTER);
    JButton signUp = new JButton("Sign up");
    JButton back = new JButton("Back");
    JButton search = new JButton("Search for center");
    JTextField enterRealName = new JTextField(20);
    JTextField enterEmail = new JTextField(20);
    JTextField center = new JTextField(20);
    DefaultListModel defaultListModel = new DefaultListModel();
    JList list = new JList(defaultListModel);
    JScrollPane scrollPane = new JScrollPane(list);

    public SignUp() {
        super("Sign up");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        panel1.add(next);
        panel2.add(exit);
        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        pack();
        JRootPane rootPane1 = SwingUtilities.getRootPane(next);
        rootPane1.setDefaultButton(next);
        Action action = new Action();
        exit.addActionListener(action);
        next.addActionListener(action);
        // JFrame signup part 2 ---------------------
        LayoutManager singUpMaster = new BorderLayout();
        LayoutManager layout = new GridLayout(6, 2, 3, 3);
        LayoutManager layout4 = new GridLayout(1, 2, 3, 3);
        LayoutManager layout5 = new GridLayout(1, 2, 3, 3);
        masterPanel2.setLayout(singUpMaster);
        panel3.setLayout(layout);
        panel4.setLayout(layout4);
        panel5.setLayout(layout5);
        panel3.add(userNameTitle);
        panel3.add(currentUserName);
        panel3.add(phoneNumberTitle);
        panel3.add(currentPhoneNumber);
        panel3.add(title);
        panel3.add(currentTitle);
        panel3.add(realName);
        panel3.add(enterRealName);
        panel3.add(email);
        panel3.add(enterEmail);
        panel3.add(search);
        panel3.add(center);
        panel4.add(chooseCenter);
        panel4.add(scrollPane);
        panel5.add(back);
        panel5.add(signUp);
        masterPanel2.add(panel3, BorderLayout.NORTH);
        masterPanel2.add(panel4, BorderLayout.CENTER);
        masterPanel2.add(panel5, BorderLayout.SOUTH);
        signUp2.add(masterPanel2);
        signUp2.pack();
        SignUpAction signUpAction = new SignUpAction();
        signUp.addActionListener(signUpAction);
        back.addActionListener(signUpAction);
        search.addActionListener(signUpAction);

    }
    private class Action extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean equalPassword = false;
            boolean userNameCheck = true;
            userName = i_username.getText();
            phoneNumber = i_telephone.getText();
            passwordTyped = i_password.getPassword();
            if (actionEvent.getSource() == exit) {
                dispose();
            }
            char[] password = i_password.getPassword();
            char[] repeatPassword = i_repeatPassword.getPassword();
            if (!userName.equals("") && password.length >= 5 && phoneNumber.length() == 8 && (storeManager.isSelected()
                    || centerManager.isSelected() || customerService.isSelected())) {
                try {
                    int telephone = Integer.parseInt(phoneNumber);
                } catch (NumberFormatException e) {
                    System.out.print("test");
                    showMessageDialog(null, "Incorrect telephone number! Please try again");
                    i_telephone.setText("");
                }
                try {
                    openConnection();
                    userNameCheck = checkUsername(userName);
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
                        if (Character.valueOf(pass).equals(repPass) && length == i) {
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
                currentUserName.setText(i_username.getText());
                currentPhoneNumber.setText(i_telephone.getText());
                currentTitle.setText(userType);
                signUp2.setVisible(true);
                signUp2.setLocationRelativeTo(null);
                setVisible(false);
            }
        }
    }
     private class SignUpAction extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            String valg = button.getText();
            String name12 = enterRealName.getText();
            String email = enterEmail.getText();
            String centerName = center.getText();
            int choice = list.getSelectedIndex();
            int userLevel = 0;

            if(actionEvent.getSource() == search){
                try{
                    openConnection();
                    ArrayList<String> list = getCenters(centerName);
                    defaultListModel.clear();
                    System.out.println(choice);
                    for(int i = 0; i < list.size(); i++){
                        defaultListModel.addElement(list.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
            }
            else if(actionEvent.getSource() == signUp) {
                if(!name12.equals("") && !email.equals("")){
                    if(choice != -1){
                        String centerName1 = list.getSelectedValue().toString();
                        if(userType.equals("CenterManager")){
                            userLevel = 3;
                        }
                        else if(userType.equals("CustomerService")) {
                            userLevel = 1;
                        }
                        else {
                            userLevel = 2;
                        }
                        int ok = 0;
                        try {
                            openConnection();
                            ok = regNewCenterUser(userName, phoneNumber, passwordTyped, 
                                    centerName1, name12, email, userLevel, userType);
                            closeConnection();
                        }
                        catch (Exception e){
                            Database.printMesssage(e, "regNewCenterManager");
                        }
                        if(ok == 1){
                            showMessageDialog(null, "Registration complete");
                            signUp2.dispose();
                            dispose();
                        } else if(ok == 2){
                            String newUsername = showInputDialog("Username already registered, please enter new one");
                            userName = newUsername;
                        } else {
                            String newTelephone = showInputDialog("Telephone number already registered, try again: ");
                            phoneNumber = newTelephone;
                        }
                    } else {
                        showMessageDialog(null, "Please select center");
                    }
                } else {
                    showMessageDialog(null, "Please enter real name and email");
                }
                
            } else {
                signUp2.dispose();
                setVisible(true);
            }

        }
    }
}

