package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;


class Login extends JFrame{
    private JTextField felt = new JTextField(15);
    private JPasswordField passwordField;
    private JLabel ledetekst = new JLabel("User Name:",JLabel.CENTER);
    private JLabel ledetekst2= new JLabel("Password:",JLabel.CENTER);
    private JButton knapp1 = new JButton("Login");
    private JButton knapp2 = new JButton("Sign Up");
    private JButton knapp3 = new JButton("Close");
    
    
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 2, 5, 5));
        passwordField = new JPasswordField(10);
        
        add(ledetekst);
        add(felt);	
        add(ledetekst2);
        add(passwordField);
        add(knapp1);
        add(knapp2);
        add(knapp3);

        Action action = new Action();
        knapp1.addActionListener(action);  // knytter lytteren til knappen
        JRootPane rootPane = SwingUtilities.getRootPane(knapp1); 
        rootPane.setDefaultButton(knapp1);
        knapp2.addActionListener(action);
        knapp3.addActionListener(action);
        
        pack();
    }
   
    class Action extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == knapp1){
                int ok = 0;
                String activ = "";
                char [] pas = passwordField.getPassword();
                String password="";
                for(int i=0;i<pas.length;i++){
                    password+= Character.toString(pas[i]);
                }
                String bruker = felt.getText();

                try {
                    openConnection();
                    ok = checkLogIn(bruker, password);
                    if(ok > 0){
                        activ = getUserActiv(bruker);
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "login");
                }
                boolean loggin=true;
                if((!loggin || bruker.equals("")|| password.equals("")) || ok == 0){ //login fail
                    showMessageDialog (null, "Incorrect Password or Username", "Login fail", JOptionPane.ERROR_MESSAGE); 
                }
                if(ok > 0 && activ.equals("y")){
                    if(ok == 1){
                        CustomerServiceMenu customerServiceMenu = new CustomerServiceMenu(bruker);
                        customerServiceMenu.setVisible(true);
                        customerServiceMenu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if(ok == 2){
                        ShopManagerMenu shopManagerMenu = new ShopManagerMenu(bruker);
                        shopManagerMenu.setVisible(true);
                        shopManagerMenu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if(ok==3){
                        CenterManagerMenu centerManagerMenu = new CenterManagerMenu(bruker);
                        centerManagerMenu.setVisible(true);
                        centerManagerMenu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if(ok==4){
                        AdminMenu menu = new AdminMenu(bruker);
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                } else if(ok > 0 && activ.equals("n")){
                    showMessageDialog(null, "Your user is not activ. Please contact Admin or center manager");
                }
            }
            else if(actionEvent.getSource() == knapp2){
                SignUp signup = new SignUp();
                signup.setVisible(true);
                signup.setLocationRelativeTo(null);

            } else {
               System.exit(0);
            }
        }
    }
}
