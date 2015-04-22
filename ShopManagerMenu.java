package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
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
    /*JFrame changeStoreInfoFrame = new JFrame();
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
    JButton changeStoreInfoBack = new JButton("Back");*/
    /*//JFrame - change Trade --------------------------------------
    ArrayList<String> trades;
    JFrame changeTradeFrame = new JFrame();
    JLabel titleTrade = new JLabel("Trade", JLabel.CENTER);
    JLabel titleDescription = new JLabel("Description");
    DefaultListModel defaultListModelTrade = new DefaultListModel();
    JList tradeList = new JList(defaultListModelTrade);
    JScrollPane scrollPaneTrade = new JScrollPane(tradeList);
    JTextArea tradeDescription = new JTextArea("Description");
    JButton changeTradeBack = new JButton("Back");
    JButton selectTrade = new JButton("Select");*/
    //JFrame - change description ----------------------------------
    /*JFrame changeDescriptionFrame = new JFrame();
    JTextArea newDescription = new JTextArea();
    JButton changeDescriptionBack = new JButton("Back");
    JButton selectDescription = new JButton("Change");*/



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
       /* LayoutManager changeInfoLayout = new BorderLayout();
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
        changeAccountInfoBack.addActionListener(changeAccountAction);*/
        // JFrame - change store info ---------------------------------------
        /*JPanel storeTopPanel = new JPanel();
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
        changeStoreInfoBack.addActionListener(actionChangeStoreInfo);*/
        //JFrame - change Trade -------------------------------------------------------------------------
        /*tradeDescription.setLineWrap(true);
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
        tradeList.addMouseListener(changeTradeaction);*/
        // JFrame - changeDescription ------------------------------------------------------------------------
        /*changeDescriptionFrame.setTitle("Change description");
        newDescription.setLineWrap(true);
        newDescription.setPreferredSize(new Dimension(300, 150));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
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
        changeDescriptionBack.addActionListener(changeDescriptionAction);*/






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
                    shopName = getShopName(username);
                    String trade = getShopTrade(username);
                    String location = getShopLocation(username);
                    String floor = getShopFloor(username);
                    String openingHrs = getShopOpeningHrs(username);
                    String openingHrsWeekends = getShopOpeningHrsWeekends(username);
                    String turnover = getShopTurnover(username);
                    String description = getShopDescription(username);
                    UpdateStoreInfo updateStoreInfo = new UpdateStoreInfo(username , shopName, trade, location, floor,
                    openingHrs, openingHrsWeekends, turnover, description);
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

