package shoolprodject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

class HovedVindu  extends JFrame {
    private JLabel ledetekst = new JLabel("MENY",JLabel.CENTER);
    private JButton bildeKnapp = new JButton("");
    private JButton knapp1 = new JButton("Customer");
    private JButton knapp2 = new JButton("Login");
    private JButton knapp3 = new JButton("Close");
    private JButton knapp4 = new JButton("testinloggin meny");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel masterPanel = new JPanel();
    private JTextArea omOssTekst = new JTextArea("We are a team of five students studying computer engineering at Sor-Trondelag university college. This spring we have a teamproject to create this program.");
    private JPanel panel11 = new JPanel();
    private JPanel masterPanel2 = new JPanel();
    private JPanel panel22 = new JPanel();
    private JButton aboutUsKnapp = new JButton("Return to menu");
    private JFrame temp = new JFrame();
    

    public HovedVindu()throws IOException {

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
        Image img;

        try{
            img = ImageIO.read(getClass().getResource("./bilde/SCHMIDT.png"));
            bildeKnapp.setIcon(new ImageIcon(img));
            bildeKnapp.setSize(300, 300);
        }
        catch(IOException e){
            System.out.println("Picture missing");
        }
        catch(IllegalArgumentException e){
            img = ImageIO.read(getClass().getResource("/bilde/SCHMIDT.png"));
            System.out.println("IllegalArgumentException");
            bildeKnapp.setIcon(new ImageIcon(img));
            bildeKnapp.setSize(300, 300);
        }



          
        panel2.add(knapp1);
        panel2.add(knapp2);
        panel2.add(knapp3);
        panel2.add(knapp4);
        
        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        Action action = new Action();
        knapp1.addActionListener(action);
        knapp2.addActionListener(action);
        knapp3.addActionListener(action);
        knapp4.addActionListener(action);
        bildeKnapp.addActionListener(action);
        
        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);
    }
    
    
    private class action2 implements ActionListener {
     
        @Override
        public void actionPerformed(ActionEvent hendelse) {
            JButton aboutUsKnapp = (JButton) hendelse.getSource();
            setVisible(true);
            temp.dispose();
                                
        }
    }  
    private class Action extends DatabaseConnection implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent source){
            JButton check = (JButton) source.getSource();
            
            if (check == knapp1){
                //setVisible(false);
                Customer CostumerVindu = new Customer();    
                CostumerVindu.setLocationRelativeTo(null);
                CostumerVindu.setVisible(true);
                //setVisible(false);
            } else if (check == knapp2){
                setVisible(false);
                Login LogInVindu = new Login();
                LogInVindu.setLocationRelativeTo(null);
                LogInVindu.setVisible(true);
            } else if (check == knapp3){
                System.out.println("Close");
                System.exit(0);
            } else if (check == knapp4){
                setVisible(false);
                InloggedMeny inloggedMeny  = new InloggedMeny();
                inloggedMeny.setLocationRelativeTo(null);
                inloggedMeny.setVisible(true);
            } else if (check == bildeKnapp){
                setVisible(false);
                temp.setTitle("SCHMIDT");
                temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                temp.setSize(300, 250);
                temp.setLocationRelativeTo(null);



                LayoutManager layout11 = new GridLayout(1,1,0,0);
                LayoutManager layout22 = new GridLayout(1,3,0,0);
                LayoutManager masterLayout2 = new BorderLayout();
                panel11.setLayout(layout11);
                panel22.setLayout(layout22);
                masterPanel2.setLayout(masterLayout2);
                omOssTekst.setLineWrap(true);
                omOssTekst.setWrapStyleWord(true);
                omOssTekst.setEditable(false);
                omOssTekst.setOpaque(false);
                Font font = new Font("Verdana", Font.BOLD, 15);
                omOssTekst.setFont(font);
                panel11.add(omOssTekst);
                panel22.add(aboutUsKnapp);


                masterPanel2.add(panel11, BorderLayout.NORTH);
                masterPanel2.add(panel22, BorderLayout.CENTER);

                temp.add(masterPanel2);
                setVisible(false);
                temp.setVisible(true);
                action2 action2 = new action2();
                aboutUsKnapp.addActionListener(action2);
            }
        }
    }

   
    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent hendelse) {
            boolean ok;
            try {
                openConnection();
                ok =checkDB();
                closeConnection();
                showMessageDialog (null, "You are connected to the DataBase", "DataBase Connected", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                showMessageDialog (null, "Can not find Database", "DataBase Fail", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
            
        }
    } 
}
