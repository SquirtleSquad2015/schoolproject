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
    private JButton Edit = new JButton("Edit/View");
    private JButton Back = new JButton("Back");
    
    
    private DefaultListModel defaultListModel = new DefaultListModel();
    private JList listbox = new JList(defaultListModel);
    private JScrollPane scroll = new JScrollPane(listbox);
    
    private String centerName;
    private String username;


    public AdminCenterView(){
        setTitle("Center View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,300);

        masterPanel.setLayout(new BorderLayout());
        panel1.setLayout(new GridLayout(1,1,3,3));
        panel2.setLayout(new GridLayout(1,3,3,3));
        
        panel1.add(scroll);
        panel2.add(Back);
        panel2.add(Edit);
        
        
        masterPanel.add(panel1, BorderLayout.CENTER);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        

        Action action = new Action();
        Back.addActionListener(action);
        Edit.addActionListener(action);
        
        
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
            
            if (check ==Edit){
                try{
                    openConnection();//må opprette sin egen, max 1 extends per klasse
                    username = getUsernameCenter(centerName);
                    closeConnection();
                    System.out.println(centerName);
                    System.out.println(username);
                    CenterManagerMenu menu =new CenterManagerMenu(username);
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);
                    dispose();
                }
                catch (Exception c){
                    Database.printMesssage(c, "getCenters For AdminView");
                }
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
                centerName = listbox.getSelectedValue().toString();
                closeConnection();
                System.out.println(centerName);
            }
            catch (Exception c){
                Database.printMesssage(c, "getCenters For AdminView");
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
