import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
import java.util.ArrayList;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField.*;
*/

class HovedVindu extends JFrame{
    private JLabel ledetekst = new JLabel("MENY",JLabel.CENTER);
    private JButton knapp1 = new JButton("Customer");
    private JButton knapp2 = new JButton("Login");
    private JButton knapp3 = new JButton("Close");
    
    
    public HovedVindu() {
        setTitle("SCHMIDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);

        setLayout(new GridLayout(4, 1,10,10));
        getContentPane().setBackground( Color.white);
        
        add(ledetekst);
        add(knapp1);
        add(knapp2);
        add(knapp3);

        Knappelytter1 lytteren = new Knappelytter1();
        knapp1.addActionListener(lytteren);  // knytter lytteren til knappen

        Knappelytter2 lytteren2 = new Knappelytter2();
        knapp2.addActionListener(lytteren2);
        
        Knappelytter3 lytteren3 = new Knappelytter3();
        knapp3.addActionListener(lytteren3);
    }

    

        class Knappelytter1 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp1 = (JButton) hendelse.getSource();
                //Costumer
                Costumer CostumerVindu = new Costumer();
                CostumerVindu.setLocationRelativeTo(null);
                CostumerVindu.setVisible(true);
            }
        }
        
        class Knappelytter2 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp2 = (JButton) hendelse.getSource();
                
                Login LogInVindu = new Login();
                LogInVindu.setLocationRelativeTo(null);
                LogInVindu.setVisible(true);
                
            }
        }
        
        class Knappelytter3 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp3 = (JButton) hendelse.getSource();
                System.out.println("Close");
                System.exit(0);
                
            }
        }
}

class Hoved {
  public static void main(String[] args) {
    HovedVindu Vindumain = new HovedVindu();
    Vindumain.setLocationRelativeTo(null);
    Vindumain.setVisible(true);
      // Jonas er kongen
  }
}