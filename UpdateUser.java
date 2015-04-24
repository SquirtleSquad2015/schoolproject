package shoolprodject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.swing.border.*;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

public class UpdateUser extends JFrame{
    private String username;
    private String activ;
    private String email;
    private String number;
   

    JLabel Lusername = new JLabel("",JLabel.CENTER);
    JLabel Laccess = new JLabel("", JLabel.CENTER);
    JLabel Lactiv = new JLabel("", JLabel.CENTER);
    JLabel Lname = new JLabel("", JLabel.CENTER);
    JLabel Lcetnername = new JLabel("", JLabel.CENTER);
    JLabel Ltitle = new JLabel("", JLabel.CENTER);
    JLabel Lmail = new JLabel("", JLabel.CENTER);
    JLabel Ltlf = new JLabel("", JLabel.CENTER);
    JTextArea description = new JTextArea();
    JLabel changeUsername = new JLabel("",JLabel.CENTER);
    JLabel changeAccess = new JLabel("",JLabel.CENTER);
    JButton changeActiv = new JButton("Change");
    JLabel changeName = new JLabel("");
    JButton changeCentername = new JButton("Change");
    JLabel changeTitle = new JLabel("");
    JButton changeMail = new JButton("Change");
    JButton changeTlf = new JButton("Change");
    JButton delete = new JButton("Delete");
    JButton backButton = new JButton("Back");

    
    
    public UpdateUser(String username){
        this.username=username;
        
        setTitle("User View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,400);
        
        JPanel storeTopPanel = new JPanel();
        JPanel storeBottomPanel = new JPanel();
        
        LayoutManager storeTopLayout = new GridLayout(9,2,3,3);
        LayoutManager storeBottomLayout = new GridLayout(1,2,3,3);
        LayoutManager changeStoreInfoLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        setLayout(changeStoreInfoLayout);
        storeTopPanel.setLayout(storeTopLayout);
        storeBottomPanel.setLayout(storeBottomLayout);
        storeTopPanel.add(Lusername);
        storeTopPanel.add(changeUsername);
        storeTopPanel.add(Laccess);
        storeTopPanel.add(changeAccess);
        storeTopPanel.add(Lactiv);
        storeTopPanel.add(changeActiv);
        storeTopPanel.add(Lname);
        storeTopPanel.add(changeName);
        storeTopPanel.add(Lcetnername);
        storeTopPanel.add(changeCentername);
        storeTopPanel.add(Ltitle);
        storeTopPanel.add(changeTitle);
        storeTopPanel.add(Ltlf);
        storeTopPanel.add(changeTlf);
        storeTopPanel.add(Lmail);
        storeTopPanel.add(changeMail);
        
        
        storeBottomPanel.add(backButton);
        storeBottomPanel.add(delete);
        add(storeTopPanel, BorderLayout.NORTH);
        add(storeBottomPanel, BorderLayout.SOUTH);
        ActionUpdateStoreInfo actionChangeCenter = new ActionUpdateStoreInfo();
        changeActiv.addActionListener(actionChangeCenter);
        changeMail.addActionListener(actionChangeCenter);
        changeCentername.addActionListener(actionChangeCenter);
        changeTlf.addActionListener(actionChangeCenter);
        delete.addActionListener(actionChangeCenter);
        backButton.addActionListener(actionChangeCenter);
       

        
        
        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);
    }
    private class ActionUpdateStoreInfo extends DatabaseConnection implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == changeActiv){
                int ok=0;
                try {
                    openConnection();
                    activ = getUserActiv(username);                    
                    closeConnection();
                    System.out.println(activ);
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeActiv");
                }
                if(activ.equals("n")){
                    String ny="y";
                    try {
                        openConnection();
                        ok=setUserActiv(ny,username);                   
                        closeConnection();
                        showMessageDialog(null, "Update complete. User is: "+ny);
                        Lactiv.setText("Activ: "+ny);
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeActiv");
                    }
                }
                else if(activ.equals("y")){
                    String ny="n";
                    try {
                        openConnection();
                        ok=setUserActiv(ny,username);                   
                        closeConnection();
                        showMessageDialog(null, "Update complete. User is: "+ny);
                        Lactiv.setText("Activ: "+ny);
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeActiv");
                    }
                }
            }
            else if(actionEvent.getSource() == changeCentername){//jonas
                try {
                    openConnection();
                    String title=getUserTitle(username);
                    closeConnection();
                    if(title.equals("Center Manager")){
                        System.out.println("senter opppdatert");
                    }
                    else{
                        showMessageDialog(null, "Ikke gyldig bruker");
                    }
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeActiv");
                }
                
                
            }
            else if(actionEvent.getSource() == changeTlf){//jonas
                boolean tlfCheck=false;
                int ok=0;
                String newTlf = showInputDialog(null, "Please insert new tlf: ");
                try {
                    int tlf=Integer.parseInt(newTlf);
                    tlfCheck=true;
                }
                catch (NumberFormatException e){
                    showMessageDialog(null, "Incorrect, try again");
                }
                if(tlfCheck){
                    try {
                        openConnection();
                        ok=setEmail(newTlf,username);                   
                        closeConnection();
                        showMessageDialog(null, "Update complete. User mail is: "+newTlf);
                        Ltlf.setText("lf: "+newTlf);
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeMail");
                    }
                }
            }
            else if(actionEvent.getSource() == changeMail){
                int ok=0;
                String newMail = showInputDialog(null, "Please insert new mail: ");
                try {
                    openConnection();
                    ok=setEmail(newMail,username);                   
                    closeConnection();
                    showMessageDialog(null, "Update complete. User mail is: "+newMail);
                    Lmail.setText("Mail: "+newMail);
                }
                catch (Exception e){
                    Database.printMesssage(e, "ChangeMail");
                }
            }
            else if(actionEvent.getSource() == delete){
                int dialogButton = JOptionPane.YES_NO_OPTION;                
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Delete "+username+"?","Warning",dialogButton);
                if(dialogResult == JOptionPane.NO_OPTION){
                    showMessageDialog(null, username +" is NOT deleted");
                    dispose();
                }
                else if(dialogButton == JOptionPane.YES_OPTION){
                    try {
                        openConnection();
                        deletePerson(username);                
                        deleteUser(username);
                        closeConnection();
                        showMessageDialog(null, username +" is deleted");
                        dispose();
                    }
                    catch (Exception e){
                        Database.printMesssage(e, "ChangeMail");
                    }
                }  
            }
            else if(actionEvent.getSource() == backButton){
                dispose();
            }
        }
    }
    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            
            try{
                openConnection();

                activ=getUserActiv(username);
                number=getPhoneNumber(username);
                email=getEmail(username);
                        
                Lusername.setText("Username: "+username);
                Laccess.setText("Access Level: "+getUserAccess(username));
                Lactiv.setText("Activ: "+activ);
                Lname.setText("Name: "+getPersonName(username));
                Lcetnername.setText("Center Name: "+getCenter(username));
                Ltitle.setText("Title: "+getUserTitle(username));
                Ltlf.setText("Tlf: "+number);
                Lmail.setText("Mail: "+email);
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
            
        }
    }  
}
