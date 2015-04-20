package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class SignUp2 extends JFrame{

    private String username;
    private String telephone;
    private String userType;
    private char[] password;
    JLabel userName = new JLabel("Username: ", JLabel.CENTER);
    JLabel telephone1 = new JLabel("Telephone: ", JLabel.CENTER);
    JLabel title = new JLabel("Title: ", JLabel.CENTER);
    JLabel realName = new JLabel("Enter name: ", JLabel.CENTER);
    JLabel email = new JLabel("Enter email: ", JLabel.CENTER);
    JLabel chooseCenter = new JLabel("Select your center:", JLabel.CENTER);


    JButton next = new JButton("Sign up");
    JButton back = new JButton("Back");
    JButton search = new JButton("Search for center");
    JTextField enterRealName = new JTextField(20);
    JTextField enterEmail = new JTextField(20);
    JTextField center = new JTextField(20);
    DefaultListModel defaultListModel = new DefaultListModel();
    JList list = new JList(defaultListModel);
    JScrollPane scrollPane = new JScrollPane(list);

    JPanel masterPanel1 = new JPanel();


    public SignUp2(String username, String telephone, char[] password, String userType){
        super("Sign Up");

        JLabel userName1 = new JLabel(username, JLabel.CENTER);
        JLabel telephone2 = new JLabel(telephone, JLabel.CENTER);
        JLabel type = new JLabel(userType, JLabel.CENTER);

        LayoutManager masterLayout = new BorderLayout();
        masterPanel1.setLayout(masterLayout);

        LayoutManager layout = new GridLayout(6, 2, 3, 3);
        JPanel panel3 = new JPanel();
        panel3.setLayout(layout);

        LayoutManager layout4 = new GridLayout(1, 2, 3, 3);
        JPanel panel4 = new JPanel();
        panel4.setLayout(layout4);

        LayoutManager layout5 = new GridLayout(1, 2, 3, 3);
        JPanel panel5 = new JPanel();
        panel5.setLayout(layout5);

        this.username = username;
        this.telephone = telephone;
        this.password = password;
        this.userType = userType;
        panel3.add(userName);
        panel3.add(userName1);
        panel3.add(telephone1);
        panel3.add(telephone2);
        panel3.add(title);
        panel3.add(type);
        panel3.add(realName);
        panel3.add(enterRealName);
        panel3.add(email);
        panel3.add(enterEmail);
        panel3.add(search);
        panel3.add(center);
        panel4.add(chooseCenter);
        panel4.add(scrollPane);
        panel5.add(back);
        panel5.add(next);
        masterPanel1.add(panel3, BorderLayout.NORTH);
        masterPanel1.add(panel4, BorderLayout.CENTER);
        masterPanel1.add(panel5, BorderLayout.SOUTH);
        add(masterPanel1);
        pack();
        KnappeLytter lytter = new KnappeLytter();
        next.addActionListener(lytter);
        back.addActionListener(lytter);
        search.addActionListener(lytter);
    }
    private class KnappeLytter extends DatabaseConnection implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            String valg = button.getText();
            String name12 = enterRealName.getText();
            String email = enterEmail.getText();
            String centerName = center.getText();
            int choice = list.getSelectedIndex();
            int userLevel = 0;

            if(actionEvent.getSource() == search){
                try{
                    openConnection();
                    ArrayList<String> list = getCenters(centerName);
                    defaultListModel.clear();
                    System.out.println(choice);
                    for(int i = 0; i < list.size(); i++){
                        defaultListModel.addElement(list.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
            }
            if(actionEvent.getSource() == back){
                dispose();
            } else if(actionEvent.getSource() == next) {
                if(!name12.equals("") && !email.equals("")){
                    if(choice != -1){
                        String centerName1 = list.getSelectedValue().toString();

                        if(userType.equals("CenterManager")){
                            userLevel = 3;
                        }
                        else if(userType.equals("CustomerService")) {
                            userLevel = 1;
                        }
                        else {
                            userLevel = 2;//storemanager(?)
                        }
                        int ok = 0;
                        try {
                            openConnection();
                            ok = regNewCenterUser(username, telephone, password, centerName1, name12, email, userLevel, userType);
                            closeConnection();
                        }
                        catch (Exception e){
                            Database.printMesssage(e, "regNewCenterManager");
                        }
                        if(ok == 1){
                            showMessageDialog(null, "Registration complete");
                            dispose();



                        } else if(ok == 2){
                            String newUsername = showInputDialog("Username already registered, please enter new one");
                            username = newUsername;
                        } else {
                            String newTelephone = showInputDialog("Telephone number already registered, try again: ");
                            telephone = newTelephone;
                            /*while(ok == false){
                                try {
                                    int testTelephone = Integer.parseInt(newTelephone)
                                    ok = true;
                                }
                                catch (NumberFormatException){
                                    showMessageDialog(null, "Wrong number format");
                                }
                                if(newTelephone.length() == 8 ) }

                            }*/
                        }
                    } else {
                        showMessageDialog(null, "Please select center");
                    }
                } else {
                    showMessageDialog(null, "Please enter real name and email");
                }
            }

        }
    }
}
