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

public class AdminUserView extends JFrame {
    private JPanel masterPanel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    
    private ArrayList<String> list;
    private JButton newCenter = new JButton("New");
    private JButton Edit = new JButton("Edit/View");
    private JButton Shops = new JButton("Shops");
    private JButton Back = new JButton("Back");
    
    
    private DefaultListModel defaultListModel = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JScrollPane scroll = new JScrollPane(listbox);
    private String username;
    private String storeName;


    public AdminUserView(){
        setTitle("User View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,300);

        masterPanel.setLayout(new BorderLayout());
        panel1.setLayout(new GridLayout(1,1,3,3));
        panel2.setLayout(new GridLayout(1,2,3,3));
        
        panel1.add(scroll);
        panel2.add(Back);
        panel2.add(Edit);
        
        
        masterPanel.add(panel1, BorderLayout.CENTER);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        

        Action action = new Action();
        newCenter.addActionListener(action);
        Edit.addActionListener(action);
        Shops.addActionListener(action);
        Back.addActionListener(action);
        
        ListboxListener lytteren7 = new ListboxListener();
        listbox.addMouseListener(lytteren7);
        
        AutomatiskOppdatering lytteren6 = new AutomatiskOppdatering();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, lytteren6);
        timer.start();
        timer.setRepeats(false);

    }
    private class Action extends DatabaseConnection implements ActionListener{
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton)source.getSource();

            if (check == newCenter) {
                newCenter center = new newCenter();
                center.setLocationRelativeTo(null);
                center.setVisible(true);
            }
            if (check ==Edit){
                UpdateUser update = new UpdateUser(username);
                update.setLocationRelativeTo(null);
                update.setVisible(true);
                
            }
            if (check ==Shops){
                
            }
            if (check ==Back){
                dispose();
            }
            
        }
    }
    
    class ListboxListener extends DatabaseConnection implements MouseListener {
       public void actionPerformed(MouseEvent hendelse){
        }

        @Override //finner ut hvilket center du trykket på, og henter inn stores registrert i det senteret
        public void mouseClicked (MouseEvent hendelse) {
            try{
                openConnection();//må opprette sin egen, max 1 extends per klasse
                username = listbox.getSelectedValue().toString();
                
                closeConnection();
                System.out.println(username);
                

            }
            catch (Exception c){
                Database.printMesssage(c, "getStoresInCenter");
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseEntered(MouseEvent e){}
        @Override
        public void mouseExited(MouseEvent e){}
    }
    
    class AutomatiskOppdatering extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            int index = listbox.getSelectedIndex();
            try{
                openConnection();
                list =getUsers();
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