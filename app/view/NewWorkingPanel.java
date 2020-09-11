/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.controller.AddNewWorkingController;
import com.toedter.calendar.JDateChooser;
import config.Config;
import gui.widgets.ColorTransitionButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author Admins
 */
public class NewWorkingPanel extends JPanel {
    private AddNewWorkingController controller;

    public NewWorkingPanel() {
        loadComponent();
        controller = new AddNewWorkingController(this);
    }

    private void loadComponent() {
        setLayout(null);
        JPanel panelDate = new JPanel(null);
        JPanel panelProject = new JPanel(null);
        JPanel panelEmployee = new JPanel(null);
        JPanel panelInfor = new JPanel(null);
        panelDate.setBorder(BorderFactory.createEtchedBorder());
        panelInfor.setBorder(BorderFactory.createEtchedBorder());
        panelProject.setBorder(BorderFactory.createTitledBorder(Config.PROJECT_LIST));
        panelEmployee.setBorder(BorderFactory.createTitledBorder(Config.EMPLOYEE_LIST));
        panelDate.setBounds(Config.resize(new Rectangle(25, 40, 200, 270)));
        panelProject.setBounds(Config.resize(new Rectangle(230, 30, 360, 280)));
        panelEmployee.setBounds(Config.resize(new Rectangle(600, 30, 360, 280)));
        panelInfor.setBounds(Config.resize(new Rectangle(210, 320, 400, 40)));

        JLabel datestartlb = new JLabel(Config.PROJECT_START_DATE + ":");
        JLabel datefinishlb = new JLabel(Config.PROJECT_FINISH_DATE + ":");
        datestart = new JDateChooser();
        datefinish = new JDateChooser();
        datestartlb.setBounds(Config.resize(new Rectangle(10, 10, 180, 40)));
        datefinishlb.setBounds(Config.resize(new Rectangle(10, 150, 180, 40)));
        datestart.setBounds(Config.resize(new Rectangle(10, 50, 180, 40)));
        datefinish.setBounds(Config.resize(new Rectangle(10, 190, 180, 40)));

        panelDate.add(datestartlb);
        panelDate.add(datefinishlb);
        panelDate.add(datestart);
        panelDate.add(datefinish);

        JLabel projectlb = new JLabel(Config.PROJECT_INPUT);
        projectTxt = new JTextField();
        searchProjectBtn = new ColorTransitionButton(Config.EMPLOYEE_SEARCH);
        projectCodeRBtn = new JRadioButton(Config.PROJECT_ID);
        projectNameRBtn = new JRadioButton(Config.PROJECT_NAME);
        ButtonGroup group = new ButtonGroup();
        group.add(projectCodeRBtn);
        group.add(projectNameRBtn);
        projectCodeRBtn.setSelected(true);

        listProject = new JTable();
        listProject.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listProject.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listProject.setRowHeight(Config.resize(25));
        JScrollPane paneList = new JScrollPane(listProject);
        tableProjectModel = new DefaultTableModel(new Object[][]{}, Config.PROJECT_LIST_COLUMN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        listProject.setModel(tableProjectModel);
        listProject.getColumnModel().getColumn(0).setPreferredWidth(Config.resize(30));
        listProject.getColumnModel().getColumn(1).setPreferredWidth(Config.resize(100));
        listProject.getColumnModel().getColumn(2).setPreferredWidth(Config.resize(150));
        projectTxt.setBounds(Config.resize(new Rectangle(30, 20, 200, 30)));
        searchProjectBtn.setBounds(Config.resize(new Rectangle(235, 20, 90, 30)));
        projectCodeRBtn.setBounds(Config.resize(new Rectangle(50, 60, 120, 30)));
        projectNameRBtn.setBounds(Config.resize(new Rectangle(200, 60, 120, 30)));
        paneList.setBounds(Config.resize(new Rectangle(30, 90, 300, 180)));
        panelProject.add(projectTxt);
        panelProject.add(searchProjectBtn);
        panelProject.add(paneList);
        panelProject.add(projectCodeRBtn);
        panelProject.add(projectNameRBtn);

        employeeTxt = new JTextField();
        searchEmployeeBtn = new ColorTransitionButton(Config.EMPLOYEE_SEARCH);
        employeeCodeRb = new JRadioButton(Config.PROJECT_ID);
        employeeNameRb = new JRadioButton(Config.PROJECT_NAME);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(employeeCodeRb);
        group2.add(employeeNameRb);
        employeeCodeRb.setSelected(true);

        listEmployee = new JTable();
        listEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEmployee.setRowHeight(Config.resize(25));
        JScrollPane paneList2 = new JScrollPane(listEmployee);
        tableEmployeeModel = new DefaultTableModel(new Object[][]{}, Config.ADD_EMPLOYEE_LIST_COLUMN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) return true;
                return false;
            }

            public Class getColumnClass(int c) {
                if (c == 3) return Boolean.class;
                return String.class;
            }
        };
        listEmployee.setModel(tableEmployeeModel);
        listEmployee.getColumnModel().getColumn(0).setPreferredWidth(Config.resize(20));
        listEmployee.getColumnModel().getColumn(1).setPreferredWidth(Config.resize(80));
        listEmployee.getColumnModel().getColumn(2).setPreferredWidth(Config.resize(140));
        listEmployee.getColumnModel().getColumn(3).setPreferredWidth(Config.resize(40));
        employeeTxt.setBounds(Config.resize(new Rectangle(30, 20, 200, 30)));
        searchEmployeeBtn.setBounds(Config.resize(new Rectangle(235, 20, 90, 30)));
        employeeCodeRb.setBounds(Config.resize(new Rectangle(50, 60, 120, 30)));
        employeeNameRb.setBounds(Config.resize(new Rectangle(200, 60, 120, 30)));
        paneList2.setBounds(Config.resize(new Rectangle(30, 90, 300, 180)));
        panelEmployee.add(employeeTxt);
        panelEmployee.add(searchEmployeeBtn);
        panelEmployee.add(paneList2);
        panelEmployee.add(employeeCodeRb);
        panelEmployee.add(employeeNameRb);

