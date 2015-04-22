package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class AdminCenterView extends JFrame {
    private JPanel masterPanel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    
    private ArrayList<String> list;
    private JButton Users = new JButton("Edit");
    private JButton Centers = new JButton("New");
    private JButton Back = new JButton("Back");
    
    
    
    private JScrollPane scroll = new JScrollPane();
    private DefaultListModel defaultListModel = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);


    public AdminCenterView(){
        LayoutManager layout = new GridLayout(4, 1, 3, 3);
        masterPanel.setLayout(new BorderLayout());
        panel1.setLayout(new GridLayout(1,1,3,3));
        panel2.setLayout(new GridLayout(1,3,3,3));
        panel1.setSize(300, 200);
        //masterPanel.setPre
        listbox.setSize(300, 200);
        panel1.add(listbox);
        
        
        panel2.add(Users);
        panel2.add(Centers);
        panel2.add(Back);
        
        masterPanel.add(panel1, BorderLayout.CENTER);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        pack();
        

        Action action = new Action();
        Users.addActionListener(action);
        Centers.addActionListener(action);
        Back.addActionListener(action);
        
        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);
        
        
        
    }
    private class Action extends DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton)source.getSource();

            if (check == Users) {
                
            }
            if (check ==Centers){
                
            }
            if (check ==Back){
                
            }
        }
    }
    
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