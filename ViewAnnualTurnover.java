package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewAnnualTurnover extends JFrame {
    private String centername;
    private ArrayList<String> storesArray = new ArrayList<String>();
    DefaultListModel defaultListModel = new DefaultListModel();
    JTextPane selectStore = new JTextPane();
    JTextPane turnover = new JTextPane();
    JComboBox stores = new JComboBox();
    JLabel viewTurnover = new JLabel("", JLabel.CENTER);
    JLabel total = new JLabel("", JLabel.CENTER);
    JLabel totalTurnover = new JLabel("", JLabel.CENTER);
    JButton back = new JButton("Back");


    public ViewAnnualTurnover(String centername) {
        ((JLabel) stores.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        setTitle("Annual turnover - " + centername);
        selectStore.setText("Select shop");
        selectStore.setPreferredSize(new Dimension(300, 10));
        selectStore.setEditable(false);
        selectStore.setCursor(null);
        selectStore.setOpaque(false);
        selectStore.setFocusable(false);
        selectStore.setFont(UIManager.getFont("Label.font"));
        turnover.setText("Turnover");
        turnover.setEditable(false);
        turnover.setCursor(null);
        turnover.setOpaque(false);
        turnover.setFocusable(false);
        turnover.setFont(UIManager.getFont("Label.font"));
        StyledDocument selectStoreStyle = selectStore.getStyledDocument();
        StyledDocument turnoverStyle = turnover.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        selectStoreStyle.setParagraphAttributes(0, selectStoreStyle.getLength(), center, false);
        turnoverStyle.setParagraphAttributes(0, turnoverStyle.getLength(), center, false);

        Update update = new Update();
        int delay = 100; //milliseconds
        Timer timer = new Timer(delay, update);
        timer.start();
        timer.setRepeats(false);

        this.centername = centername;
        LayoutManager gridLayout = new GridLayout(4, 2, 3, 3);
        setLayout(gridLayout);
        add(selectStore);
        add(turnover);
        add(stores);
        add(viewTurnover);
        add(total);
        add(totalTurnover);
        add(back);
        pack();
        Action action = new Action();
        stores.addActionListener(action);
        back.addActionListener(action);

    }

    class Action extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == stores) {
                try {
                    openConnection();
                    int selectedStore = stores.getSelectedIndex();
                    Integer turnover = getTurnoverStore(centername, storesArray.get(selectedStore));
                    viewTurnover.setText(turnover.toString() + " millions");

                    closeConnection();
                } catch (Exception c) {
                    Database.printMesssage(c, "getStoresInCenter");
                }
            } else {
                dispose();
            }
        }
    }

    class Update extends DatabaseConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int totalCenterTurnover = 0;
            int turnoverStore = 0;
            try {
                openConnection();
                storesArray = getStore(centername);
                for (int i = 0; i < storesArray.size(); i++) {
                    stores.addItem(storesArray.get(i));
                    turnoverStore = getTurnoverStore(centername, storesArray.get(i));
                    totalCenterTurnover += turnoverStore;
                }
                closeConnection();
                total.setText("Annual turnover - " + centername);
                totalTurnover.setText(totalCenterTurnover + " millions");
            } catch (Exception e) {
                Database.printMesssage(e, "Update");
            }
        }
    }
}


