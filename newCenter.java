package shoolprodject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.border.Border;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class newCenter extends JFrame{
    
    private String centerName;
    private String username;
    JLabel Lcenter_Name = new JLabel("Center name: ", JLabel.CENTER);
    JLabel Lmunicipality = new JLabel("Municipality: ", JLabel.CENTER);
    JLabel Lcenter_turnover = new JLabel("Turnover: ", JLabel.CENTER);
    JLabel Lnr_Shops = new JLabel("Number of Shops: ", JLabel.CENTER);
    JLabel Lsqm = new JLabel("meter ^2 : ", JLabel.CENTER);
    JLabel LAddress = new JLabel("Address: ", JLabel.CENTER);
    JLabel LMail = new JLabel("Mail:", JLabel.CENTER);
    JLabel LTlf = new JLabel("Tlf:", JLabel.CENTER);
    JLabel LCarPark = new JLabel("Car Parking (y/n):", JLabel.CENTER);
    JLabel LDescription = new JLabel("Description:", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JTextField newCenterName = new JTextField();
    JTextField centerMunicipality = new JTextField();
    JTextField centerTurnover = new JTextField();
    JTextField nrShops = new JTextField();
    JTextField sqm = new JTextField();
    JTextField address = new JTextField();
    JTextField mail = new JTextField();
    JTextField tlf = new JTextField();
    JTextField carPark = new JTextField();
    JButton create = new JButton("Create");
    JButton back = new JButton("Back");
     //JFrame - change Trade --------------------------------------
    ArrayList<String> trades;
    JFrame changeTradeFrame = new JFrame();
    JLabel titleTrade = new JLabel("Trade", JLabel.CENTER);
    JLabel titleDescription = new JLabel("Description");
    DefaultListModel defaultListModelTrade = new DefaultListModel();
    JList tradeList = new JList(defaultListModelTrade);
    JScrollPane scrollPaneTrade = new JScrollPane(tradeList);
    JTextArea tradeDescription = new JTextArea("Description");
    JButton changeTradeBack = new JButton("Back");
    JButton selectTrade = new JButton("Select");
    //JFrame - change description ----------------------------------
    JFrame changeDescriptionFrame = new JFrame();
    JTextArea newDescription = new JTextArea();
    JButton changeDescriptionBack = new JButton("Back");
    JButton selectDescription = new JButton("Change");
    
    
    public newCenter(){
        JPanel storeTopPanel = new JPanel();
        JPanel storeCenterPanel = new JPanel();
        JPanel storeBottomPanel = new JPanel();
        LayoutManager storeTopLayout = new GridLayout(9,2,3,3);
        LayoutManager storeCenterLayout = new GridLayout(1,2,3,3);
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        description.setBorder(border);
        description.setPreferredSize(new Dimension(300, 100));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        setLayout(changeStoreInfoLayout);
        storeTopPanel.setLayout(storeTopLayout);
        storeCenterPanel.setLayout(storeCenterLayout);
        storeBottomPanel.setLayout(storeBottomLayout);
        storeTopPanel.add(this.Lcenter_Name);
        storeTopPanel.add(newCenterName);
        storeTopPanel.add(this.Lmunicipality);
        storeTopPanel.add(centerMunicipality);
        storeTopPanel.add(this.Lcenter_turnover);
        storeTopPanel.add(centerTurnover);
        storeTopPanel.add(this.Lsqm);
        storeTopPanel.add(sqm);
        storeTopPanel.add(this.LAddress);
        storeTopPanel.add(address);
        storeTopPanel.add(this.LTlf);
        storeTopPanel.add(tlf);
        storeTopPanel.add(this.LMail);
        storeTopPanel.add(mail);
        storeTopPanel.add(LCarPark);
        storeTopPanel.add(carPark);
        storeCenterPanel.add(LDescription);
        storeCenterPanel.add(this.description);
        storeBottomPanel.add(back);
        storeBottomPanel.add(create);
        add(storeTopPanel, BorderLayout.NORTH);
        add(storeCenterPanel, BorderLayout.CENTER);
        add(storeBottomPanel, BorderLayout.SOUTH);
        pack();
        
        
        ActionUpdateStoreInfo actionChangeStoreInfo = new ActionUpdateStoreInfo();
        create.addActionListener(actionChangeStoreInfo);
        back.addActionListener(actionChangeStoreInfo);
    }
    private class ActionUpdateStoreInfo extends DatabaseConnection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == create){
                String na=newCenterName.getText();
                boolean check1=true;
                if(na==null || na.equals("") || na.equals(" ")){
                    check1=false;
                    JOptionPane.showMessageDialog(null,"Must type inn Center Name");
                }
                String mu=centerMunicipality.getText();
                boolean check2=true;
                if(mu==null || mu.equals("") || mu.equals(" ")){
                    check2=false;
                    JOptionPane.showMessageDialog(null,"Must type inn Municipality");
                }
                String tu=centerTurnover.getText();
                boolean check3=false;
                int i;
                try{
                     i=Integer.parseInt(tu);
                     check3=true;
                    }
                catch (NumberFormatException e){
                    Database.printMesssage(e, "integer ikke gyldig");
                    JOptionPane.showMessageDialog(null,"Must type inn Center Turnover, must be a Number");
                }
                String sq=sqm.getText();
                boolean check4=false;
                int i2;
                try{
                     i2=Integer.parseInt(tu);
                     check4=true;
                    }
                catch (NumberFormatException e){
                    Database.printMesssage(e, "integer ikke gyldig");
                    JOptionPane.showMessageDialog(null,"Must type inn SQM, must be a Number");
                }
                String ad=address.getText();
                boolean check5=true;
                if(ad==null || ad.equals("") || ad.equals(" ")){
                    check5=false;
                    JOptionPane.showMessageDialog(null,"Must type inn Address");
                }
                String tl=tlf.getText();
                boolean check6=false;
                int i3;
                try{
                     i3=Integer.parseInt(tu);
                     check6=true;
                    }
                catch (NumberFormatException e){
                    Database.printMesssage(e, "integer ikke gyldig");
                    JOptionPane.showMessageDialog(null,"Must type inn tlf, must be a Number");
                }
                String ma=mail.getText();
                boolean check7=true;
                if(ma==null || ma.equals("") || ma.equals(" ")){
                    check7=false;
                    JOptionPane.showMessageDialog(null,"Must type inn Mail");
                }
                String ca=carPark.getText();
                boolean check8=false;
                if(ca.equals("n") || ca.equals("y")){
                    check8=true;
                }
                else{
                    if (JOptionPane.showConfirmDialog(null, "Is it Car Parking?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        ca="y";
                    } 
                    else {
                        ca="n";
                    }
                    check8=true;
                }
                String de=description.getText();
                boolean check9=true;
                if(de==null || de.equals("") || de.equals(" ")){
                    check9=false;
                    JOptionPane.showMessageDialog(null,"Must type inn Description");
                }
                
                
                if(check1 && check2 && check3 && check4 && check5 && check6 && check7 && check8 && check9){
                    try{
                        openConnection();
                        newCenter(na,mu,tu,"0",sq,ad,tl,ma,ca,de);
                        closeConnection();
                        JOptionPane.showMessageDialog(null,"Data is updated");
                        dispose();
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "getCenters");
                    }
                }
                
            }
            if(actionEvent.getSource() == back){
                dispose();
            }
        }
    }
    
}
