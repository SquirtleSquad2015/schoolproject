package shoolprodject;
import shoolprodject.DatabasePackage.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;


public class CustomerServiceMenu extends JFrame {
    private final String username;
    private String centerName;
    JButton viewInfo = new JButton("View Information");
    JButton updateInfo = new JButton("Update customerservice information");
    JButton answerQuestions = new JButton("Answer questions");
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
    //JFrame - change center info -----------------------------------------
    JFrame changeCenterInfoFrame = new JFrame();
    JLabel currentCenterMail = new JLabel("Center.mail@gmail.com", JLabel.CENTER);
    JLabel currentCenterPhoneNumber = new JLabel("41214994", JLabel.CENTER);
    JButton changeCenterMail = new JButton("Change email");
    JButton changeCenterPhoneNumber = new JButton("Change phone number");
    JButton changeCenterBack = new JButton("Back");


    public CustomerServiceMenu(String username) {
        super("CustomerService - " + username);
        this.username = username;
        LayoutManager layout = new GridLayout(5, 2, 3, 3);
        setLayout(layout);
        add(viewInfo);
        add(answerQuestions);
        add(updateInfo);
        add(changeAccountInfo);
        add(exit);
        pack();
        Action action = new Action();
        viewInfo.addActionListener(action);
        answerQuestions.addActionListener(action);
        updateInfo.addActionListener(action);
        changeAccountInfo.addActionListener(action);
        exit.addActionListener(action);
        // JFrame - change center info ----------------------------------------------
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        LayoutManager changeCenterInfoLayout = new BorderLayout();
        LayoutManager topLayout = new GridLayout(2,2,3,3);
        LayoutManager bottomLayout = new GridLayout(1,1,3,3);
        topPanel.setLayout(topLayout);
        bottomPanel.setLayout(bottomLayout);
        changeCenterInfoFrame.setLayout(changeCenterInfoLayout);
        topPanel.add(currentCenterMail);
        topPanel.add(changeCenterMail);
        topPanel.add(currentCenterPhoneNumber);
        topPanel.add(changeCenterPhoneNumber);
        bottomPanel.add(changeCenterBack);
        changeCenterInfoFrame.add(topPanel, BorderLayout.NORTH);
        changeCenterInfoFrame.add(bottomPanel, BorderLayout.SOUTH);
        changeCenterInfoFrame.pack();
        ActionChangeCenterInfo actionChangeCenterInfo = new ActionChangeCenterInfo();
        changeCenterMail.addActionListener(actionChangeCenterInfo);
        changeCenterPhoneNumber.addActionListener(actionChangeCenterInfo);
        changeCenterBack.addActionListener(actionChangeCenterInfo);

    }


    private class Action extends DatabaseConnection implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton) source.getSource();

            if (check == viewInfo) {
                System.out.println("ViewInfo");
                shoolprodject.Customer CostumerVindu = new shoolprodject.Customer();
                CostumerVindu.setLocationRelativeTo(null);
                CostumerVindu.setVisible(true);
            } else if (check == answerQuestions) {
                System.out.println("Answer Questions");
                shoolprodject.AnswerQuestion answer = new shoolprodject.AnswerQuestion(username);
                answer.setVisible(true);
                answer.setLocationRelativeTo(null);
            } else if (check == updateInfo) {
                try {
                    openConnection();
                    centerName = getCenter(username);
                    changeCenterInfoFrame.setTitle("Change center information - " + centerName);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenter");
                }
                setVisible(false);
                changeCenterInfoFrame.setVisible(true);
                changeCenterInfoFrame.setLocationRelativeTo(null);
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
    public class ActionChangeCenterInfo extends DatabaseConnection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeCenterMail){
                String newMail = showInputDialog(null, "Enter new mail: ");
                int ok;
                try {
                    openConnection();
                    ok = setCenterMail(newMail, centerName);
                    if(ok == 1){
                        showMessageDialog(null, "Update complete");
                        currentCenterMail.setText(newMail);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "Change center mail");
                }
            }
            else if(actionEvent.getSource() == changeCenterPhoneNumber){
                String newPhoneNumber = showInputDialog(null, "Enter new mail: ");
                boolean phoneNumberCheck = false;
                int ok;
                try {
                    int newPhoneNumberInt = Integer.parseInt(newPhoneNumber);
                    phoneNumberCheck = true;
                }
                catch (NumberFormatException e){
                    showMessageDialog(null, "Invalid phone number");
                }
                if(phoneNumberCheck){
                    try {
                        openConnection();
                        ok = setCenterPhoneNumber(newPhoneNumber, centerName);
                        if(ok == 1){
                            showMessageDialog(null, "Update complete");
                            currentPhoneNumber.setText(newPhoneNumber);
                        }
                        closeConnection();
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "Change center mail");
                    }
                } else {
                    showMessageDialog(null, "Invalid phone number");
                }
            } else {
                changeCenterInfoFrame.dispose();
                setVisible(true);
            }
        }
    }
}

