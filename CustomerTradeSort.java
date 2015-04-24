package shoolprodject;

import javax.swing.*;
import java.awt.*;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Andreas on 23.04.2015.
 */
public class CustomerTradeSort extends JFrame{

    private ArrayList<String> list;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private JScrollPane scroll = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();
    private DefaultListModel defaultListModel = new DefaultListModel();
    private DefaultListModel defaultListModel2 = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JList listbox2 = new JList(defaultListModel2);

    private JTextArea textArea = new JTextArea(10,10);

    private JButton knapp3 = new JButton("back");

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel masterPanel = new JPanel();

    private String[] tradeListe = new String[100];
    private JComboBox tradeValg = new JComboBox();

    public CustomerTradeSort() {
        setTitle("Find store by trade");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(100,200);

        LayoutManager layout1 = new GridLayout(1, 2, 3, 3);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(4, 2, 3, 3);
        panel2.setLayout(layout2);
        LayoutManager layout3 = new GridLayout(1, 0, 3, 3);
        panel3.setLayout(layout3);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);

        panel1.add( listbox, BorderLayout.CENTER );
        panel1.add( listbox2, BorderLayout.CENTER );
        scroll = new JScrollPane(listbox);
        scroll2 = new JScrollPane(listbox2);
        panel1.add(scroll, BorderLayout.CENTER);
        panel1.add(scroll2, BorderLayout.CENTER);

        panel2.add(tradeValg);
        panel2.add(knapp3);

        textArea.setLineWrap(true);
        panel3.add(textArea);
        textArea.setEditable(false);

        masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.CENTER);
        masterPanel.add(panel3, BorderLayout.SOUTH);

        add(masterPanel);
        pack();

        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

        Knappelytter11 knappelytter11 = new Knappelytter11();
        knapp3.addActionListener(knappelytter11);

        Knappelytter13 knappelytter13 = new Knappelytter13();
        tradeValg.addActionListener(knappelytter13);

        ListboxListener lytteren7 = new ListboxListener();
        listbox.addMouseListener(lytteren7);
        ListboxListener2 lytteren8 = new ListboxListener2();
        listbox2.addMouseListener(lytteren8);

    }

    private class Knappelytter11 implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp4 = (JButton) hendelse.getSource();
            System.out.println("Back");
            dispose();

        }
    }

    private class Knappelytter13 extends shoolprodject.DatabasePackage.DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent hendelse){
            JComboBox tradeValg = (JComboBox) hendelse.getSource();
            String trade = tradeValg.getSelectedItem().toString();

            try{
                openConnection();
                list = getCenterFromTrade(trade);
                defaultListModel.clear();
                for(int i = 0; i < list.size(); i++){
                    defaultListModel.addElement(list.get(i));
                }
                closeConnection();
            }
            catch (Exception e){
                shoolprodject.DatabasePackage.Database.printMesssage(e, "getCenterFromTrade");
            }
        }
    }

    //automatisk oppdattering
     private class AutomatiskOppdatering extends shoolprodject.DatabasePackage.DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            try{
                openConnection();
                list3 = getTrades();
                for(int i = 0; i < list3.size(); i++){
                    tradeListe[i] = list3.get(i).toString();
                    tradeValg.addItem(tradeListe[i]);

                }
                closeConnection();
            }
            catch (Exception e){
                shoolprodject.DatabasePackage.Database.printMesssage(e, "getCenters");
            }
            tradeValg.setEditable(false);
        }
    }

    private class ListboxListener extends shoolprodject.DatabasePackage.DatabaseConnection implements MouseListener {
        public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilket center du trykket på, og henter inn stores registrert i det senteret
        public void mouseClicked (MouseEvent hendelse) {
            try{
                openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                String selectedTrade = tradeValg.getSelectedItem().toString();
                list2 = getStoreAndTrade(selectedCenter,selectedTrade);
                defaultListModel2.clear();
                for(int i = 0; i < list2.size(); i++){
                    defaultListModel2.addElement(list2.get(i));
                }
                closeConnection();
            }
            catch (Exception c){
                shoolprodject.DatabasePackage.Database.printMesssage(c, "getStoresInCenter");
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


    private class ListboxListener2 extends shoolprodject.DatabasePackage.DatabaseConnection implements MouseListener {
        String svartekst="";
        public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilken store du trykket på
        public void mouseClicked (MouseEvent hendelse) {
            try {
                openConnection();
                String selectedCenter = listbox.getSelectedValue().toString();
                String selectedStore = listbox2.getSelectedValue().toString();
                String a = getTradeDescription(tradeValg.getSelectedItem().toString());
                String b = getCenterMunicipality(selectedCenter);
                String c = getLocation(selectedStore);
                String d = getOpenings(selectedCenter, selectedStore);
                String e = getShopDescription(selectedCenter, selectedStore);
                String f = getAddress(selectedCenter);
                String g = getCenterTelephone(selectedCenter);
                String h = getCenterMail(selectedCenter);


                String centerInfo = "Center info:\nMunicipality " + b + "\nAddress " + f + "\nTelephone " + g + "\nMail" + h;

                String shopInfo = "Store info:\n " + a +"\nLocation " + c +"\nOpen " + d + "\nDescription " + e;
                textArea.setText(centerInfo +"\n-----------------------------------------------\n" + shopInfo);
                closeConnection();

            }catch (Exception c){
                shoolprodject.DatabasePackage.Database.printMesssage(c, "getStoreinCenterInfo");
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

}
