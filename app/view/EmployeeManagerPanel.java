/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.controller.EmployeeManagerController;
import app.model.Employee;
import config.Config;
import gui.main.ManagerPanel;
import gui.widgets.ColorTransitionButton;
import gui.widgets.TabButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author Admins
 */
public class EmployeeManagerPanel extends JPanel {
    EmployeeManagerController controller;

    public EmployeeManagerPanel() {
        loadComponent();
        controller = new EmployeeManagerController(this);
        controller.loadTable(Employee.all());
        controller.loadForm(profilePanel);
    }

    private void loadComponent() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_MANAGER));



        // list employee
        listEmployee = new JTable();
        listEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEmployee.setRowHeight(Config.resize(25));
        JScrollPane paneList = new JScrollPane(listEmployee);
        paneList.setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_LIST));
        paneList.setBounds(Config.resize(new Rectangle(20, 180, 300, 320)));
        add(paneList);
        tableModel = new DefaultTableModel(new Object[][]{}, config.Config.EMPLOYEE_LIST_COLUMN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        listEmployee.setModel(tableModel);
        listEmployee.getColumnModel().getColumn(0).setPreferredWidth(Config.resize(30));
        listEmployee.getColumnModel().getColumn(1).setPreferredWidth(Config.resize(100));
        listEmployee.getColumnModel().getColumn(2).setPreferredWidth(Config.resize(150));
        // search panel
        JPanel searchpnl = new JPanel(null);
        searchpnl.setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_SEARCH));
        searchpnl.setBounds(Config.resize(new Rectangle(20, 40, 300, 120)));
        add(searchpnl);
        searchBar = new JTextField();
        searchBar.setBounds(Config.resize(new Rectangle(5, 30, 200, 30)));
        searchBtn = new ColorTransitionButton(config.Config.EMPLOYEE_SEARCH);

        searchBtn.setBounds(Config.resize(new Rectangle(205, 30, 90, 30)));
        employeeNameRadio = new JRadioButton(config.Config.EMPLOYEE_NAME);
        employeeIdRadio = new JRadioButton(config.Config.EMPLOYEE_ID);
        employeeNameRadio.setBounds(Config.resize(new Rectangle(25, 70, 120, 30)));
        employeeIdRadio.setBounds(Config.resize(new Rectangle(150, 70, 100, 30)));
        ButtonGroup radioGroup = new ButtonGroup();
        employeeNameRadio.setSelected(true);
        radioGroup.add(employeeIdRadio);
        radioGroup.add(employeeNameRadio);
        searchpnl.add(searchBar);
        searchpnl.add(searchBtn);
        searchpnl.add(employeeNameRadio);
        searchpnl.add(employeeIdRadio);
        //information panel

        profilePanel = new EmployeeProfilePanel();
        profilePanel.setBounds(Config.resize(new Rectangle(340, 10, 610, 520)));
        add(profilePanel);


    }


    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRow(String row[]) {
        tableModel.addRow(row);
    }

    public void deleteRow(int row) {
        tableModel.removeRow(row);
    }

    public String getTextSearch() { return searchBar.getText(); }

    public EmployeeProfilePanel profilePanel;
    private DefaultTableModel tableModel;
    public JTable listEmployee;
    private JTextField searchBar;
    public JButton searchBtn;
    public JRadioButton employeeNameRadio;
    public JRadioButton employeeIdRadio;
}
