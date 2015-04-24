package shoolprodject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Andreas on 10.04.2015.
 */
public class InloggedMeny extends JFrame {

    private JLabel ledetekst1 = new JLabel("Turnover: ", JLabel.CENTER);
    private JLabel ledetekstSvar1 = new JLabel("", JLabel.CENTER);
    private ArrayList<String> list;
    private ArrayList<String> list2;

    private JTextArea textArea = new JTextArea(5, 5);
    private JButton knapp1 = new JButton("Turnover store");
    private JButton knapp2 = new JButton("Register Turnover");
    private JButton knapp4 = new JButton("Find total turnover");
    private JButton knapp3 = new JButton("Sort store by turnover");
    private JScrollPane scroll = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();
    private JScrollPane scroll3 = new JScrollPane();
    DefaultListModel defaultListModel = new DefaultListModel();
    DefaultListModel defaultListModel2 = new DefaultListModel();
    //DefaultListModel defaultListModel3 = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JList listbox2 = new JList(defaultListModel2);
    //private JList listbox3 = new JList(defaultListModel3);

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel masterPanel = new JPanel();
    int antallButikker;


    public InloggedMeny() {
        setTitle("SCHMIDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);



        // Create a new listbox control
        LayoutManager layout1 = new GridLayout(1, 3, 3, 3);
        panel1.setLayout(layout1);
        LayoutManager layout2 = new GridLayout(7, 2, 3, 3);
        panel2.setLayout(layout2);
        LayoutManager layout3 = new GridLayout(1, 1, 3, 3);
        panel3.setLayout(layout3);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);



        //listbox3 = new JList(listData3);
        panel1.add(listbox, BorderLayout.CENTER);
        panel1.add(listbox2, BorderLayout.CENTER);
        //panel1.add(listbox3, BorderLayout.CENTER);
        scroll = new JScrollPane(listbox);
        scroll2 = new JScrollPane(listbox2);
        //scroll3 = new JScrollPane(listbox3);
        panel1.add(scroll, BorderLayout.CENTER);
        panel1.add(scroll2, BorderLayout.CENTER);
        //panel1.add(scroll3, BorderLayout.CENTER);


        panel2.add(knapp1);
        panel2.add(knapp2);
        panel2.add(knapp3);
        panel2.add(knapp4);
        panel2.add(ledetekst1);
        panel2.add(ledetekstSvar1);

        textArea.setLineWrap(true);


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

        //Knappelytter3 lytteren3 = newKnappelytter3();
        //knapp3.addActionListener(lytteren3);

        Knappelytter4 lytteren4 = new Knappelytter4();
        knapp4.addActionListener(lytteren4);

        ListboxListener lytteren5 = new ListboxListener();
        listbox.addMouseListener(lytteren5);

        AutomatiskOppdatering2 lytteren6 = new AutomatiskOppdatering2();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

        ListboxListener2 lytteren7 = new ListboxListener2();
        listbox2.addMouseListener(lytteren7);


    }


    class Knappelytter1 extends DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp1 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();

            try{
                openConnection();
                Integer svar = getTurnoverStore(listbox.getSelectedValue().toString(),listbox2.getSelectedValue().toString());
                ledetekstSvar1.setText(svar.toString());
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getTurnover");
            }

        }
    }

    class Knappelytter3 extends DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp1 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();

            try{
                openConnection();
                Integer svar = getTurnoverStore(listbox.getSelectedValue().toString(),listbox2.getSelectedValue().toString());
                ledetekstSvar1.setText(svar.toString());
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getTurnover");
            }


        }
    }

    class Knappelytter2 extends JFrame implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp2 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();


            if (index > 0) {
                JFrame frame = new JFrame("Register turnover:");
                String newturnover = JOptionPane.showInputDialog(frame,"Turnover...");
                int turnover = Integer.parseInt(newturnover);
                //listData3[index][index2] = turnover;
            }
        }
    }




    class Knappelytter4 extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp4 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();
            int annualTurnover = 0;

            try{
                openConnection();
                for(int i = 0; i< antallButikker; i++ ) {
                    Integer svar = getTurnoverStore(listbox.getSelectedValue().toString(), listbox2.getModel().getElementAt(i).toString());
                    annualTurnover += svar;
                }
                closeConnection();
            }
            catch (Exception e){
                Database.printMesssage(e, "getTurnover");
            }
            Integer B = annualTurnover;
            ledetekstSvar1.setText("Total annual turnover for center: " + B.toString());

        }
    }

    class ListboxListener2 extends JFrame implements MouseListener {
        private DatabaseConnection DBconnection = new DatabaseConnection(); //må opprette sin egen, max 1 extends per klasse
        public void actionPerformed(MouseEvent hendelse){
    }

        @Override //finner ut hvilken store du trykket på, og henter inn turnover registrert på storen
        public void mouseClicked (MouseEvent hendelse) {
            try{
                DBconnection.openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                String selectedStore = listbox2.getSelectedValue().toString();
                System.out.println("center: " + selectedCenter + ", store:"+ selectedStore);
                Integer turnover = DBconnection.getTurnoverStore(selectedCenter, selectedStore);
                ledetekstSvar1.setText(turnover.toString());
                //defaultListModel2.clear();

                DBconnection.closeConnection();
            }
            catch (Exception c){
                Database.printMesssage(c, "getStoresInCenter");
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



    class ListboxListener extends JFrame implements MouseListener {
        private DatabaseConnection DBconnection = new DatabaseConnection(); //må opprette sin egen, max 1 extends per klasse
        public void actionPerformed(MouseEvent hendelse){


            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();
        }

        @Override //finner ut hvilket center du trykket på, og henter inn stores registrert i det senteret
        public void mouseClicked (MouseEvent hendelse) {
            try{
                DBconnection.openConnection();//må opprette sin egen, max 1 extends per klasse
                String selectedCenter = listbox.getSelectedValue().toString();
                System.out.println(selectedCenter);
                list2 = DBconnection.getStore(selectedCenter);
                defaultListModel2.clear();
                antallButikker = list2.size();


                for(int i = 0; i < list2.size(); i++){
                    defaultListModel2.addElement(list2.get(i));
                }
                DBconnection.closeConnection();
            }
            catch (Exception c){
                Database.printMesssage(c, "getStoresInCenter");
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


    class AutomatiskOppdatering2 extends DatabaseConnection implements ActionListener {
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