
package shoolprodject;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showConfirmDialog;

import static javax.swing.JOptionPane.showMessageDialog;

public class ManageUsers extends JFrame{
    private final String centername;
    private ArrayList<String> userArray = new ArrayList<String>();
    private ArrayList<String> storeArray = new ArrayList<String>();
    private ArrayList<String> unActivArray = new ArrayList<String>();
    Update update = new Update();
    int delay = 100; //milliseconds
    Timer timer = new Timer(delay, update);
    JLabel storesWithoutUsers = new JLabel("Stores without a user: ", JLabel.CENTER);
    JLabel usersWithoutStore = new JLabel("Users without a store: ", JLabel.CENTER);
    DefaultListModel defaultListModel = new DefaultListModel();
    JList storeList = new JList(defaultListModel);
    JScrollPane stores = new JScrollPane(storeList);
    DefaultListModel defaultListModel2 = new DefaultListModel();
    JList userList = new JList(defaultListModel2);
    JScrollPane users = new JScrollPane(userList);
    JButton back = new JButton("Back");
    JButton setUserToStore = new JButton("Set user to store");
    JLabel unActivUser = new JLabel("Unactive users: ", JLabel.CENTER);
    DefaultListModel defaultListModel3 = new DefaultListModel();
    JList unActivList = new JList(defaultListModel3);
    JScrollPane unActivusers = new JScrollPane(unActivList);
    JButton makeActiv = new JButton("Make user active");
    JButton deleteUser = new JButton("Delete users");
    
    JFrame deleteUsersFrame = new JFrame();
    JPanel deleteUsersTopPanel = new JPanel();
    JPanel deleteUsersBottomPanel = new JPanel();
    DefaultListModel defaultListModelDU = new DefaultListModel();
    JList deleteUserList = new JList(defaultListModelDU);
    JScrollPane scrollPaneDU = new JScrollPane(deleteUserList);
    JButton selectStore = new JButton("Next");
    JButton selectStoreBack = new JButton("Back");


    public ManageUsers(String centername){
        this.centername = centername;
        timer.start();
        timer.setRepeats(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Manage users - " + centername);
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel buttonPanelCenter = new JPanel();
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        LayoutManager topLayout = new GridLayout(1,2,3,3);
        LayoutManager centerLayout = new GridLayout(2,2,3,3);
        LayoutManager buttonLayout = new GridLayout(1,4,3,3);
        topPanel.setLayout(topLayout);
        centerPanel.setLayout(centerLayout);
        buttonPanelCenter.setLayout(buttonLayout);
        topPanel.add(storesWithoutUsers);
        topPanel.add(usersWithoutStore);
        centerPanel.add(stores);
        centerPanel.add(users);
        centerPanel.add(unActivUser);
        centerPanel.add(unActivusers);
        buttonPanelCenter.add(back);
        buttonPanelCenter.add(deleteUser);
        buttonPanelCenter.add(setUserToStore);
        buttonPanelCenter.add(makeActiv);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanelCenter, BorderLayout.SOUTH);
        pack();
        Action action = new Action();
        setUserToStore.addActionListener(action);
        makeActiv.addActionListener(action);
        back.addActionListener(action);
        // JFrame - delete users ----------------------------
        deleteUsersFrame.setSize(300, 200);
        LayoutManager borderLayout = new BorderLayout();
        LayoutManager deleteUsersTopLayout = new GridLayout(1,1,0,0);
        LayoutManager deleteUsersBottomLayout = new GridLayout(1,2,0,0);
        deleteUsersFrame.setLayout(borderLayout);
        deleteUsersTopPanel.setLayout(deleteUsersTopLayout);
        deleteUsersBottomPanel.setLayout(deleteUsersBottomLayout);
        deleteUsersTopPanel.add(scrollPaneDU);
        deleteUsersBottomPanel.add(selectStoreBack);
        deleteUsersBottomPanel.add(selectStore);
        deleteUsersFrame.add(deleteUsersTopPanel, BorderLayout.NORTH);
        deleteUsersFrame.add(deleteUsersBottomPanel, BorderLayout.SOUTH);
        //selectStore.addActionListener();
        //selectStoreBack.addActionListener();
        
    }
    private class Action extends DatabaseConnection implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == setUserToStore){
                String store = storeList.getSelectedValue().toString();
                String username = userList.getSelectedValue().toString();
                try {
                    openConnection();
                    String activ = getUserActiv(username);
                    if(activ.equals("n")){
                        String title = "User activation";
                        String message = "Would you like to set user: " + username + " as activ?";
                        int reply = showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                        if(reply == JOptionPane.YES_OPTION){
                            int setActiv = setUserActiv("y", username);
                        }   
                    }
                    int ok = setStoreUser(username, store, centername);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        timer.start();
                    }
                    closeConnection();
                }
                catch (Exception e){

                }
            }
            else if(actionEvent.getSource() == makeActiv){
                String setActiv = unActivList.getSelectedValue().toString();
                try {
                    openConnection();
                    int ok = setUserActiv("y", setActiv);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        timer.start();
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "makeActiv");
                }
            }
            else if(actionEvent.getSource() == deleteUser){
                
            } else {
                dispose();
            }
        }
    }
    class Update extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent hendelse) {
            defaultListModel.clear();
            defaultListModel2.clear();
            defaultListModel3.clear();
            try {
                openConnection();
                storeArray = getStoresWithoutUser(centername);
                for (int i = 0; i < storeArray.size(); i++){
                    defaultListModel.addElement(storeArray.get(i));
                }
                userArray = getUsersWithoutStore(centername);
                for(int i = 0; i < userArray.size(); i++){
                    defaultListModel2.addElement(userArray.get(i));
                }
                unActivArray = getUsersNotActiv(centername);
                for(int i = 0; i < unActivArray.size(); i++){
                    defaultListModel3.addElement(unActivArray.get(i));
                }
                closeConnection();
            } catch (Exception e) {
                Database.printMesssage(e, "Update   ");
            }

        }
    }
}
