package shoolprodject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        Knappelytter4 lytteren4 = new Knappelytter4();
        knapp4.addActionListener(lytteren4);

        AutomatiskOppdatering2 lytteren6 = new AutomatiskOppdatering2();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);
        //listbox2.addAc

    }


    class Knappelytter1 extends DatabaseConnection implements ActionListener {
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




    class Knappelytter4 extends JFrame implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton knapp4 = (JButton) hendelse.getSource();
            int index = listbox.getSelectedIndex();
            int index2 = listbox2.getSelectedIndex();


            if (index > 0) {
                int sum = 0;
               // for(int i = 1; i < listData3.length; i++){
               //     sum += (listData3[index][i]);
               // }
                String svar = Integer.toString(sum);
                ledetekstSvar1.setText(svar);
            }
        }
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