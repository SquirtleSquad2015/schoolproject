/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoolprodject;

import shoolprodject.DatabasePackage.Database;
import shoolprodject.DatabasePackage.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Created by jonas on 15.04.15.
 */
public class AnswerQuestion extends JFrame {
    private String username;
    private String centerName;
    private int selectedIndex;
    private int selectedIndexLog;
    ArrayList<String> subject = new ArrayList<String>();
    ArrayList<Integer> customerCaseID = new ArrayList<Integer>();
    ArrayList<String> subjectLog = new ArrayList<String>();
    ArrayList<Integer> customerCaseIDLog = new ArrayList<Integer>();
    JButton selectQuestion = new JButton("Select question");
    JTextArea question = new JTextArea("Please select question");
    JTextArea textField = new JTextArea("Answer");
    // Main Window--------------------------
    JButton post = new JButton("Post");
    JButton back = new JButton("Back");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel masterPanel = new JPanel();
    JPanel questionPanel = new JPanel();
    JPanel answerPanel = new JPanel();
    JPanel selectPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    // JFrame 1 - select question ---------------------------------------
    JFrame jFrame = new JFrame();
    JButton search = new JButton("Search for title");
    JButton select = new JButton("Select question");
    JButton backQ = new JButton("Back");
    JButton log = new JButton("Log");
    JTextField searchWord = new JTextField(20);
    DefaultListModel defaultListModel = new DefaultListModel();
    JList list = new JList(defaultListModel);
    JScrollPane scrollPane = new JScrollPane(list);
    JTextArea textArea = new JTextArea("Description");
    JScrollPane scroll = new JScrollPane(textArea);
    // JFrame 2 - Log ------------------------------------------
    JFrame logFrame = new JFrame();
    JButton searchLog = new JButton("Search for title");
    JButton selectLog = new JButton("Select question");
    JButton backLog = new JButton("Back");
    JButton delete = new JButton("Delete");
    JTextField searchWordLog = new JTextField(20);
    DefaultListModel defaultListModelLog = new DefaultListModel();
    JList listLog = new JList(defaultListModelLog);
    JScrollPane scrollPaneLog = new JScrollPane(listLog);
    JTextArea textAreaLog = new JTextArea("Description");
    JScrollPane scrollLog = new JScrollPane(textAreaLog);


    public AnswerQuestion(String username) {
        super("CustomerService - Answer questions");
        this.username = username;
        textField.setPreferredSize(new Dimension(330, 150));
        textField.setLineWrap(true);
        question.setPreferredSize(new Dimension(330, 150));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);
        question.setOpaque(false);
        // JFrame 1 - select question --------------------------------------------
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        selectQuestion.setAlignmentX(JButton.CENTER_ALIGNMENT);
        post.setAlignmentX(JButton.CENTER_ALIGNMENT);
        back.setAlignmentX(JButton.CENTER_ALIGNMENT);

        LayoutManager masterLayout = new BorderLayout();
        masterPanel.setLayout(masterLayout);

        LayoutManager questionLayout = new GridLayout(1, 1, 3, 3);
        LayoutManager buttonLayout = new GridLayout(3, 1, 3, 3);
        question.setLayout(questionLayout);
        answerPanel.setLayout(questionLayout);
        buttonPanel.setLayout(buttonLayout);

        LayoutManager panelLayout = new BorderLayout();
        panel1.setLayout(panelLayout);
        panel2.setLayout(panelLayout);

        questionPanel.add(question);
        selectPanel.add(selectQuestion);
        answerPanel.add(textField);
        buttonPanel.add(post);
        buttonPanel.add(selectQuestion);
        buttonPanel.add(back);


