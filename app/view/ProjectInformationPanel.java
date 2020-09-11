/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import com.toedter.calendar.JDateChooser;
import config.Config;
import gui.widgets.ColorTransitionButton;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;

/**
 * @author Admins
 */
public class ProjectInformationPanel extends JPanel {
    public ProjectInformationPanel() {
        loadComponent();
    }

    private void loadComponent() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder(Config.PROJECT_INFORMATION));
        JPanel panel1 = new JPanel(null);
        JPanel panel2 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder());
        panel2.setBorder(BorderFactory.createEtchedBorder());
        panel1.setBounds(Config.resize(new Rectangle(50, 25, 500, 200)));
        panel2.setBounds(Config.resize(new Rectangle(50, 450, 500, 60)));

        JLabel emIDlb = new JLabel(Config.PROJECT_ID + ":");
        JLabel emNamelb = new JLabel(Config.PROJECT_NAME + ":");
        projectIdText = new JTextField();
        projectNameText = new JTextField();
        JLabel startday = new JLabel(Config.PROJECT_START_TIME + ":");
        JLabel finishday = new JLabel(Config.PROJECT_FINISH_TIME);
        startChooser = new JDateChooser();
        finishChooser = new JDateChooser();
        emIDlb.setBounds(Config.resize(new Rectangle(60, 10, 80, 50)));
        emNamelb.setBounds(Config.resize(new Rectangle(60, 70, 90, 50)));
        projectIdText.setBounds(Config.resize(new Rectangle(135, 20, 145, 30)));
        projectNameText.setBounds(Config.resize(new Rectangle(135, 80, 290, 30)));
        startday.setBounds(Config.resize(new Rectangle(60, 130, 80, 50)));
        startChooser.setBounds(Config.resize(new Rectangle(135, 140, 120, 30)));
        finishday.setBounds(Config.resize(new Rectangle(270, 130, 40, 50)));
        finishChooser.setBounds(Config.resize(new Rectangle(305, 140, 120, 30)));
        panel1.add(emIDlb);
        panel1.add(emNamelb);
        panel1.add(projectIdText);
        panel1.add(projectNameText);
        panel1.add(startday);
        panel1.add(startChooser);
        panel1.add(finishday);
        panel1.add(finishChooser);
//        descriptionText
        descriptionText = new JTextArea();
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setBorder(BorderFactory.createTitledBorder(Config.DESCRIPTION));
        JScrollPane scrollPane = new JScrollPane(descriptionText);
        scrollPane.setBounds(Config.resize(new Rectangle(50, 230, 500, 210)));
//        button panel
        deleteBtn = new ColorTransitionButton("Xóa");
        addNewBtn = new ColorTransitionButton("Thêm mới");
        editBtn = new ColorTransitionButton("Sửa");
        cancelBtn = new ColorTransitionButton("Hủy");
        saveBtn = new ColorTransitionButton("Lưu");
        deleteBtn.setBounds(Config.resize(new Rectangle(10, 10, 100, 40)));
        addNewBtn.setBounds(Config.resize(new Rectangle(200, 10, 100, 40)));
        editBtn.setBounds(Config.resize(new Rectangle(390, 10, 100, 40)));
        cancelBtn.setBounds(Config.resize(new Rectangle(10, 10, 100, 40)));
        saveBtn.setBounds(Config.resize(new Rectangle(390, 10, 100, 40)));
        panel2.add(deleteBtn);
        panel2.add(addNewBtn);
        panel2.add(editBtn);
        panel2.add(cancelBtn);
        panel2.add(saveBtn);

        add(panel1);
        add(panel2);
        add(scrollPane);
    }

    public JButton cancelBtn;
    public JButton saveBtn;
    public JButton editBtn;
    public JButton addNewBtn;
    public JButton deleteBtn;

    private JTextField projectIdText;
    private JTextField projectNameText;
    private JDateChooser startChooser;
    private JDateChooser finishChooser;
    private JTextArea descriptionText;
}
