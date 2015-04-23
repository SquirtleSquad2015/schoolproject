package shoolprodject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.swing.border.*;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class UpdateUser extends JFrame{
    private String username;
    private String activ;
    private String mail;
   

    JLabel Lusername = new JLabel("",JLabel.CENTER);
    JLabel Laccess = new JLabel("", JLabel.CENTER);
    JLabel Lactiv = new JLabel("", JLabel.CENTER);
    JLabel Lname = new JLabel("", JLabel.CENTER);
    JLabel Lcetnername = new JLabel("", JLabel.CENTER);
    JLabel Ltitle = new JLabel("", JLabel.CENTER);
    JLabel Lmail = new JLabel("", JLabel.CENTER);
    JLabel Ltlf = new JLabel("", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JLabel changeUsername = new JLabel("",JLabel.CENTER);
    JLabel changeAccess = new JLabel("",JLabel.CENTER);
    JButton changeActiv = new JButton("Change");
    JLabel changeName = new JLabel("");
    JLabel changeCentername = new JLabel("");
    JButton changeTitle = new JButton("Change");
    JButton changeMail = new JButton("Change");
    JButton changeTlf = new JButton("Change");
    JButton knapp = new JButton("Change description");
    JButton backButton = new JButton("Back");
     //JFrame - change Trade --------------------------------------
    ArrayList<String> activList;
    JFrame changeTradeFrame = new JFrame();
    JLabel titleTrade = new JLabel("Choose (y) for activ and (n) for inactiv", JLabel.CENTER);
    DefaultListModel defaultListModelTrade = new DefaultListModel();
    JList tradeList = new JList(defaultListModelTrade);
    JScrollPane scrollPaneTrade = new JScrollPane(tradeList);
    JButton changeActivBack = new JButton("Back");
    JButton selectActiv = new JButton("Select");
    //JFrame - change description ----------------------------------
    JFrame changeDescriptionFrame = new JFrame();
    JTextArea newDescription = new JTextArea();
    JButton changeDescriptionBack = new JButton("Back");
    JButton selectDescription = new JButton("Change");
    
    
    public UpdateUser(String centerName){
        this.username=centerName;
        JPanel storeTopPanel = new JPanel();
       
        JPanel storeBottomPanel = new JPanel();
        LayoutManager storeTopLayout = new GridLayout(9,2,3,3);
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.description.setBorder(border);
        this.description.setPreferredSize(new Dimension(300, 100));
        this.description.setLineWrap(true);
        this.description.setWrapStyleWord(true);
        this.description.setEditable(false);
        this.description.setOpaque(false);
        setLayout(changeStoreInfoLayout);
        storeTopPanel.setLayout(storeTopLayout);
        storeBottomPanel.setLayout(storeBottomLayout);
        storeTopPanel.add(Lusername);
        storeTopPanel.add(changeUsername);
        storeTopPanel.add(Laccess);
        storeTopPanel.add(changeAccess);
        storeTopPanel.add(Lactiv);
        storeTopPanel.add(changeActiv);
        storeTopPanel.add(Lname);
        storeTopPanel.add(changeName);
        storeTopPanel.add(Lcetnername);
        storeTopPanel.add(changeCentername);
        storeTopPanel.add(Ltitle);
        storeTopPanel.add(changeTitle);
        storeTopPanel.add(Ltlf);
        storeTopPanel.add(changeTlf);
        storeTopPanel.add(Lmail);
        storeTopPanel.add(changeMail);
        
        
        storeBottomPanel.add(backButton);
        storeBottomPanel.add(knapp);
        add(storeTopPanel, BorderLayout.NORTH);
        add(storeBottomPanel, BorderLayout.SOUTH);
        pack();
        ActionUpdateStoreInfo actionChangeCenter = new ActionUpdateStoreInfo();
        changeActiv.addActionListener(actionChangeCenter);
        changeTitle.addActionListener(actionChangeCenter);
        changeMail.addActionListener(actionChangeCenter);
        changeTlf.addActionListener(actionChangeCenter);
        knapp.addActionListener(actionChangeCenter);
        backButton.addActionListener(actionChangeCenter);
       
        //JFrame - change Trade -------------------------------------------------------------------------

        JPanel tradeTopPanel = new JPanel();
        JPanel tradeCenterPanel = new JPanel();
        JPanel tradeBottomPanel = new JPanel();
        LayoutManager tradeLayout = new BorderLayout();
        LayoutManager tradePanelLayout = new GridLayout(1,2,3,3);
        tradeTopPanel.setLayout(tradePanelLayout);
        tradeCenterPanel.setLayout(tradePanelLayout);
        tradeBottomPanel.setLayout(tradePanelLayout);
        changeTradeFrame.setLayout(tradeLayout);
        tradeTopPanel.add(titleTrade);
        tradeCenterPanel.add(scrollPaneTrade);
        tradeBottomPanel.add(changeActivBack);
        tradeBottomPanel.add(selectActiv);
        changeTradeFrame.add(tradeTopPanel, BorderLayout.NORTH);
        changeTradeFrame.add(tradeCenterPanel, BorderLayout.CENTER);
        changeTradeFrame.add(tradeBottomPanel, BorderLayout.SOUTH);
        changeTradeFrame.pack();
        ChangeTradeAction changeTradeaction = new ChangeTradeAction();
        changeActivBack.addActionListener(changeTradeaction);
        selectActiv.addActionListener(changeTradeaction);
        
        // JFrame - changeDescription ------------------------------------------------------------------------
        changeDescriptionFrame.setTitle("Change description");
        newDescription.setLineWrap(true);
        newDescription.setPreferredSize(new Dimension(300, 150));
        newDescription.setBorder(border);
        LayoutManager changeDescriptionLayout = new BorderLayout();
        LayoutManager layout1 = new GridLayout(1,1,3,3);
        LayoutManager layout2 = new GridLayout(1,2,3,3);
        changeDescriptionFrame.setLayout(changeDescriptionLayout);
        changeDescriptionFrame.setLayout(changeDescriptionLayout);
        JPanel topPanel = new JPanel();
        JPanel changeDescriptionButtonPanel = new JPanel();
        topPanel.setLayout(layout1);
        changeDescriptionButtonPanel.setLayout(layout2);
        topPanel.add(newDescription);
        changeDescriptionButtonPanel.add(changeDescriptionBack);
        changeDescriptionButtonPanel.add(selectDescription);
        changeDescriptionFrame.add(topPanel, BorderLayout.NORTH);
        changeDescriptionFrame.add(changeDescriptionButtonPanel, BorderLayout.SOUTH);
        changeDescriptionFrame.pack();
        
        ChangeDescriptionAction changeDescriptionAction = new ChangeDescriptionAction();
        selectDescription.addActionListener(changeDescriptionAction);
        changeDescriptionBack.addActionListener(changeDescriptionAction);
        
        
        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);
    }
    private class ActionUpdateStoreInfo extends DatabaseConnection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeActiv){
                int ok=0;
                try {
                    openConnection();
                    activ = getUserActiv(username);                    
                    closeConnection();
                    System.out.println(activ);
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeActiv");
                }
                if(activ.equals("n")){
                    String ny="y";
                    try {
                        openConnection();
                        ok=setUserActiv(ny,username);                   
                        closeConnection();
                        showMessageDialog(null, "Update complete. User is: "+ny);
                        Lactiv.setText("Activ: "+ny);
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeActiv");
                    }
                }
                else if(activ.equals("y")){
                    String ny="n";
                    try {
                        openConnection();
                        ok=setUserActiv(ny,username);                   
                        closeConnection();
                        showMessageDialog(null, "Update complete. User is: "+ny);
                        Lactiv.setText("Activ: "+ny);
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeActiv");
                    }
                }
            }
            else if(actionEvent.getSource() == "nyknapp"){
                String newStoreLocation = showInputDialog(null, "Please insert new store name: ");
                try {
                    openConnection();
                    int ok = setStoreLocation(username, newStoreLocation);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Lactiv.setText("Location: " + newStoreLocation);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreLocation");
                }

            }

            else if(actionEvent.getSource() == changeCentername){
                String newStoreOpeningHrs = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrs(username, newStoreOpeningHrs);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Lcetnername.setText("Opening hours: " + newStoreOpeningHrs);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrs");
                }
            }
            else if(actionEvent.getSource() == changeTitle){
                String newStoreOpeningHrsWeekends = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrsWeekends(username, newStoreOpeningHrsWeekends);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Ltitle.setText("Weekends: " + newStoreOpeningHrsWeekends);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrsWeekends");
                }
            }
            else if(actionEvent.getSource() == changeMail){
                int ok=0;
                String newMail = showInputDialog(null, "Please insert new mail: ");
                try {
                    openConnection();
                    ok=setEmail(newMail,username);                   
                    closeConnection();
                    showMessageDialog(null, "Update complete. User mail is: "+newMail);
                    Lmail.setText("Activ: "+newMail);
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeMail");
                }
            }
            else if(actionEvent.getSource() == knapp){
                changeDescriptionFrame.setVisible(true);
                changeDescriptionFrame.setLocationRelativeTo(null);
            }
            else if(actionEvent.getSource() == backButton){
                dispose();
            }
        }
    }
    private class ChangeTradeAction extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeActivBack){
                changeTradeFrame.dispose();
            }
            else if(actionEvent.getSource()==changeActiv){
                int index = tradeList.getSelectedIndex();
                String activ=activList.get(index);
                try {
                    
                    openConnection();
                    int ok=setUserActiv(activ,username);
                    if(ok==1){
                        showMessageDialog(null, "Update complete");
                        Laccess.setText("Trade: " + activList.get(index));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "selectTrade");
                }
                changeTradeFrame.dispose();
            }
        }
    }
    private class ChangeDescriptionAction extends DatabaseConnection implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == selectDescription){
                System.out.println(newDescription.getText());
                try {
                    openConnection();
                    int ok = setStoreDescription(username, newDescription.getText());
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        description.setText(newDescription.getText());
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "setDescription");
                }
            } else {
                
            }
        }
        
    }
    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            
            try{
                openConnection();
                
                //getUserAccess(username);
                activ=getUserActiv(username);
                //getPersonName(username);
                //getCenter(username);
                //getUserTitle(username);
                //getPhoneNumber(username);
                //getEmail(username);
                        
                        
                Lusername.setText("Username: "+username);
                Laccess.setText("Access Level: "+getUserAccess(username));
                Lactiv.setText("Activ: "+activ);
                Lname.setText("Name: "+getPersonName(username));
                Lcetnername.setText("Center Name: "+getCenter(username));
                Ltitle.setText("Title: "+getUserTitle(username));
                Ltlf.setText("Tlf:"+getPhoneNumber(username));
                Lmail.setText("Mail:"+getEmail(username));
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
            
        }
    }  
}
