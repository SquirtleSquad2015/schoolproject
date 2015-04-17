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

class Customer extends JFrame{
    private ArrayList<String> list;
    private ArrayList<String> list2;
    private JLabel ledetekst1 = new JLabel("Navn:",JLabel.CENTER);
    private JLabel ledetekst2 = new JLabel("Kategori:",JLabel.CENTER);
    private JLabel ledetekst3 = new JLabel("Manager: navn",JLabel.CENTER);
    private JLabel ledetekst4 = new JLabel("..",JLabel.CENTER);
    private JLabel ledetekst5 = new JLabel("..",JLabel.CENTER);
    private JLabel ledetekst6 = new JLabel("Beskrivelse:",JLabel.CENTER);
    private JLabel ledetekstSvar1 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekstSvar2 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekstSvar3 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekstSvar4 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekstSvar5 = new JLabel("blabal info",JLabel.CENTER);
    private JTextArea textArea = new JTextArea(5,5);
    
    private JLabel blank = new JLabel("",JLabel.CENTER);
    
    private JButton knapp1 = new JButton("Info, Senter");
    private JButton knapp2 = new JButton("info, butikk");
    private JButton knapp4 = new JButton("Close");
    private JButton knapp3 = new JButton("Costumer Service");
    private JButton knapp5 = new JButton("Update");
    
    private JScrollPane scroll = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();
    private DefaultListModel defaultListModel = new DefaultListModel();
    private DefaultListModel defaultListModel2 = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JList listbox2 = new JList(defaultListModel2);
    
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel masterPanel = new JPanel();
    
    public Customer() {
        setTitle("Costumer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        
        LayoutManager layout1 = new GridLayout(1, 2, 3, 3);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(9, 2, 3, 3);
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
        panel2.add(ledetekst4);
        panel2.add(ledetekstSvar4);
        panel2.add(ledetekst5);
        panel2.add(ledetekstSvar5);
        panel2.add(ledetekst6);
        textArea.setLineWrap(true);
        panel3.add(textArea);

        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.CENTER);
        masterPanel.add(panel3, BorderLayout.SOUTH);
        add(masterPanel);
        pack();
        
        Knappelytter1 lytteren = new Knappelytter1();
        knapp1.addActionListener(lytteren);
        Knappelytter2 lytteren2 = new Knappelytter2();
        knapp2.addActionListener(lytteren2);
        Knappelytter3 lytteren3 = new Knappelytter3();
        knapp3.addActionListener(lytteren3);
        Knappelytter4 lytteren4 = new Knappelytter4();
        knapp4.addActionListener(lytteren4);
        Knappelytter5 lytteren5 = new Knappelytter5();
        knapp5.addActionListener(lytteren5);
        ListboxListener lytteren7 = new ListboxListener();
        listbox.addMouseListener(lytteren7);

        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

    }
    //Info, senter
    class Knappelytter1 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp1 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            ledetekst4.setText("Parking:");
            ledetekst5.setText("Adress:");
            
            if(index>=0){
            //print all info
                //navn
                String centerName=list.get(index);
                ledetekstSvar1.setText(centerName);
                System.out.println(centerName);
                //parkering
                try{
                    openConnection();
                    ledetekstSvar4.setText(getParking(centerName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //kategori
                try{
                    openConnection();
                    ledetekstSvar2.setText(getTradeCenter(centerName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //Manager navn
                try{
                    openConnection();
                    ledetekstSvar3.setText(getCenterManager(centerName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //plassering (adresse + komune)
                try{
                    openConnection();
                    ledetekstSvar5.setText(getAddress(centerName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //beskrivelse
                
            }
            if(index==-1){
                System.out.println("Chose Shoping Center");
                showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Info, shop
    class Knappelytter2 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp2 = (JButton) hendelse.getSource();
            int index = listbox2.getSelectedIndex();
            ledetekst4.setText("Location:");
            ledetekst5.setText("Openings:");
            
            if(index>=0){
            //print all info
                //navn
                String StoreName=list2.get(index);
                ledetekstSvar1.setText(StoreName);
                System.out.println(StoreName);
                //manager navn
                try{
                    openConnection();
                    ledetekstSvar3.setText(getStoreManager(StoreName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //kategori
                try{
                    openConnection();
                    ledetekstSvar2.setText(getTradeStore(StoreName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //lokasjon (location + floor)
                try{
                    openConnection();
                    ledetekstSvar4.setText(getLocation(StoreName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //openings
                try{
                    openConnection();
                    ledetekstSvar5.setText(getOpenings(StoreName));
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
                //beskrivelse
                
            }
            if(index==-1){
                System.out.println("Chose Shoping Center");
                showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Costumer service
    class Knappelytter3 extends JFrame implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp3 = (JButton) hendelse.getSource();
            System.out.println("Costumer Service");  
        }
    }
    //Close
    class Knappelytter4 extends JFrame implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp4 = (JButton) hendelse.getSource();
            System.out.println("Close");
            System.exit(0);

        }
    }
    //Update
    class Knappelytter5 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
           JButton knapp5 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();

            if(index>=0){
                try{
                    String centerName=list.get(index);
                    openConnection();
                    list2 = getStore(centerName);
                    defaultListModel2.clear();
                    for(int i = 0; i < list2.size(); i++){
                        defaultListModel2.addElement(list2.get(i));
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "getCenters");
                }
            }
            else{
                showMessageDialog (null, "Chose Center then press \"Update\" to update shops", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //oppdatering av store  ved trykk i listbox
    class ListboxListener extends JFrame implements MouseListener {
        private DatabaseConnection DBconnection = new DatabaseConnection(); //må opprette sin egen, max 1 extends per klasse
        public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilket center du trykket på, og henter inn stores registrert i det senteret
        public void mouseClicked (MouseEvent hendelse) {
            try{
                DBconnection.openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                System.out.println(selectedCenter);
                list2 = DBconnection.getStore(selectedCenter);
                defaultListModel2.clear();

                for(int i = 0; i < list2.size(); i++){
                    defaultListModel2.addElement(list2.get(i));
                }
                DBconnection.closeConnection();
            }
            catch (Exception c){
                Database.printMesssage(c, "getStoresInCenter");
            }
        }

        //må implementeres
        @Override
        public void mousePressed(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseEntered(MouseEvent e){}
        @Override
        public void mouseExited(MouseEvent e){}
    }
    //automatisk oppdattering
    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {

            int index = listbox.getSelectedIndex();
            try{
                openConnection();
                list = getCenters("");
                defaultListModel.clear();
                for(int i = 0; i < list.size(); i++){
                    defaultListModel.addElement(list.get(i));
                }
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getCenters");
            }
        }
    }    
}