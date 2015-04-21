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

public class ShopManagerMenu extends JFrame {
    private String username;
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
    //JFrame - change shop info -----------------------------------------
    JFrame changeStoreInfoFrame = new JFrame();
    JLabel storeName = new JLabel("Store name: ", JLabel.CENTER);
    JLabel trade = new JLabel("Trade: ", JLabel.CENTER);
    JLabel location = new JLabel("Location: ", JLabel.CENTER);
    JLabel floor = new JLabel("Floor: ", JLabel.CENTER);
    JLabel openingHrs = new JLabel("Opening hours: ", JLabel.CENTER);
    JLabel openingHrsWeekends = new JLabel("Weekends: ", JLabel.CENTER);
    JLabel descriptionTitle = new JLabel("Description", JLabel.CENTER);
    JLabel turnover = new JLabel("Turnover", JLabel.CENTER);
    JTextArea description = new JTextArea("Dette er en beskrivelse av en butikk falødjaølsdaølksd løa adsklaølsdkløa" +
            "ajødaøldkaløsdkaølsdklø akkløaksdølakd løak dølasdkløakd ølsadk aøldsk las-dk");
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
        // JFrame - Change account info -------------------------------
        LayoutManager changeInfoLayout = new BorderLayout();
        changeAccountInfoFrame.setTitle("Change account information - " + username);
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
        changeAccountInfoFrame.add(changePanel, BorderLayout.NORTH);
        changeAccountInfoFrame.add(passwordPanel, BorderLayout.CENTER);
        changeAccountInfoFrame.add(buttonPanel, BorderLayout.SOUTH);
        changeAccountInfoFrame.pack();
        ChangeAccountAction changeAccountAction = new ChangeAccountAction();
        changePassword.addActionListener(changeAccountAction);
        changeEmail.addActionListener(changeAccountAction);
        changePhoneNumber.addActionListener(changeAccountAction);
        changeAccountInfoBack.addActionListener(changeAccountAction);
        // JFrame - change store info ---------------------------------------
        JPanel storeTopPanel = new JPanel();
        JPanel storeCenterPanel = new JPanel();
        JPanel storeBottomPanel = new JPanel();
        LayoutManager storeTopLayout = new GridLayout(7,2,3,3);
        LayoutManager storeCenterLayout = new GridBagLayout();
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(changeStoreInfoFrame.getContentPane(), BoxLayout.Y_AXIS);
        descriptionTitle.setPreferredSize(new Dimension(175, 100));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        description.setBorder(border);
        description.setPreferredSize(new Dimension(300, 100));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setOpaque(false);
        changeStoreInfoFrame.setLayout(changeStoreInfoLayout);
        storeTopPanel.setLayout(storeTopLayout);
        storeCenterPanel.setLayout(storeCenterLayout);
        storeBottomPanel.setLayout(storeBottomLayout);
        storeTopPanel.add(storeName);
        storeTopPanel.add(changeStoreName);
        storeTopPanel.add(trade);
        storeTopPanel.add(changeTrade);
        storeTopPanel.add(location);
        storeTopPanel.add(changeLocation);
        storeTopPanel.add(floor);
        storeTopPanel.add(changeFloor);
        storeTopPanel.add(openingHrs);
        storeTopPanel.add(changeOpeningHrs);
        storeTopPanel.add(openingHrsWeekends);
        storeTopPanel.add(changeOpeningHrsWeekends);
        storeTopPanel.add(turnover);
        storeTopPanel.add(addAnnualTurnover);
        storeCenterPanel.add(descriptionTitle);
        storeCenterPanel.add(description);
        storeBottomPanel.add(changeStoreInfoBack);
        storeBottomPanel.add(changeDescription);
        changeStoreInfoFrame.add(storeTopPanel, BorderLayout.NORTH);
        changeStoreInfoFrame.add(storeCenterPanel, BorderLayout.CENTER);
        changeStoreInfoFrame.add(storeBottomPanel, BorderLayout.SOUTH);
        changeStoreInfoFrame.pack();
        ActionChangeStoreInfo actionChangeStoreInfo = new ActionChangeStoreInfo();
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
        changeTradeAction changeTradeaction = new changeTradeAction();
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
    private class Action extends DatabaseConnection implements ActionListener {

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
                    changeStoreInfoFrame.setTitle("Change store info - " + getShopName(username));
                    shopName = getShopName(username);
                    storeName.setText("Store name: " + shopName);
                    trade.setText("Trade: " + getShopTrade(username));
                    location.setText("Location: " + getShopLocation(username));
                    floor.setText("Floor: " + getShopFloor(username));
                    openingHrs.setText("Opening hours: " + getShopOpeningHrs(username));
                    openingHrsWeekends.setText("Weekends: " + getShopOpeningHrsWeekends(username));
                    turnover.setText("Annual turnover: " + getShopTurnover(username));
                    description.setText(getShopDescription(username));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenter");
                }
                changeStoreInfoFrame.setVisible(true);
                changeStoreInfoFrame.setLocationRelativeTo(null);
                setVisible(false);
            } else if (check == changeAccountInfo) {
                try {
                    openConnection();
                    String email = getEmail(username);
                    String tlf = getPhoneNumber(username);
                    currentEmail.setText(email);
                    currentPhoneNumber.setText(tlf);
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "changeAccountInfo");
                }
                setVisible(false);
                changeAccountInfoFrame.setVisible(true);
                changeAccountInfoFrame.setLocationRelativeTo(null);
            } else {
                System.exit(0);
            }
        }
    }
    private class ActionChangeStoreInfo extends DatabaseConnection implements ActionListener{
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
                changeStoreInfoFrame.dispose();
                setVisible(true);
            }
        }
    }
    private class ChangeAccountAction extends DatabaseConnection implements ActionListener {
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
                changeAccountInfoFrame.dispose();
                setVisible(true);
            }
        }
    }
    private class changeTradeAction extends DatabaseConnection implements ActionListener, MouseListener {
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
                changeDescriptionFrame.dispose();
            }
        }
        
    }
}

