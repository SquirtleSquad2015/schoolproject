package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class CenterManagerMenu extends JFrame {

    private final String username;
    private String currentCenterName;
    private ArrayList<String> stores = new ArrayList<String>();
    JButton viewShopCenterInfoBtn = new JButton("View center information");
    JButton viewAdminInfoBtn = new JButton("View annual turnover");
    JButton updateCenterInfoBtn = new JButton("Update center information");
    JButton updateStoreInfoBtn = new JButton("Update store information");
    JButton updateAccountInfoBtn = new JButton("Update account information");
    JButton manageUsersBtn = new JButton("Manage Users");
    JButton exitBtn = new JButton("Exit");

    JFrame selectStoreFrame = new JFrame();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    DefaultListModel defaultListModel = new DefaultListModel();
    JList storeList = new JList(defaultListModel);
    JScrollPane scrollPane = new JScrollPane(storeList);
    JButton selectStore = new JButton("Next");
    JButton selectStoreBack = new JButton("Back");

    public CenterManagerMenu(String username){
        super("Center manager - " + username);
        this.username = username;
        LayoutManager layout = new GridLayout(7,1,2,2);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(layout);
        add(viewShopCenterInfoBtn);
        add(viewAdminInfoBtn);
        add(updateCenterInfoBtn);
        add(updateStoreInfoBtn);
        add(manageUsersBtn);
        add(updateAccountInfoBtn);
        add(exitBtn);
        pack();
        Action action = new Action();
        viewShopCenterInfoBtn.addActionListener(action);
        viewAdminInfoBtn.addActionListener(action);
        updateCenterInfoBtn.addActionListener(action);
        updateStoreInfoBtn.addActionListener(action);
        updateAccountInfoBtn.addActionListener(action);
        manageUsersBtn.addActionListener(action);
        exitBtn.addActionListener(action);


        LayoutManager borderLayout = new BorderLayout();
        LayoutManager topLayout = new GridLayout(1,1,0,0);
        LayoutManager bottomLayout = new GridLayout(1,2,0,0);
        selectStoreFrame.setLayout(borderLayout);
        topPanel.setLayout(topLayout);
        bottomPanel.setLayout(bottomLayout);
        topPanel.add(scrollPane);
        bottomPanel.add(selectStoreBack);
        bottomPanel.add(selectStore);
        selectStoreFrame.add(topPanel, BorderLayout.NORTH);
        selectStoreFrame.add(bottomPanel, BorderLayout.SOUTH);
        selectStoreFrame.pack();
        ActionUpdateShop actionUpdateShop = new ActionUpdateShop();
        selectStore.addActionListener(actionUpdateShop);
        selectStoreBack.addActionListener(actionUpdateShop);
    }

    private class Action extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent)  {
            if (actionEvent.getSource() == viewShopCenterInfoBtn) {
                Customer customer = new Customer();
                customer.setVisible(true);
                customer.setLocationRelativeTo(null);
            } else if (actionEvent.getSource() == viewAdminInfoBtn) {
                try {
                    openConnection();
                    currentCenterName = getCenter(username);
                    closeConnection();
                }
                catch(Exception e){
                    Database.printMesssage(e, "turnover");
                }
                ViewAnnualTurnover viewAnnualTurnover = new ViewAnnualTurnover(currentCenterName);
                viewAnnualTurnover.setVisible(true);
                viewAnnualTurnover.setLocationRelativeTo(null);
                
            } else if (actionEvent.getSource() == updateCenterInfoBtn) {
                try {
                    openConnection();
                    currentCenterName = getCenter(username);
                    UpdateCenterInfo updateCenterInfo = new UpdateCenterInfo(username, currentCenterName);
                    updateCenterInfo.setVisible(true);
                    updateCenterInfo.setLocationRelativeTo(null);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "UpdateCenterInfoBtn");
                }
            }
            else if (actionEvent.getSource() == updateAccountInfoBtn) {
                String tlf = "";
                String email = "";
                try {
                    openConnection();
                    email = getEmail(username);
                    tlf = getPhoneNumber(username);
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "changeAccountInfo");
                }
                ChangeAccountInfo changeAccountInfo = new ChangeAccountInfo(username, email, tlf);
                changeAccountInfo.setVisible(true);
                changeAccountInfo.setLocationRelativeTo(null);
            } else if (actionEvent.getSource() == manageUsersBtn) {

            }
            else if(actionEvent.getSource() == updateStoreInfoBtn){
                try {
                    openConnection();
                    currentCenterName = getCenter(username);
                    defaultListModel.clear();
                    stores = getStore(currentCenterName);
                    for(int i = 0; i < stores.size(); i++){
                        defaultListModel.addElement(stores.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "updateStoreInfo");
                }
                selectStoreFrame.setTitle("Select store - " + currentCenterName);
                selectStoreFrame.setVisible(true);
                selectStoreFrame.setLocationRelativeTo(null);

            } else {
                System.exit(0);
            }
        }
    }
    public class ActionUpdateShop extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == selectStore){
                int index = storeList.getSelectedIndex();
                try {
                    openConnection();
                    System.out.println(currentCenterName);
                    System.out.println(stores.get(index));
                    UpdateStoreInfo updateStoreInfo = new UpdateStoreInfo(currentCenterName, stores.get(index));
                    updateStoreInfo.setVisible(true);
                    updateStoreInfo.setLocationRelativeTo(null);
                    closeConnection();
                }
                catch (Exception e){

                }
            } else {
                selectStoreFrame.dispose();
            }
        }
    }
}
