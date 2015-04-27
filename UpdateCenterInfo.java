
package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showConfirmDialog;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;


public class UpdateCenterInfo extends JFrame {
    private final String username;
    private final String currentCenterName;
    ArrayList<String> stores = new ArrayList<String>();
    JLabel sqm = new JLabel("", JLabel.CENTER);
    JLabel nrOfShops = new JLabel("", JLabel.CENTER);
    JLabel carPark = new JLabel("", JLabel.CENTER);
    JLabel mail = new JLabel("Update center email", JLabel.CENTER);
    JLabel phoneNumber = new JLabel("Update center phone number", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JLabel descriptionTitle = new JLabel("Description", JLabel.CENTER);
    JButton updateCenterAreaBtn = new JButton("Update square meters");
    JButton updateCenterNrOfShopsBtn = new JButton("Establish new store");
    JButton deleteStore = new JButton("Delete store");
    JButton updateCarParkBtn = new JButton("Update Car park");
    JButton updateMail = new JButton("Update email");
    JButton updatePhoneNumber = new JButton("Update phone number");
    JButton updateDescriptionBtn = new JButton("Update description");
    JButton updateCenterBackBtn = new JButton("Back");
    //JFrame - change description ----------------------------------
    JFrame changeDescriptionFrame = new JFrame();
    JTextArea newDescription = new JTextArea();
    JButton changeDescriptionBack = new JButton("Back");
    JButton selectDescription = new JButton("Change");
    // JFrame - delete shop -------------------
    JFrame selectStoreFrame = new JFrame();
    DefaultListModel defaultListModelSelect = new DefaultListModel();
    JList selectStoreList = new JList(defaultListModelSelect);
    JScrollPane scrollPaneSelect = new JScrollPane(selectStoreList);
    JButton selectStore = new JButton("Next");
    JButton selectStoreBack = new JButton("Back");
    Update update = new Update();
    int delay = 100; //milliseconds
    Timer timer = new Timer(delay, update);

    public UpdateCenterInfo(String username, String centerName) {
        timer.start();
        timer.setRepeats(false);
        this.username = username;
        currentCenterName = centerName;
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        description.setBorder(border);
        description.setPreferredSize(new Dimension(300, 100));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setOpaque(false);
        setTitle("Update center information - " + centerName);
        JPanel updateCenterTop = new JPanel();
        JPanel updateCenterCenter = new JPanel();
        JPanel updateCenterBottom = new JPanel();
        JPanel buttons = new JPanel();
        LayoutManager updateCenterLayout = new BorderLayout();
        LayoutManager updateCenterTopLayout = new GridLayout(5, 2, 3, 3);
        LayoutManager updateCenterCenterLayout = new GridLayout(1, 2, 3, 3);
        LayoutManager updateCenterBottomLayout = new GridLayout(1, 2, 3, 3);
        buttons.setLayout(updateCenterBottomLayout);
        setLayout(updateCenterLayout);
        updateCenterTop.setLayout(updateCenterTopLayout);
        updateCenterCenter.setLayout(updateCenterCenterLayout);
        updateCenterBottom.setLayout(updateCenterBottomLayout);
        buttons.add(updateCenterNrOfShopsBtn);
        buttons.add(deleteStore);
        updateCenterTop.add(nrOfShops);
        updateCenterTop.add(buttons);
        updateCenterTop.add(sqm);
        updateCenterTop.add(updateCenterAreaBtn);
        updateCenterTop.add(mail);
        updateCenterTop.add(updateMail);
        updateCenterTop.add(phoneNumber);
        updateCenterTop.add(updatePhoneNumber);
        updateCenterTop.add(carPark);
        updateCenterTop.add(updateCarParkBtn);
        updateCenterCenter.add(descriptionTitle);
        updateCenterCenter.add(description);
        updateCenterBottom.add(updateCenterBackBtn);
        updateCenterBottom.add(updateDescriptionBtn);
        add(updateCenterTop, BorderLayout.NORTH);
        add(updateCenterCenter, BorderLayout.CENTER);
        add(updateCenterBottom, BorderLayout.SOUTH);
        pack();
        Action action = new Action();
        updateCenterNrOfShopsBtn.addActionListener(action);
        updateCenterBackBtn.addActionListener(action);
        updateCenterAreaBtn.addActionListener(action);
        updateMail.addActionListener(action);
        updatePhoneNumber.addActionListener(action);
        updateCarParkBtn.addActionListener(action);
        updateDescriptionBtn.addActionListener(action);
        updateCenterBackBtn.addActionListener(action);
        deleteStore.addActionListener(action);

        // ChangeDescription ----------------------------------------------
        changeDescriptionFrame.setTitle("Change description");
        newDescription.setLineWrap(true);
        newDescription.setPreferredSize(new Dimension(300, 150));
        newDescription.setBorder(border);
        LayoutManager changeDescriptionLayout = new BorderLayout();
        LayoutManager layout1 = new GridLayout(1, 1, 3, 3);
        LayoutManager layout2 = new GridLayout(1, 2, 3, 3);
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
        
        //JFrame - delete store ----------------
        selectStoreFrame.setSize(300, 250);
        JPanel selectStoreTop = new JPanel();
        JPanel selectStoreButton = new JPanel();
        LayoutManager selectStoreTopLayout = new GridLayout(1,1,3,3);
        LayoutManager selectStoreButtonLayout = new GridLayout(1,2,3,3);
        selectStoreFrame.setLayout(new BorderLayout());
        selectStoreTop.setLayout(selectStoreTopLayout);
        selectStoreButton.setLayout(selectStoreButtonLayout);
        selectStoreTop.add(scrollPaneSelect);
        selectStoreButton.add(selectStoreBack);
        selectStoreButton.add(selectStore);
        selectStoreFrame.add(selectStoreTop, BorderLayout.NORTH);
        selectStoreFrame.add(selectStoreButton, BorderLayout.SOUTH);
        ActionSelectStore actionSelectStore = new ActionSelectStore();
        selectStoreBack.addActionListener(actionSelectStore);
        selectStore.addActionListener(actionSelectStore);
        
    }

    private class Action extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == updateCenterNrOfShopsBtn) {
                ArrayList<String> trades = new ArrayList<String>();
                String centerName = "";
                try {
                    openConnection();
                    trades = getTrades();
                    centerName = getCenter(username);
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "updateCenterNrOfShopsBtn");
                }
                EstablishNewStore establishNewStore = new EstablishNewStore(centerName, trades);
                establishNewStore.setVisible(true);
                establishNewStore.setLocationRelativeTo(null);
            } else if (actionEvent.getSource() == updateCenterAreaBtn) {
                int ok;
                String input = showInputDialog("Enter new number of square meters: ");
                try {
                    openConnection();
                    ok = setCenterSqm(input, currentCenterName);
                    if (ok == 1) {
                        showMessageDialog(null, "Update Complete");
                        sqm.setText("Number of square meters: " + input);
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "SetSqm");
                }
            } else if(actionEvent.getSource() == deleteStore){
                defaultListModelSelect.clear();
                try {
                    openConnection();
                    stores = getStore(currentCenterName);
                    for(int i = 0; i < stores.size(); i++){
                        defaultListModelSelect.addElement(stores.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    
                }
                selectStoreFrame.setTitle("Delete Store - " + currentCenterName);
                selectStoreFrame.setVisible(true);
                selectStoreFrame.setLocationRelativeTo(null);
            } 
            else if (actionEvent.getSource() == updateMail) {
                int ok;
                String input = showInputDialog("Enter new center mail: ");
                try {
                    openConnection();
                    ok = setCenterMail(input, currentCenterName);
                    if (ok == 1) {
                        showMessageDialog(null, "Update Complete");
                        mail.setText("Email: " + input);
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "SetEmail");
                }
            } else if (actionEvent.getSource() == updatePhoneNumber) {
                int ok;
                String input = showInputDialog("Enter new phoneNumber: ");
                try {
                    openConnection();
                    ok = setCenterPhoneNumber(input, currentCenterName);
                    if (ok == 1) {
                        showMessageDialog(null, "Update Complete");
                        phoneNumber.setText("Phone number: " + input);
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "SetSqm");
                }
            } else if (actionEvent.getSource() == updateCarParkBtn) {
                int ok;
                String input = showInputDialog("Enter carpark (n for no, y for yes): ");
                char carParkValue = input.charAt(0);
                try {
                    openConnection();
                    ok = setCenterCarPark(carParkValue, currentCenterName);
                    if (ok == 1) {
                        showMessageDialog(null, "Update Complete");
                        if (carParkValue == 'n') {
                            carPark.setText("Car park: No");
                        } else {
                            carPark.setText("Car park: Yes");
                        }
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "SetCarPark");
                }
            } else if (actionEvent.getSource() == updateDescriptionBtn) {
                changeDescriptionFrame.setVisible(true);
                changeDescriptionFrame.setLocationRelativeTo(null);
            } else {
                dispose();
            }
        }
    }

    private class ChangeDescriptionAction extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == selectDescription) {
                System.out.println(newDescription.getText());
                try {
                    openConnection();
                    int ok = setCenterDescription(newDescription.getText(), currentCenterName);
                    if (ok == 1) {
                        showMessageDialog(null, "Update complete");
                        description.setText(newDescription.getText());
                        changeDescriptionFrame.dispose();
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "setDescription");
                }
            } else {
                changeDescriptionFrame.dispose();
            }
        }
    }
    public class ActionSelectStore extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == selectStore){
                String storeName = selectStoreList.getSelectedValue().toString();
                String message = "Are you sure you want to delete store: " + storeName;
                int reply = showConfirmDialog(null, message, "Delete Store", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                    openConnection();
                    System.out.println(storeName + " - " +  currentCenterName);
                    
                    System.out.println(username + " - Dette er en test");
                    int ok = deleteStore(storeName, currentCenterName);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        timer.start();
                        defaultListModelSelect.removeElementAt(selectStoreList.getSelectedIndex());
                    }
                    closeConnection();
                    }
                    catch (Exception e){

                    }
                }
        
                
            } else {
                selectStoreFrame.dispose();
            }
        }
    }

    public class EstablishNewStore extends JFrame {
        private final String centerName;
        private final ArrayList<String> trades;
        JLabel enterStoreName = new JLabel("Enter store name: ", JLabel.CENTER);
        JLabel enterLocation = new JLabel("Enter location", JLabel.CENTER);
        JLabel enterFloor = new JLabel("Enter floor", JLabel.CENTER);
        JLabel enterOpeningHrs = new JLabel("Enter opening hours", JLabel.CENTER);
        JLabel enterOpeningHrsWeekends = new JLabel("Weekends: ", JLabel.CENTER);
        JLabel enterDescription = new JLabel("Description: ", JLabel.CENTER);
        JLabel selectTrade = new JLabel("Select Trade: ", JLabel.CENTER);
        JTextField storeName = new JTextField(20);
        JTextField location = new JTextField(20);
        JTextField floor = new JTextField(5);
        JTextField openingHrs = new JTextField(20);
        JTextField openingHrsWeekends = new JTextField();
        JTextArea description = new JTextArea();
        DefaultListModel defaultListModel = new DefaultListModel();
        JList list = new JList(defaultListModel);
        JScrollPane scrollPaneNewStore = new JScrollPane(list);
        JButton done = new JButton("Establish");
        JButton back = new JButton("Back");

        public EstablishNewStore(String centerName, ArrayList<String> trades) {
            setTitle("Establish new store - " + centerName);
            this.centerName = centerName;
            this.trades = trades;
            for( int i = 0; i < trades.size();i++){
                defaultListModel.addElement(trades.get(i));
            };
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            description.setBorder(border);
            JPanel topPanel = new JPanel();
            JPanel centerPanel = new JPanel();
            JPanel bottomPanel = new JPanel();
            LayoutManager frameLayout = new BorderLayout();
            LayoutManager topLayout = new GridLayout(5, 2, 3, 3);
            LayoutManager centerlayout = new GridLayout(2, 2, 3, 3);
            LayoutManager bottomLayout = new GridLayout(1, 2, 3, 3);
            setLayout(frameLayout);
            topPanel.setLayout(topLayout);
            centerPanel.setLayout(centerlayout);
            bottomPanel.setLayout(bottomLayout);
            description.setPreferredSize(new Dimension(100, 100));
            topPanel.add(enterStoreName);
            topPanel.add(storeName);
            topPanel.add(enterLocation);
            topPanel.add(location);
            topPanel.add(enterFloor);
            topPanel.add(floor);
            topPanel.add(enterOpeningHrs);
            topPanel.add(openingHrs);
            topPanel.add(enterOpeningHrsWeekends);
            topPanel.add(openingHrsWeekends);
            centerPanel.add(selectTrade);
            centerPanel.add(scrollPaneNewStore);
            centerPanel.add(enterDescription);
            centerPanel.add(description);
            bottomPanel.add(back);
            bottomPanel.add(done);
            add(topPanel, BorderLayout.NORTH);
            add(centerPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
            pack();
            Action action = new Action();
            done.addActionListener(action);
            back.addActionListener(action);
        }

        private class Action extends DatabaseConnection implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == done) {
                    String storeNameTyped = storeName.getText();
                    String tradeSelected = trades.get(list.getSelectedIndex());
                    String locationTyped = location.getText();
                    String floorTyped = floor.getText();
                    String openingHrsTyped = openingHrs.getText();
                    String openingHrsWeekendsTyped = openingHrsWeekends.getText();
                    String descriptionTyped = description.getText();
                    
                    try {
                        openConnection();
                        int ok = regNewStore(storeNameTyped, centerName, tradeSelected, locationTyped, floorTyped,
                                openingHrsTyped, openingHrsWeekendsTyped, descriptionTyped);
                        if (ok == 1) {
                            showMessageDialog(null, "Update complete");
                            String number = getNoOfShops(centerName);
                            nrOfShops.setText("Number of shops: " + number);
                            dispose();
                        }
                        closeConnection();
                    } catch (Exception e) {
                        Database.printMesssage(e, "EstablishStore");
                    }
                } else {
                    dispose();
                }
            }
        }
    }

    class Update extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent hendelse) {
            try {
                openConnection();
                String currentNrofShops = getNoOfShops(currentCenterName);
                String currentSqm = getSQM(currentCenterName);
                String currentMail = getCenterMail(currentCenterName);
                String currentPhoneNumber = getCenterTelephone(currentCenterName);
                String currentCarPark = getParking(currentCenterName);
                String currentDescription = getCenterDescription(currentCenterName);
                nrOfShops.setText("Number of shops: " + currentNrofShops);
                sqm.setText("Number of square meters: " + currentSqm);
                mail.setText("Email: " + currentMail);
                phoneNumber.setText("Phone number: " + currentPhoneNumber);
                if (currentCarPark.equals('n')) {
                    carPark.setText("Car park: No");
                } else {
                    carPark.setText("Car park: Yes");
                }
                description.setText(currentDescription);

                closeConnection();
            } catch (Exception e) {
                Database.printMesssage(e, "Update   ");
            }

        }
    }
}