        panel1.add(selectPanel, BorderLayout.NORTH);
        panel2.add(questionPanel, BorderLayout.NORTH);
        panel2.add(answerPanel, BorderLayout.CENTER);
        panel2.add(buttonPanel, BorderLayout.SOUTH);
        //masterPanel.add(panel1, BorderLayout.NORTH);
        masterPanel.add(panel2, BorderLayout.SOUTH);
        add(masterPanel);
        pack();

        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if ("Answer".equals(textField.getText())) {
                    textField.setText("");
                }
            }
            public void focusLost(FocusEvent focusEvent) {
                if ("".equals(textField.getText())) {
                    textField.setText("Answer");
                }
            }
        });

        // JFrame 1 - Select question ---------------------------------------
        Action action = new Action();
        selectQuestion.addActionListener(action);
        post.addActionListener(action);
        back.addActionListener(action);

        JPanel top = new JPanel();
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        LayoutManager topLayout = new GridLayout(1, 2, 3, 3);
        top.setLayout(topLayout);
        center.setLayout(topLayout);
        LayoutManager bottomLayout = new GridLayout(1,3,3,3);
        bottom.setLayout(bottomLayout);

        jFrame.setTitle("Questions");
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        LayoutManager layout = new BorderLayout();
        jFrame.setLayout(layout);

        top.add(searchWord);
        top.add(search);
        center.add(scrollPane);
        center.add(scroll);
        bottom.add(backQ);
        bottom.add(log);
        bottom.add(select);

        jFrame.add(top, BorderLayout.NORTH);
        jFrame.add(center, BorderLayout.CENTER);
        jFrame.add(bottom, BorderLayout.SOUTH);
        jFrame.pack();

        ActionSelect actionSelect = new ActionSelect();
        backQ.addActionListener(actionSelect);
        select.addActionListener(actionSelect);
        search.addActionListener(actionSelect);
        log.addActionListener(actionSelect);
        list.addMouseListener(actionSelect);

        // JFrame 2 - Log -----------------------------------------------------
        textAreaLog.setLineWrap(true);
        textAreaLog.setWrapStyleWord(true);
        textAreaLog.setEditable(false);
        textAreaLog.setOpaque(false);

        logFrame.setTitle("Log");
        LayoutManager logFrameLayout = new BorderLayout();
        logFrame.setLayout(logFrameLayout);
        JPanel logTop = new JPanel();
        JPanel logCenter = new JPanel();
        JPanel logBottom = new JPanel();
        logTop.setLayout(topLayout);
        logCenter.setLayout(topLayout);
        logBottom.setLayout(topLayout);
        logTop.add(searchWordLog);
        logTop.add(searchLog);
        logCenter.add(scrollPaneLog);
        logCenter.add(scrollLog);
        logBottom.add(backLog);
        logBottom.add(selectLog);
        logBottom.add(delete);

        logFrame.add(logTop, BorderLayout.NORTH);
        logFrame.add(logCenter, BorderLayout.CENTER);
        logFrame.add(logBottom, BorderLayout.SOUTH);
        /*logFrame.add(top, BorderLayout.NORTH);
        logFrame.add(center, BorderLayout.CENTER);
        logFrame.add(bottom, BorderLayout.SOUTH);
        logFrame.pack();*/
        logFrame.pack();
        ActionLog actionLog = new ActionLog();
        backLog.addActionListener(actionLog);
        selectLog.addActionListener(actionLog);
        searchLog.addActionListener(actionLog);
        delete.addActionListener(actionLog);
        listLog.addMouseListener(actionLog);


    }
    private class Action extends DatabaseConnection implements ActionListener {
        public void actionPerformed(ActionEvent source) {
            JButton check = (JButton) source.getSource();
            if (check == selectQuestion) {
                setVisible(false);
                jFrame.setVisible(true);
                jFrame.setLocationRelativeTo(null);
            } else if (check == post) {
                String getAnswer = textField.getText();
                String title = "Post answer";
                String message = "Are you sure you want to post: " + getAnswer;
                if(!question.getText().equals("Please select question") && customerCaseID.size() > 0) {
                    if(getAnswer.length() > 0 && !getAnswer.equals("Answer")) {
                        int reply = showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                        if(reply == JOptionPane.YES_OPTION) {
                            try {
                                openConnection();
                                int complete = setAnswer(getAnswer, customerCaseID.get(selectedIndex));
                                if (complete == 1) {
                                    textField.setText("");
                                    question.setText("Please select question");
                                    showMessageDialog(null, "Answer registered");

                                }
                                closeConnection();
                            } catch (Exception e) {
                                Database.printMesssage(e, "setAnswer - action");
                            }
                        }
                    } else {
                        showMessageDialog(null, "Please enter Answer");
                    }
                }
                else if(!question.getText().equals("Please select question") && customerCaseIDLog.size() > 0){
                    if(getAnswer.length() > 0 && !getAnswer.equals("Answer")) {
                        int reply = showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                        if(reply == JOptionPane.YES_OPTION) {
                            try {
                                openConnection();
                                int complete = setAnswer(getAnswer, customerCaseIDLog.get(selectedIndexLog));
                                if (complete == 1) {
                                    question.setText("Please select question");
                                    textField.setText("");
                                    showMessageDialog(null, "Answer registered");
                                }
                                closeConnection();
                            } catch (Exception e) {
                                Database.printMesssage(e, "setAnswer - action");
                            }
                        }
                    } else {
                        showMessageDialog(null, "Please enter Answer");
                    }
                } else {
                    showMessageDialog(null, "No question has been selected!" + "\n" + "Please select question");
                }
            } else {
                jFrame.dispose();
                dispose();
            }
        }
    }
    private class ActionSelect extends DatabaseConnection implements ActionListener, MouseListener {
        String description = "";
        public void actionPerformed(ActionEvent source) {
            JButton getSource = (JButton) source.getSource();
            if (getSource == search) {
                defaultListModel.clear();
                String titleWord = searchWord.getText();
                try {
                    openConnection();
                    centerName = getCenter(username);
                    customerCaseID = getCustomerCaseID(titleWord, centerName, 'n');
                    subject = customerServiceGetTitle(titleWord, centerName, 'n');
                    for (int i = 0; i < subject.size(); i++) {
                        defaultListModel.addElement(subject.get(i));
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "CustomerServiceGetTitle");
                }
                if(subject.size() == 0 && !titleWord.equals("")){
                    showMessageDialog(null, "No unresolved questions with subject: " + titleWord);
                }
                else if(subject.size() == 0){
                    showMessageDialog(null, "No unresolved questions");
                }
            } else if (getSource == select) {
                if(!description.equals("")){
                    question.setText(description);
                    description ="";
                    textArea.setText("");
                    setVisible(true);
                    jFrame.setVisible(false);
                    defaultListModel.clear();
                } else {
                  showMessageDialog(null, "No question was selected");
                }
            }
            else if (getSource == log){
                jFrame.setVisible(false);
                logFrame.setVisible(true);
                logFrame.setLocationRelativeTo(null);
            }else {
                jFrame.dispose();
                setVisible(true);
            }
        }
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getClickCount() == 2){
                selectedIndex = list.getSelectedIndex();
                try {
                    openConnection();
                    description = getDescription(customerCaseID.get(selectedIndex));
                    textArea.setText(description);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "MouseClicked - Description");
                }
            }
        }
        public void mousePressed(MouseEvent mouseEvent) {}
        public void mouseReleased(MouseEvent mouseEvent) {}
        public void mouseEntered(MouseEvent mouseEvent) {}
        public void mouseExited(MouseEvent mouseEvent) {}
    }
    public class ActionLog extends DatabaseConnection implements ActionListener, MouseListener {
        String description;
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == searchLog){
                defaultListModelLog.clear();
                String titleWord = searchWordLog.getText();
                try {
                    openConnection();
                    centerName = getCenter(username);
                    customerCaseIDLog = getCustomerCaseID(titleWord, centerName, 'y');
                    subjectLog = customerServiceGetTitle(titleWord, centerName, 'y');
                    for (int i = 0; i < subjectLog.size(); i++) {
                        System.out.println(customerCaseIDLog.get(i));
                        defaultListModelLog.addElement(subjectLog.get(i));
                    }
                    closeConnection();
                } catch (Exception e) {
                    Database.printMesssage(e, "CustomerServiceGetTitle - Log");
                }
                if(subjectLog.size() == 0 && !titleWord.equals("")){
                    showMessageDialog(null, "No history with subject: " + titleWord);
                }
                else if(subjectLog.size() == 0){
                    showMessageDialog(null, "No history");
                }
            }
            else if(actionEvent.getSource() == selectLog){
                if(!description.equals("")){
                    question.setText(description);
                    setVisible(true);
                    logFrame.setVisible(false);
                } else {
                    showMessageDialog(null, "No question was selected");
                }
            }
            else if(actionEvent.getSource() == delete){
                int ok;
                try {
                    openConnection();
                    ok = deleteCustomerCase(customerCaseIDLog.get(selectedIndexLog));
                    if(ok == 1){
                        String titleWord = searchWordLog.getText();
                        showMessageDialog(null, "Case: " + customerCaseIDLog.get(selectedIndexLog) + " deleted successfully");
                        defaultListModelLog.clear();
                        textAreaLog.setText("Description");
                        customerCaseIDLog = getCustomerCaseID(titleWord, centerName, 'y');
                        subjectLog = customerServiceGetTitle(titleWord, centerName, 'y');
                        for (int i = 0; i < subjectLog.size(); i++) {
                            System.out.println(customerCaseIDLog.get(i));
                            defaultListModelLog.addElement(subjectLog.get(i));
                        }
                    }
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "Delete case");
                }
            } else {
                logFrame.dispose();
                jFrame.setVisible(true);
            }
        }

        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getClickCount() == 2){
                selectedIndexLog = listLog.getSelectedIndex();
                System.out.println(customerCaseIDLog.get(selectedIndexLog));
                try {
                    openConnection();
                    description = getDescription(customerCaseIDLog.get(selectedIndexLog));
                    textAreaLog.setText(description);
                    closeConnection();
                }
                catch (Exception e){
                    Database.printMesssage(e, "MouseClicked - Description");
                }
            }
        }
        public void mousePressed(MouseEvent mouseEvent) {}
        public void mouseReleased(MouseEvent mouseEvent) {}
        public void mouseEntered(MouseEvent mouseEvent) {}
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}