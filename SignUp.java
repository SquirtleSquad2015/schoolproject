
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;


public class SignUp extends JFrame {


    JLabel username = new JLabel("Name: ", JLabel.CENTER);
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

    public SignUp() {
        super("Sign up");
        setSize(400, 600);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        LayoutManager layout = new GridLayout(6, 2, 3, 3);
        setLayout(layout);
        ButtonGroup choices = new ButtonGroup();
        choices.add(centerManager);
        choices.add(storeManager);
        choices.add(customerService);
        JButton signup = new JButton("Sign up");
        add(username);
        add(i_username);
        add(password);
        add(i_password);
        add(repeatPassword);
        add(i_repeatPassword);
        add(telephone);
        add(i_telephone);
        add(centerManager);
        add(storeManager);
        add(customerService);
        add(signup);
        pack();

        JRootPane rootPane = SwingUtilities.getRootPane(signup);
        rootPane.setDefaultButton(signup);

        KnappeLytter lytter = new KnappeLytter();
        signup.addActionListener(lytter);


    }

    private class KnappeLytter extends Register implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            boolean equalPassword = false;
            boolean storeManagerValue = false;
            boolean centerManagerValue = false;
            boolean customerServiceValue = false;
            String name = i_username.getText();
            String telephone = i_telephone.getText();
            String passord = "";

            char[] password = i_password.getPassword();
            char[] repeatPassword = i_repeatPassword.getPassword();
            if (!name.equals("") && password.length >= 5 &&
                    telephone.length() == 8 &&
                    (storeManager.isSelected() ||
                    centerManager.isSelected() ||
                    customerService.isSelected())) {
                
                if (password.length == repeatPassword.length) {
                    int length = 0;
                    
                    for (int i = 0; i < password.length; i++) {
                        char pass = password[i];
                        passord += Character.toString(pass);
                        char repPass = repeatPassword[i];
                        if (Character.valueOf(pass).equals(Character.valueOf(repPass)) && length == i) {
                            length++;
                        }
                    }
                    if (length == password.length) {
                        equalPassword = true;
                    }
                } 
                else {
                    i_password.setText("");
                    i_repeatPassword.setText("");
                    showMessageDialog(null, "Passwords do not match! Please try again");
                }
                
                    if(storeManager.isSelected()){
                        storeManagerValue = true;

                    }
                    else if(centerManager.isSelected()){
                        centerManagerValue = true;
                      //  Registrering registrering = new Registrering();
                        try {
                            update("blabla");
                        }
                        catch (Exception exception){
                            System.out.println("Fikk ikke tak i database");
                        }
                        
                    } else {
                        customerServiceValue = true;
                    }
                } else {
                    showMessageDialog(null, "Incorrect information! Please try again");
                }
            }
        }
    }

