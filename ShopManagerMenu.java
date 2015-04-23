package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShopManagerMenu extends JFrame {
    private final String username;
    private String centerName;
    private String shopName;
    JButton viewInfo = new JButton("View Information");
    JButton updateInfo = new JButton("Update shop information");
    JButton changeAccountInfo = new JButton("Change account information");
    JButton exit = new JButton("Exit");

    // JFrame - Change account info ------------------------------------------
    JFrame changeAccountInfoFrame = new JFrame();
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
    
    public ShopManagerMenu(String username){
        super("ShopManager - " + username);
        this.username = username;
        LayoutManager layout = new GridLayout(4, 2, 3, 3);
        setLayout(layout);
        add(viewInfo);
        add(updateInfo);
        add(changeAccountInfo);
        add(exit);
        pack();

        Action action = new Action();
        viewInfo.addActionListener(action);
        updateInfo.addActionListener(action);
        changeAccountInfo.addActionListener(action);
        exit.addActionListener(action);
        
    }
    private class Action extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton)source.getSource();

            if (check == viewInfo) {
                Customer CostumerVindu = new Customer();
                CostumerVindu.setLocationRelativeTo(null);
                CostumerVindu.setVisible(true);
            }
            else if (check == updateInfo) {
                try {
                    openConnection();
                    UpdateStoreInfo updateStoreInfo = new UpdateStoreInfo(username);
                    updateStoreInfo.setVisible(true);
                    updateStoreInfo.setLocationRelativeTo(null);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenter");
                }
            } else if (check == changeAccountInfo) {
                try {
                    openConnection();
                    String email = getEmail(username);
                    String tlf = getPhoneNumber(username);
                    ChangeAccountInfo changeAccountInfo = new ChangeAccountInfo(username, email, tlf);
                    changeAccountInfo.setVisible(true);
                    changeAccountInfo.setLocationRelativeTo(null);
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "changeAccountInfo");
                }
            } else {
                System.exit(0);
            }
        }
    }
}

