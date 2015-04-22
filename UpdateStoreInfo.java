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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class UpdateStoreInfo extends JFrame{
    private final String username;
    /*private String currentShopName;
    private String currentTrade;
    private String currentLocation;
    private String currentFloor;
    private String currentOpeningHrs;
    private String currentOpeningHrsWeekends;
    private String currentTurnover;
    private String currentDescription;*/
    
    JLabel storeName = new JLabel("Store name: ", JLabel.CENTER);
    JLabel trade = new JLabel("Trade: ", JLabel.CENTER);
    JLabel location = new JLabel("Location: ", JLabel.CENTER);
    JLabel floor = new JLabel("Floor: ", JLabel.CENTER);
    JLabel openingHrs = new JLabel("Opening hours: ", JLabel.CENTER);
    JLabel openingHrsWeekends = new JLabel("Weekends: ", JLabel.CENTER);
    JLabel descriptionTitle = new JLabel("Description", JLabel.CENTER);
    JLabel turnover = new JLabel("Turnover", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JButton changeStoreName = new JButton("Change");
    JButton changeTrade = new JButton("Change");
    JButton changeLocation = new JButton("Change");
    JButton changeFloor = new JButton("Change");
    JButton changeOpeningHrs = new JButton("Change");
    JButton changeOpeningHrsWeekends = new JButton("Change");
    JButton addAnnualTurnover = new JButton("Add annual turnover");
    JButton changeDescription = new JButton("Change description");
    JButton changeStoreInfoBack = new JButton("Back");
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
    
    
    public UpdateStoreInfo(String username, String storeName, String trade, String location, String floor,
                    String openingHrs, String openingHrsWeekends, String turnover, String description){
        this.username = username;
        this.storeName.setText("Store name: " + storeName);
        this.trade.setText("Trade: " + trade);
        this.location.setText("Location: " + location);
        this.floor.setText("Floor: " + floor);
        this.openingHrs.setText("Opening hours: " + openingHrs);
        this.openingHrsWeekends.setText("Weekends: " + openingHrsWeekends);
        this.turnover.setText("Turnover: " + turnover);
        this.description.setText(description);
        JPanel storeTopPanel = new JPanel();
        JPanel storeCenterPanel = new JPanel();
        JPanel storeBottomPanel = new JPanel();
        LayoutManager storeTopLayout = new GridLayout(7,2,3,3);
        LayoutManager storeCenterLayout = new GridBagLayout();
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        descriptionTitle.setPreferredSize(new Dimension(175, 100));
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
        storeTopPanel.add(this.storeName);
        storeTopPanel.add(changeStoreName);
        storeTopPanel.add(this.trade);
        storeTopPanel.add(changeTrade);
        storeTopPanel.add(this.location);
        storeTopPanel.add(changeLocation);
        storeTopPanel.add(this.floor);
        storeTopPanel.add(changeFloor);
        storeTopPanel.add(this.openingHrs);
        storeTopPanel.add(changeOpeningHrs);
        storeTopPanel.add(this.openingHrsWeekends);
        storeTopPanel.add(changeOpeningHrsWeekends);
        storeTopPanel.add(this.turnover);
        storeTopPanel.add(addAnnualTurnover);
        storeCenterPanel.add(descriptionTitle);
        storeCenterPanel.add(this.description);
        storeBottomPanel.add(changeStoreInfoBack);
        storeBottomPanel.add(changeDescription);
        add(storeTopPanel, BorderLayout.NORTH);
        add(storeCenterPanel, BorderLayout.CENTER);
        add(storeBottomPanel, BorderLayout.SOUTH);
        pack();
        ActionUpdateStoreInfo actionChangeStoreInfo = new ActionUpdateStoreInfo();
        changeStoreName.addActionListener(actionChangeStoreInfo);
        changeTrade.addActionListener(actionChangeStoreInfo);
        changeLocation.addActionListener(actionChangeStoreInfo);
        changeFloor.addActionListener(actionChangeStoreInfo);
        changeOpeningHrs.addActionListener(actionChangeStoreInfo);
        changeOpeningHrsWeekends.addActionListener(actionChangeStoreInfo);
        addAnnualTurnover.addActionListener(actionChangeStoreInfo);
        changeDescription.addActionListener(actionChangeStoreInfo);
        changeStoreInfoBack.addActionListener(actionChangeStoreInfo);
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
    }
    private class ActionUpdateStoreInfo extends DatabaseConnection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeStoreName){
                String newStoreName = showInputDialog(null, "Please insert new store name: ");
                try {
                    openConnection();
                    int ok = setStoreName(username, newStoreName);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        if(newStoreName.length() < 16){
                            storeName.setText("Store name: " + newStoreName);
                        } else {
                            storeName.setText(newStoreName);
                        }

                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreName");
                }

            }
            else if(actionEvent.getSource() == changeTrade){
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
            else if(actionEvent.getSource() == changeLocation){
                String newStoreLocation = showInputDialog(null, "Please insert new store name: ");
                try {
                    openConnection();
                    int ok = setStoreLocation(username, newStoreLocation);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        location.setText("Location: " + newStoreLocation);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreLocation");
                }

            }
            else if(actionEvent.getSource() == changeFloor){
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
                            floor.setText("Floor: " + newStoreFloor);
                        }
                        closeConnection();
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeStoreFloor");
                    }
                }

            }
            else if(actionEvent.getSource() == changeOpeningHrs){
                String newStoreOpeningHrs = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrs(username, newStoreOpeningHrs);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        openingHrs.setText("Opening hours: " + newStoreOpeningHrs);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrs");
                }
            }
            else if(actionEvent.getSource() == changeOpeningHrsWeekends){
                String newStoreOpeningHrsWeekends = showInputDialog(null, "Please insert new opening hours: ");
                try {
                    openConnection();
                    int ok = setStoreOpeningHrsWeekends(username, newStoreOpeningHrsWeekends);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        openingHrsWeekends.setText("Weekends: " + newStoreOpeningHrsWeekends);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeStoreOpeningHrsWeekends");
                }
            }
            else if(actionEvent.getSource() == addAnnualTurnover){

            }
            else if(actionEvent.getSource() == changeDescription){
                changeDescriptionFrame.setVisible(true);
                changeDescriptionFrame.setLocationRelativeTo(null);
            }
            else if(actionEvent.getSource() == changeStoreInfoBack){
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
                        trade.setText("Trade: " + trades.get(index));
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
}
