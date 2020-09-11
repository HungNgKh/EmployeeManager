/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.controller.ProjectManagerController;
import config.Config;
import gui.widgets.ColorTransitionButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author Admins
 */
public class ProjectManagerPanel extends JPanel {
    private ProjectManagerController controller;

    public ProjectManagerPanel() {
        loadComponent();
        controller = new ProjectManagerController(this);
    }

    private void loadComponent() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder(config.Config.PROJECT_MANAGER));
        //list project
        listProject = new JTable();
        listProject.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listProject.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listProject.setRowHeight(config.Config.resize(25));
        JScrollPane paneList = new JScrollPane(listProject);
        paneList.setBorder(BorderFactory.createTitledBorder(config.Config.PROJECT_LIST));
        paneList.setBounds(config.Config.resize(new Rectangle(20, 180, 300, 320)));
        add(paneList);
        tableModel = new DefaultTableModel(new Object[][]{}, config.Config.PROJECT_LIST_COLUMN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        listProject.setModel(tableModel);
        listProject.getColumnModel().getColumn(0).setPreferredWidth(config.Config.resize(30));
        listProject.getColumnModel().getColumn(1).setPreferredWidth(config.Config.resize(100));
        listProject.getColumnModel().getColumn(2).setPreferredWidth(config.Config.resize(150));
        // search panel
        JPanel searchpnl = new JPanel(null);
        searchpnl.setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_SEARCH));
        searchpnl.setBounds(config.Config.resize(new Rectangle(20, 40, 300, 120)));
        add(searchpnl);
        searchBar = new JTextField();
        searchBar.setBounds(config.Config.resize(new Rectangle(5, 30, 200, 30)));
        searchBtn = new ColorTransitionButton(config.Config.EMPLOYEE_SEARCH);
        searchBtn.setBounds(config.Config.resize(new Rectangle(205, 30, 90, 30)));
        projectNameRadio = new JRadioButton(config.Config.PROJECT_NAME);
        projectIdRadio = new JRadioButton(config.Config.PROJECT_ID);
        projectNameRadio.setBounds(config.Config.resize(new Rectangle(25, 70, 120, 30)));
        projectIdRadio.setBounds(config.Config.resize(new Rectangle(150, 70, 100, 30)));
        ButtonGroup radioGroup = new ButtonGroup();
        projectNameRadio.setSelected(true);
        radioGroup.add(projectIdRadio);
        radioGroup.add(projectNameRadio);
        searchpnl.add(searchBar);
        searchpnl.add(searchBtn);
        searchpnl.add(projectNameRadio);
        searchpnl.add(projectIdRadio);
        //information panel
        inforPanel = new ProjectInformationPanel();
        inforPanel.setBounds(config.Config.resize(new Rectangle(340, 10, 610, 520)));
        add(inforPanel);
    }

    public void clearTable() { tableModel.setRowCount(0); }
    public void addRow(String row[]) { tableModel.addColumn(row); }
    public void deleteRow(int row) { tableModel.removeRow(row); }
    public String getTextSearch() { return searchBar.getText(); }

    public ProjectInformationPanel inforPanel;
    private DefaultTableModel tableModel;
    private JTable listProject;
    private JTextField searchBar;
    public JButton searchBtn;
    public JRadioButton projectNameRadio;
    public JRadioButton projectIdRadio;
}
