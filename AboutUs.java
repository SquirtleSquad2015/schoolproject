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
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.*;
/*
import java.util.ArrayList;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField.*;
*/

class AboutUs extends JFrame{
    private JLabel ledetekst = new JLabel("MENY",JLabel.CENTER);
    private JButton bildeKnapp = new JButton("");
    private JButton knapp1 = new JButton("Customer");
    private JButton knapp2 = new JButton("Login");
    private JButton knapp3 = new JButton("Close");
    private JButton knapp4 = new JButton("testinloggin meny");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel masterPanel = new JPanel();
    
    public AboutUs() throws IOException {
        setTitle("SCHMIDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(300,415);
        
        LayoutManager layout1 = new GridLayout(1, 1, 0, 0);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(4, 1, 0, 0);
        panel2.setLayout(layout2);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);


        panel1.add(bildeKnapp);
        
        Image img = ImageIO.read(getClass().getResource("bilde/bilde.png"));
        bildeKnapp.setIcon(new ImageIcon(img));
        bildeKnapp.setSize(300, 300);
        
          
        panel2.add(knapp1);
        panel2.add(knapp2);
        panel2.add(knapp3);
        panel2.add(knapp4);
        
        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);

    }
  
}