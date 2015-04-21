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

        Knappelytter1 lytteren = new Knappelytter1();
        knapp1.addActionListener(lytteren);  // knytter lytteren til knappen
        JRootPane rootPane = SwingUtilities.getRootPane(knapp1); 
        rootPane.setDefaultButton(knapp1);

        Knappelytter2 lytteren2 = new Knappelytter2();
        knapp2.addActionListener(lytteren2);
        
        Knappelytter3 lytteren3 = new Knappelytter3();
        knapp3.addActionListener(lytteren3);
        
        pack();
    }
    
        class Knappelytter1 extends DatabaseConnection implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                int ok = 0;
                JButton knapp1 = (JButton) hendelse.getSource();
                char [] pas = passwordField.getPassword();
                String password="";
                for(int i=0;i<pas.length;i++){
                    password+= Character.toString(pas[i]);
                }
                String bruker = felt.getText();

                try {
                    openConnection();
                    ok = checkLogIn(bruker, password);
                    System.out.println(ok);
                    closeConnection();
                }
                catch (Exception e){
                    System.out.println("Username fail");
                }
                boolean loggin=true;
                if((!loggin || bruker.equals("")||bruker.equals(null)|| password.equals("")) || ok == 0){ //login fail
                    showMessageDialog (null, "Incorrect Password or Username", "Login fail", JOptionPane.ERROR_MESSAGE); 
                }
                if(ok > 0){//login
                    /*Menu  MenuVindu = new Menu();
                    MenuVindu.setVisible(true);
                    System.out.println(ok);*/
                    if(ok == 1){
                        CustomerServiceMenu customerServiceMenu = new CustomerServiceMenu(bruker);
                        customerServiceMenu.setVisible(true);
                        customerServiceMenu.setLocationRelativeTo(null);
                        dispose();
                        //System.out.println("Access level 1");
                    }
                    if(ok == 2){
                        ShopManagerMenu shopManagerMenu = new ShopManagerMenu(bruker);
                        shopManagerMenu.setVisible(true);
                        shopManagerMenu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if(ok==3){
                        System.out.println("Access level 3");
                    }
                    if(ok==4){
                        System.out.println("Admin level");
                    }

                    
                }
            }
        }
        
        class Knappelytter2 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp2 = (JButton) hendelse.getSource();
                
                SignUp Vindu2 = new SignUp();
                Vindu2.setVisible(true);
                Vindu2.setLocationRelativeTo(null);
                
                //visible (false)?
            }
        }
        
        class Knappelytter3 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp3 = (JButton) hendelse.getSource();
                System.out.println("Close");
                //Vindu.super.setVisible(false);
                System.exit(0);
                
            }
        }
}
