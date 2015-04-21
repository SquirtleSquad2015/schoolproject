package shoolprodject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;


public class CenterManagerMenu extends JFrame {
	
    private final String username;
    private String currentCenterName;

    JButton viewShopCenterInfoBtn = new JButton("View center information");
    JButton viewAdminInfoBtn = new JButton("View administrative information");
    JButton updateCenterInfoBtn = new JButton("Update center information");
    JButton establishNewShopBtn = new JButton("Establish new shop");
    JButton updateAccountInfoBtn = new JButton("Update account information");
    JButton updateStoreInfoBtn = new JButton("Update store information");
    JButton manageUsersBtn = new JButton("Manage Users");
    JButton exitBtn = new JButton("Exit");


    // Administrative info Frame
    JFrame viewAdminFrame = new JFrame();
    JLabel turnoverLbl = new JLabel("Turnover", JLabel.CENTER);
    JLabel showTurnoverBtn = new JLabel("A milli", JLabel.CENTER);
    JButton backBtn = new JButton("Back");

    //Update center info frame
    JFrame updateCenterFrame = new JFrame();
    JLabel centerName = new JLabel("", JLabel.CENTER);
    JLabel sqm = new JLabel("", JLabel.CENTER);
    JLabel nrOfShops = new JLabel("", JLabel.CENTER);
    JLabel carPark = new JLabel("", JLabel.CENTER);
    JLabel mail = new JLabel("Update center email", JLabel.CENTER);
    JLabel phoneNumber = new JLabel("Update center phone number", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JLabel descriptionTitle = new JLabel("Description", JLabel.CENTER);
    JButton updateCenterNameBtn = new JButton("Update center name");
    JButton updateCenterAreaBtn = new JButton("Update center area");
    JButton updateCenterNrOfShopsBtn = new JButton("Update nr of shops");
    JButton updateCarParkBtn = new JButton("Update car park");
    JButton updateMail = new JButton("Update center email");
    JButton updatePhoneNumber = new JButton("Update center phone number");
    JButton updateDescriptionBtn = new JButton("Update description");
    JButton updateCenterBackBtn = new JButton("Back");

    // Establish new shop frame

    // Update Account info frame

    //Manage users frame
    JFrame manageUsersFrame = new JFrame();
    JButton validateAccountBtn = new JButton("Validate Accounts");
    JButton changeAccesslevelBtn = new JButton("Change access levels");

    public CenterManagerMenu(String username){
        super("Center Manager -" + username);
        this.username = username;
        LayoutManager layout = new GridLayout(3,3,2,2);
        setLayout(layout);
        add(viewShopCenterInfoBtn);
        add(viewAdminInfoBtn);
        add(updateCenterInfoBtn);
        add(establishNewShopBtn);
        add(updateAccountInfoBtn);
        add(manageUsersBtn);
        add(exitBtn);
        pack();

        Action action = new Action();
        viewShopCenterInfoBtn.addActionListener(action);
        viewAdminInfoBtn.addActionListener(action);
        updateCenterInfoBtn.addActionListener(action);
        establishNewShopBtn.addActionListener(action);
        updateAccountInfoBtn.addActionListener(action);
        manageUsersBtn.addActionListener(action);
        exitBtn.addActionListener(action);
        // Update center information ---------------------------------------------
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        description.setBorder(border);
        description.setPreferredSize(new Dimension(300, 100));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setOpaque(false);
        JPanel updateCenterTop = new JPanel();
        JPanel updateCenterCenter = new JPanel();
        JPanel updateCenterBottom = new JPanel();
        LayoutManager updateCenterLayout = new BorderLayout();
        LayoutManager updateCenterTopLayout = new GridLayout(6,2,3,3);
        LayoutManager updateCenterCenterLayout = new GridLayout(1,2,3,3);
        LayoutManager updateCenterBottomLayout = new GridLayout(1,2,3,3);
        updateCenterFrame.setLayout(updateCenterLayout);
        updateCenterTop.setLayout(updateCenterTopLayout);
        updateCenterCenter.setLayout(updateCenterCenterLayout);
        updateCenterBottom.setLayout(updateCenterBottomLayout);
        updateCenterTop.add(centerName);
        updateCenterTop.add(updateCenterNameBtn);
        updateCenterTop.add(nrOfShops);
        updateCenterTop.add(updateCenterNrOfShopsBtn);
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
        updateCenterFrame.add(updateCenterTop, BorderLayout.NORTH);
        updateCenterFrame.add(updateCenterCenter, BorderLayout.CENTER);
        updateCenterFrame.add(updateCenterBottom, BorderLayout.SOUTH);
        updateCenterFrame.pack();
    }
	private class Action extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent)  {
            if (actionEvent.getSource() == viewShopCenterInfoBtn) {
                // Insert Customer
            } else if (actionEvent.getSource() == viewAdminInfoBtn) {

            } else if (actionEvent.getSource() == updateCenterInfoBtn) {
                try {
                    openConnection();
                    currentCenterName = getCenter(username);
                    String currentNrofShops =getNoOfShops(currentCenterName);
                    String currentSqm = getSQM(currentCenterName);
                    String currentMail = getCenterMail(currentCenterName);
                    String currentPhoneNumber = getCenterTelephone(currentCenterName);

                    centerName.setText("Center name: " + currentCenterName);
                    nrOfShops.setText("Number of shops: " + currentNrofShops);
                    sqm.setText("Number of sqm: " + currentSqm);
                    mail.setText("Email: " + currentMail);
                    phoneNumber.setText("Phone Number: " + currentPhoneNumber);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "UpdateCenterInfoBtn");
                }
                updateCenterFrame.setVisible(true);
                updateCenterFrame.setLocationRelativeTo(null);
            }
            else if (actionEvent.getSource() == establishNewShopBtn) {

            } else if (actionEvent.getSource() == updateAccountInfoBtn) {
                try {
                    openConnection();
                    String email = getEmail(username);
                    String tlf = getPhoneNumber(username);
                    //currentEmail.setText(email);
                    //currentPhoneNumber.setText(tlf);
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "changeAccountInfo");
                }
                setVisible(false);
                //changeAccountInfoFrame.setVisible(true);
                //changeAccountInfoFrame.setLocationRelativeTo(null);
            } else if (actionEvent.getSource() == manageUsersBtn) {

            }
            else if(actionEvent.getSource() == updateStoreInfoBtn){

            } else {
                System.exit(0);
            }
        }
    }
}
