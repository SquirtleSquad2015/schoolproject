package shoolprodject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.swing.border.*;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class UpdateCenter extends JFrame{
    private String centerName;
    private String username;

    JLabel Lcentername = new JLabel("Center name: ", JLabel.CENTER);
    JLabel Lmunicipality = new JLabel("Municipality: ", JLabel.CENTER);
    JLabel Lturnover = new JLabel("Turnover: ", JLabel.CENTER);
    JLabel Lnr_shops = new JLabel("Number of Stores: ", JLabel.CENTER);
    JLabel Lsqm = new JLabel("meter ^2: ", JLabel.CENTER);
    JLabel Laddress = new JLabel("Address: ", JLabel.CENTER);
    JLabel Lmail = new JLabel("Mail:", JLabel.CENTER);
    JLabel Ltlf = new JLabel("Tlf:", JLabel.CENTER);
    JLabel Lcarpark = new JLabel("Car Parking:", JLabel.CENTER);
    JLabel Ldescription = new JLabel("Description:", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JButton changeCenterName = new JButton("Change");
    JButton changeMunicipality = new JButton("Change");
    JButton changeTurnover = new JButton("Change");
    JButton changeNr_Stores = new JButton("Change");
    JButton changeSqm = new JButton("Change");
    JButton changeAddress = new JButton("Change");
    JButton changeMail = new JButton("Change");
    JButton changeTlf = new JButton("Change");
    JButton changeCarPark = new JButton("Change");
    JButton changeDescription = new JButton("Change description");
    JButton backButton = new JButton("Back");
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
    
    
    public UpdateCenter(String centerName){
        this.centerName=centerName;
        JPanel storeTopPanel = new JPanel();
        JPanel storeCenterPanel = new JPanel();
        JPanel storeBottomPanel = new JPanel();
        LayoutManager storeTopLayout = new GridLayout(9,2,3,3);
        LayoutManager storeCenterLayout = new GridBagLayout();
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        Ldescription.setPreferredSize(new Dimension(175, 100));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.description.setBorder(border);
        this.description.setPreferredSize(new Dimension(300, 100));
        this.description.setLineWrap(true);
        this.description.setWrapStyleWord(true);
        this.description.setEditable(false);
        this.description.setOpaque(false);
        setLayout(changeStoreInfoLayout);
        storeTopPanel.setLayout(storeTopLayout);
        storeCenterPanel.setLayout(storeCenterLayout);
        storeBottomPanel.setLayout(storeBottomLayout);
        storeTopPanel.add(Lcentername);
        storeTopPanel.add(changeCenterName);
        storeTopPanel.add(Lmunicipality);
        storeTopPanel.add(changeMunicipality);
        storeTopPanel.add(Lturnover);
        storeTopPanel.add(changeTurnover);
        storeTopPanel.add(Lnr_shops);
        storeTopPanel.add(changeNr_Stores);
        storeTopPanel.add(Lsqm);
        storeTopPanel.add(changeSqm);
        storeTopPanel.add(Laddress);
        storeTopPanel.add(changeAddress);
        storeTopPanel.add(Ltlf);
        storeTopPanel.add(changeTlf);
        storeTopPanel.add(Lmail);
        storeTopPanel.add(changeMail);
        storeTopPanel.add(Lcarpark);
        storeTopPanel.add(changeCarPark);
        
        
        storeCenterPanel.add(Ldescription);
        storeCenterPanel.add(description);
        
        storeBottomPanel.add(backButton);
        storeBottomPanel.add(changeDescription);
        add(storeTopPanel, BorderLayout.NORTH);
        add(storeCenterPanel, BorderLayout.CENTER);
        add(storeBottomPanel, BorderLayout.SOUTH);
        pack();
        ActionUpdateStoreInfo actionChangeCenter = new ActionUpdateStoreInfo();
        changeCenterName.addActionListener(actionChangeCenter);
        changeMunicipality.addActionListener(actionChangeCenter);
        changeTurnover.addActionListener(actionChangeCenter);
        changeNr_Stores.addActionListener(actionChangeCenter);
        changeSqm.addActionListener(actionChangeCenter);
        changeAddress.addActionListener(actionChangeCenter);
        changeMail.addActionListener(actionChangeCenter);
        changeTlf.addActionListener(actionChangeCenter);
        changeCarPark.addActionListener(actionChangeCenter);
        changeDescription.addActionListener(actionChangeCenter);
        backButton.addActionListener(actionChangeCenter);
       
        //JFrame - change Trade -------------------------------------------------------------------------
        tradeDescription.setLineWrap(true);
        tradeDescription.setWrapStyleWord(true);
        tradeDescription.setEditable(false);
        tradeDescription.setOpaque(false);
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
        tradeTopPanel.add(titleDescription);
        tradeCenterPanel.add(scrollPaneTrade);
        tradeCenterPanel.add(tradeDescription);
        tradeBottomPanel.add(changeTradeBack);
        tradeBottomPanel.add(selectTrade);
        changeTradeFrame.add(tradeTopPanel, BorderLayout.NORTH);
        changeTradeFrame.add(tradeCenterPanel, BorderLayout.CENTER);
        changeTradeFrame.add(tradeBottomPanel, BorderLayout.SOUTH);
        changeTradeFrame.pack();
        ChangeTradeAction changeTradeaction = new ChangeTradeAction();
        changeTradeBack.addActionListener(changeTradeaction);
        selectTrade.addActionListener(changeTradeaction);
        tradeList.addMouseListener(changeTradeaction);
        
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
            if(actionEvent.getSource() == changeCenterName){
                String newStoreName = showInputDialog(null, "Please insert new store name: ");
                try {
                    openConnection();
                    int ok = setStoreName(username, newStoreName);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        if(newStoreName.length() < 16){
                            Lcentername.setText("Store name: " + newStoreName);
                        } else {
                            Lcentername.setText(newStoreName);
                        }

                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreName");
                }

            }
            else if(actionEvent.getSource() == changeMunicipality){
                defaultListModelTrade.clear();
                try {
                    openConnection();
                    trades = getTrades();
                    for(int i = 0; i < trades.size(); i++){
                        defaultListModelTrade.addElement(trades.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "changeTrade");
                }
                changeTradeFrame.setVisible(true);
                changeTradeFrame.setLocationRelativeTo(null);
            }
            else if(actionEvent.getSource() == changeTurnover){
                String newStoreLocation = showInputDialog(null, "Please insert new store name: ");
                try {
                    openConnection();
                    int ok = setStoreLocation(username, newStoreLocation);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Lturnover.setText("Location: " + newStoreLocation);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreLocation");
                }

            }
            else if(actionEvent.getSource() == changeNr_Stores){
                String newStoreFloorRead = showInputDialog(null, "Please insert new store name: ");
                int newStoreFloor = 0;
                boolean integerCheck = false;
                try {
                    newStoreFloor = Integer.parseInt(newStoreFloorRead);
                    integerCheck = true;
                }
                catch (NumberFormatException e){
                    showMessageDialog(null, "Incorrect floor! Please try again");
                }
                if(integerCheck){
                    try {
                        openConnection();
                        int ok = setStoreFloor(username, newStoreFloor);
                        if(ok == 1){
                            showMessageDialog(null, "Update complete");
                            Lnr_shops.setText("Floor: " + newStoreFloor);
                        }
                        closeConnection();
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeStoreFloor");
                    }
                }

            }
            else if(actionEvent.getSource() == changeSqm){
                String newStoreOpeningHrs = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrs(username, newStoreOpeningHrs);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Lsqm.setText("Opening hours: " + newStoreOpeningHrs);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrs");
                }
            }
            else if(actionEvent.getSource() == changeAddress){
                String newStoreOpeningHrsWeekends = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrsWeekends(username, newStoreOpeningHrsWeekends);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Laddress.setText("Weekends: " + newStoreOpeningHrsWeekends);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrsWeekends");
                }
            }
            else if(actionEvent.getSource() == changeMail){

            }
            else if(actionEvent.getSource() == changeDescription){
                changeDescriptionFrame.setVisible(true);
                changeDescriptionFrame.setLocationRelativeTo(null);
            }
            else if(actionEvent.getSource() == backButton){
                dispose();
            }
        }
    }
    private class ChangeTradeAction extends DatabaseConnection implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeTradeBack){
                changeTradeFrame.dispose();
            } else {
                int index = tradeList.getSelectedIndex();
                try {
                    openConnection();
                    int ok = setTrade(username, trades.get(index));
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        Lmunicipality.setText("Trade: " + trades.get(index));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "selectTrade");
                }
                changeTradeFrame.dispose();
            }
        }
        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getClickCount() == 1){
                int index = tradeList.getSelectedIndex();
                try {
                    openConnection();
                    String getTradeDescription = getTradeDescription(trades.get(index));
                    tradeDescription.setText(getTradeDescription);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "MouseClicked - Description");
                }
            }
        }
        public void mousePressed(MouseEvent mouseEvent) {}
        public void mouseReleased(MouseEvent mouseEvent) {}
        public void mouseEntered(MouseEvent mouseEvent) {}
        public void mouseExited(MouseEvent mouseEvent) {}
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
                Lcentername.setText("Center name: "+centerName);
                Lmunicipality.setText("Municipality: "+centerName);
                Lturnover.setText("Turnover: "+centerName);
                Lnr_shops.setText("Number of Stores: "+centerName);
                Lsqm.setText("meter ^2: "+centerName);
                Laddress.setText("Address: "+centerName);
                Ltlf.setText("Tlf:"+centerName);
                Lmail.setText("Mail:"+centerName);
                Lcarpark.setText("Car Parking:"+centerName);
                Ldescription.setText("Description:"+centerName);
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
            
        }
    }  
}
