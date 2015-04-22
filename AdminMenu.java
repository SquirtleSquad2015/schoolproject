package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class AdminMenu extends JFrame {
    private String username;
    private String centerName;
    private String shopName;
    JButton Users = new JButton("View Users");
    JButton Centers = new JButton("View Centers");
    JButton changeAccountInfo = new JButton("Change account information");
    JButton exit = new JButton("Exit");

    public AdminMenu(String username){
        super("ShopManager - " + username);
        this.username = username;
        LayoutManager layout = new GridLayout(4, 1, 3, 3);
        setLayout(layout);
        add(Users);
        add(Centers);
        add(changeAccountInfo);
        add(exit);
        pack();

        Action action = new Action();
        Users.addActionListener(action);
        Centers.addActionListener(action);
        changeAccountInfo.addActionListener(action);
        exit.addActionListener(action);
    }
    private class Action extends DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton)source.getSource();

            if (check == Users) {
                
            }
            if (check ==Centers){
                AdminCenterView centerView = new AdminCenterView();
                centerView.setLocationRelativeTo(null);
                centerView.setVisible(true);
            }
            if (check ==changeAccountInfo){
                
            }
            if (check ==exit){
                System.exit(0);
            }
            
        }
    }
}