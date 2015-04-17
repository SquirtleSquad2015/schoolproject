package shoolprodject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
import java.util.ArrayList;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField.*;
*/

class Menu extends JFrame{
    private JLabel ledetekst = new JLabel("Costumer menu:",JLabel.CENTER);
    private JButton knapp1 = new JButton("Info, Senter");
    private JButton knapp2 = new JButton("info, butikk");
    private JButton knapp3 = new JButton("Close");
    
    
    public Menu() {
        setTitle("Costumer");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//DISPOSE_ON_CLOSE
        setSize(600,600);

        setLayout(new GridLayout(1, 4,10,10));
        
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

            }
        }
        
        class Knappelytter2 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp2 = (JButton) hendelse.getSource();

                
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