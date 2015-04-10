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
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;
/*
import java.util.ArrayList;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField.*;
*/

class Costumer extends JFrame{

    private JLabel ledetekst1 = new JLabel("Navn:",JLabel.CENTER);
    private JLabel ledetekstSvar1 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst2 = new JLabel("Kategori:",JLabel.CENTER);
    private JLabel ledetekstSvar2 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst3 = new JLabel("Manager:",JLabel.CENTER);
    private JLabel ledetekstSvar3 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst4 = new JLabel("Beskrivelse:",JLabel.CENTER);
    private JLabel blank = new JLabel("",JLabel.CENTER);
    private JTextArea textArea = new JTextArea(5,5);
    private JButton knapp1 = new JButton("Info, Senter");
    private JButton knapp2 = new JButton("info, butikk");
    private JButton knapp4 = new JButton("Close");
    private JButton knapp3 = new JButton("Costumer Service");
    private JButton knapp5 = new JButton("Update");
    private JScrollPane scroll = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();
    DefaultListModel defaultListModel = new DefaultListModel();
    DefaultListModel defaultListModel2 = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JList listbox2 = new JList(defaultListModel2);
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel masterPanel = new JPanel();
    
    
    public Costumer() {
        setTitle("Costumer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);

		// Create a new listbox control
        LayoutManager layout1 = new GridLayout(1, 2, 3, 3);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(7, 2, 3, 3);
        panel2.setLayout(layout2);
        LayoutManager layout3 = new GridLayout(1, 1, 3, 3);
        panel3.setLayout(layout3);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);
        
        
        
        
        
	panel1.add( listbox, BorderLayout.CENTER );
        panel1.add( listbox2, BorderLayout.CENTER );
        scroll =new JScrollPane(listbox);
        scroll2 =new JScrollPane(listbox2);
        panel1.add(scroll, BorderLayout.CENTER);
        panel1.add(scroll2, BorderLayout.CENTER);
        
        
                
        panel2.add(knapp1);
        panel2.add(knapp2);
        panel2.add(knapp3);
        panel2.add(knapp4);
        panel2.add(knapp5);
        panel2.add(blank);
        panel2.add(ledetekst1);
        panel2.add(ledetekstSvar1);
        panel2.add(ledetekst2);
        panel2.add(ledetekstSvar2);
        panel2.add(ledetekst3);
        panel2.add(ledetekstSvar3);
        textArea.setLineWrap(true);
        panel2.add(ledetekst4);
        panel3.add(textArea);
        
        
        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.CENTER);
        masterPanel.add(panel3, BorderLayout.SOUTH);
        add(masterPanel);
        pack();
        
        
        Knappelytter1 lytteren = new Knappelytter1();
        knapp1.addActionListener(lytteren);  // knytter lytteren til knappen

        Knappelytter2 lytteren2 = new Knappelytter2();
        knapp2.addActionListener(lytteren2);
        
        Knappelytter3 lytteren3 = new Knappelytter3();
        knapp3.addActionListener(lytteren3);
        
        Knappelytter4 lytteren4 = new Knappelytter4();
        knapp4.addActionListener(lytteren4);
        
        Knappelytter5 lytteren5 = new Knappelytter5();
        knapp5.addActionListener(lytteren5);

        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

    }


        class Knappelytter1 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp1 = (JButton) hendelse.getSource();
                int index = listbox.getSelectedIndex();
                
                System.out.println(index);
                
                if(index==-1){
                    System.out.println("Chose Shoping Center");
                    showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
                }
                if(index>0){
                    
                }

            }
        }
        
        class Knappelytter2 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp2 = (JButton) hendelse.getSource();
                
                int index = listbox.getSelectedIndex();
                int index2 = listbox2.getSelectedIndex();
                
                System.out.println(index+" og "+ index2);
                
                if(index==-1){
                    showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
                }
                if(index2==-1){
                    showMessageDialog (null, "Chose Shop", "Fail", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(index>=0  && index2>=0){
                    
                }

                
            }
        }
        
        class Knappelytter3 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp3 = (JButton) hendelse.getSource();
                System.out.println("Costumer Service");
                
                
            }
        }
        class Knappelytter4 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp4 = (JButton) hendelse.getSource();
                System.out.println("Close");
                System.exit(0);
                
            }
        }
        class Knappelytter5 extends DatabaseConnection implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
               JButton knapp5 = (JButton) hendelse.getSource();
                int index = listbox.getSelectedIndex();
                try{
                    openConnection();
                    ArrayList<String> list = getCenters("");
                    defaultListModel.clear();

                    for(int i = 0; i < list.size(); i++){
                        defaultListModel.addElement(list.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                try{
                    openConnection();
                    ArrayList<String> list = getStore("");
                    defaultListModel2.clear();

                    for(int i = 0; i < list.size(); i++){
                        defaultListModel2.addElement(list.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
            }
        }

    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            // JButton knapp5 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            try{
                openConnection();
                ArrayList<String> list = getCenters("");
                defaultListModel.clear();

                for(int i = 0; i < list.size(); i++){
                    defaultListModel.addElement(list.get(i));
                }
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
            try{
                openConnection();
                ArrayList<String> list = getStore("");
                defaultListModel2.clear();

                for(int i = 0; i < list.size(); i++){
                    defaultListModel2.addElement(list.get(i));
                }
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
        }
    }
        
        
}