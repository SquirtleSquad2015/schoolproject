

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
import static javax.swing.JOptionPane.*;
/*
import java.util.ArrayList;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField.*;
*/

class Costumer extends JFrame{
    private String[] listData = {
            "Center",
            "Sirkus",
            "City Lade",
            "Trondheim torg",
            "Merkur senteret",
            "Malvik Senteret",
            "Solsiden"
        };
    private String[] listData2 = {
            "All shops",
            "Shop 2",
            "Shop 3",
            "Shop 4",
            "Shop 5",
            "Shop 6",
            "Shop 7"
        };
    private JLabel ledetekst1 = new JLabel("Navn:",JLabel.CENTER);
    private JLabel ledetekstSvar1 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst2 = new JLabel("Kategori:",JLabel.CENTER);
    private JLabel ledetekstSvar2 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst3 = new JLabel("Manager:",JLabel.CENTER);
    private JLabel ledetekstSvar3 = new JLabel("blabal info",JLabel.CENTER);
    private JLabel ledetekst4 = new JLabel("Beskrivelse:",JLabel.CENTER);
    private JTextArea textArea = new JTextArea(5,5);
    private JButton knapp1 = new JButton("Info, Senter");
    private JButton knapp2 = new JButton("info, butikk");
    private JButton knapp4 = new JButton("Close");
    private JButton knapp3 = new JButton("Costumer Service");
    private JScrollPane scroll = new JScrollPane();
    private JScrollPane scroll2 = new JScrollPane();
    private JList listbox = new JList();
    private JList listbox2 = new JList();
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
        LayoutManager layout2 = new GridLayout(6, 2, 3, 3);
        panel2.setLayout(layout2);
        LayoutManager layout3 = new GridLayout(1, 1, 3, 3);
        panel3.setLayout(layout3);
        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);
        
        
        
        listbox = new JList( listData );
        listbox2 = new JList( listData2 );
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
    }
   

    

        class Knappelytter1 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp1 = (JButton) hendelse.getSource();
                int index = listbox.getSelectedIndex();
                
                System.out.println(index);
                
                if(index==-1 || index==0){
                    System.out.println("Chose Shoping Center");
                    showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
                }
                if(index>0){
                    System.out.println("info info:  " + listData[index] );
                    ledetekstSvar1.setText("Navn "+listData[index]);
                    ledetekstSvar2.setText("Kategori "+listData[index]);
                    ledetekstSvar3.setText("Manager "+listData[index]);
                    textArea.setText("Beskrivelse "+listData[index]);
                }

            }
        }
        
        class Knappelytter2 extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent hendelse) {
                JButton knapp2 = (JButton) hendelse.getSource();
                
                int index = listbox.getSelectedIndex();
                int index2 = listbox2.getSelectedIndex();
                
                System.out.println(index+" og "+ index2);
                
                if(index==-1 || index==0){
                    showMessageDialog (null, "Chose Shoping Center", "Fail", JOptionPane.ERROR_MESSAGE);
                }
                if(index2==-1 || index2==0){
                    showMessageDialog (null, "Chose Shop", "Fail", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(index>0  && index2>0){
                    System.out.println("info info:  " +listData2[index2]+" på "+ listData[index] );
                    ledetekstSvar1.setText("Navn: "+listData2[index]);
                    ledetekstSvar2.setText("Kategori: "+listData2[index]);
                    ledetekstSvar3.setText("Manager "+listData2[index]);
                    textArea.setText("Beskrivelse: asdfgkeid dkerides \n ifeksfei skriskdo skeifskaf skr. "+listData2[index]);
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
        
        
}