        projectTitle = new JLabel(Config.PROJECT_NAME + ":");
        projectTitle.setFont(new java.awt.Font("Tahoma", 1, Config.resize(14)));
        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setBounds(Config.resize(new Rectangle(0, 0, 400, 40)));
        panelInfor.add(projectTitle);

        descriptionTxt = new JTextArea();
        descriptionTxt.setBorder(BorderFactory.createTitledBorder(null, Config.DESCRIPTION,
            TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.BOLD, Config.resize(10))));
        descriptionTxt.setWrapStyleWord(true);
        descriptionTxt.setLineWrap(true);
        descriptionTxt.setFont(new java.awt.Font("Tahoma", 0, Config.resize(14)));
        JScrollPane scrollPane = new JScrollPane(descriptionTxt);
        add(scrollPane);
        add(panelDate);
        add(panelProject);
        add(panelEmployee);
        add(panelInfor);
        saveBtn = new ColorTransitionButton(Config.SAVE);
        add(saveBtn);

        scrollPane.setBounds(Config.resize(new Rectangle(160, 365, 500, 170)));
        saveBtn.setBounds(Config.resize(new Rectangle(700, 450, 100, 50)));
    }

    public void addRow(String table, Object[] row) {
        if (table.compareTo("Employee") == 0)
            tableEmployeeModel.addRow(row);
        else if (table.compareTo("Project") == 0)
            tableProjectModel.addRow(row);
    }

    public void clearTable(String table) {
        if (table.compareTo("Employee") == 0)
            tableEmployeeModel.setRowCount(0);
        else if (table.compareTo("Project") == 0)
            tableProjectModel.setRowCount(0);
    }

    JDateChooser datestart;
    JDateChooser datefinish;

    JTextField projectTxt;
    JRadioButton projectCodeRBtn;
    JRadioButton projectNameRBtn;

    JTextField employeeTxt;
    JRadioButton employeeCodeRb;
    JRadioButton employeeNameRb;

    JButton searchEmployeeBtn;
    JButton searchProjectBtn;
    JTable listEmployee;
    JTable listProject;
    DefaultTableModel tableProjectModel;
    DefaultTableModel tableEmployeeModel;

    JLabel projectTitle;
    JTextArea descriptionTxt;

    JButton saveBtn;
}