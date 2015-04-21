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
import system.Center;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

class Customer extends JFrame{
    private ArrayList<String> list;
    private ArrayList<String> list2;

    
    private JLabel ledetekst1 = new JLabel("Name",JLabel.CENTER);
    private JLabel ledetekst2 = new JLabel("Trade",JLabel.CENTER);
    private JLabel ledetekst3 = new JLabel("Manager name",JLabel.CENTER);
    private JLabel ledetekst4 = new JLabel("..",JLabel.CENTER);
    private JLabel ledetekst5 = new JLabel("..",JLabel.CENTER);
    private JLabel ledetekst6 = new JLabel("Description:",JLabel.CENTER);
    private JLabel ledetekstSvar1 = new JLabel("",JLabel.CENTER);
    private JLabel ledetekstSvar2 = new JLabel("",JLabel.CENTER);
    private JLabel ledetekstSvar3 = new JLabel("",JLabel.CENTER);
    private JLabel ledetekstSvar4 = new JLabel("",JLabel.CENTER);
    private JLabel ledetekstSvar5 = new JLabel("",JLabel.CENTER);
    private JLabel filler = new JLabel("",JLabel.CENTER);

    private JTextArea textArea = new JTextArea(5,5);
    
    private JLabel blank = new JLabel("",JLabel.CENTER);
    
    private JButton search = new JButton("Search for center");
    private JTextField center = new JTextField(20);    
    private JButton search2 = new JButton("Search for Muncipality");
    private JTextField kommune = new JTextField(20);

    
    private JButton knapp1 = new JButton("Info, Senter");
    private JButton knapp2 = new JButton("info, butikk");
    private JButton knapp4 = new JButton("Back");
    private JButton knapp3 = new JButton("Customer Service");
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
        LayoutManager layout2 = new GridLayout(10, 2, 3, 3);
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
        
        //panel2.add(knapp1);
        //panel2.add(knapp2);
        panel2.add(knapp3);
        panel2.add(knapp4);
        panel2.add(search);//search
        panel2.add(center);//search knapp
        panel2.add(search2);//search
        panel2.add(kommune);//search knapp
        
        
        panel2.add(filler);
       // panel2.add(knapp5);
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
        //panel2.add(ledetekstSvar6);
        textArea.setLineWrap(true);
        panel3.add(textArea);

        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.CENTER);
        masterPanel.add(panel3, BorderLayout.SOUTH);
        add(masterPanel);
        pack();
        
        Knappelytter1 lytteren = new Knappelytter1();
        search.addActionListener(lytteren);
        Knappelytter2 lytteren2 = new Knappelytter2();
        search2.addActionListener(lytteren2);
        Knappelytter3 lytteren3 = new Knappelytter3();
        knapp3.addActionListener(lytteren3);
        Knappelytter4 lytteren4 = new Knappelytter4();
        knapp4.addActionListener(lytteren4);
        //Knappelytter5 lytteren5 = new Knappelytter5();
        //knapp5.addActionListener(lytteren5);
        ListboxListener lytteren7 = new ListboxListener();
        listbox.addMouseListener(lytteren7);
        ListboxListener2 lytteren8 = new ListboxListener2();
        listbox2.addMouseListener(lytteren8);

        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

    }
    //Info, senter
    class Knappelytter1 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton button = (JButton) hendelse.getSource();
            String valg = button.getText();
            String centerName = center.getText();

            if(hendelse.getSource() == search){
                try{
                    openConnection();
                    list = getCenters(centerName);
                    defaultListModel.clear();
                    System.out.println(centerName);
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
    //Info, shop
    class Knappelytter2 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton search2= (JButton) hendelse.getSource();
            
            String kommun = kommune.getText();
            
            if(hendelse.getSource() == search2){
                try{
                    openConnection();
                    list = getMuncipality(kommun);
                    defaultListModel.clear();
                    System.out.println(kommun);
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
    class ListboxListener extends DatabaseConnection implements MouseListener {
       public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilket center du trykket på, og henter inn stores registrert i det senteret
        public void mouseClicked (MouseEvent hendelse) {
            try{
                openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                System.out.println(selectedCenter);
                list2 = getStore(selectedCenter);
                defaultListModel2.clear();

                for(int i = 0; i < list2.size(); i++){
                    defaultListModel2.addElement(list2.get(i));
                }

                ledetekst4.setText("Number of shops");
                ledetekstSvar4.setText(getNoOfShops(selectedCenter));
                ledetekst5.setText("Store area");
                ledetekstSvar5.setText(getSQM(selectedCenter) + " sqm");
                ledetekstSvar1.setText(selectedCenter);
                System.out.println(selectedCenter);

                ledetekstSvar3.setText(getCenterManager(selectedCenter));

                ledetekst2.setText("Addresse");
                ledetekstSvar2.setText(getAddress(selectedCenter));
                String parking_check = getCenterParking(selectedCenter);
                String parking="";
                if(parking_check.equalsIgnoreCase("y")){ parking = "\nThe center has its own parking facility.";}
                if(parking_check.equalsIgnoreCase("n")){ parking = "\nThe center does not have its own parking facility.";}
                ledetekst6.setText("Contact Information");
                textArea.setText("Email: " + getCenterMail(selectedCenter) + ", Telephone: " + getCenterTelephone(selectedCenter)+ ". "
                        + parking+"\nInformation about the center: \n"+ getCenterDescription(selectedCenter));
                closeConnection();
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


    class ListboxListener2 extends DatabaseConnection implements MouseListener {

        public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilken store du trykket på
        public void mouseClicked (MouseEvent hendelse) {
            try {
                openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                String selectedStore = listbox2.getSelectedValue().toString();
                ledetekst4.setText("Location");
                ledetekst5.setText("Opening hours");
                ledetekstSvar1.setText(selectedStore);
                System.out.println(selectedStore);
                String Uname = getStoreManager(selectedCenter,selectedStore);
                ledetekstSvar3.setText(Uname);
                ledetekstSvar2.setText(getTradeStore(selectedStore));
                ledetekstSvar4.setText(getLocation(selectedStore));
                ledetekstSvar5.setText(getOpenings(selectedCenter, selectedStore));
                ledetekst6.setText("Information about the store");
                textArea.setText(getShopDescription(selectedCenter, selectedStore));
                closeConnection();

            }catch (Exception c){
                Database.printMesssage(c, "getStoreinCenterInfo");
            }

        }

        //må være med
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
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