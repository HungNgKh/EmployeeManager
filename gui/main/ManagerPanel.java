package gui.main;

import app.view.EmployeeManagerPanel;
import app.view.NewWorkingPanel;
import app.view.ProjectManagerPanel;
import app.view.SchedulePanel;
import gui.widgets.TabButton;
import gui.widgets.TabButtonPanel;
import gui.widgets.TabPanel;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

public class ManagerPanel extends JPanel{

    public TabButtonPanel tab_button_panel;


    public ManagerPanel(String text)
    {
        super();

        setLayout(new BorderLayout());
//        setPreferredSize(new Dimension(800, 1000));
        tab_button_panel = new TabButtonPanel();
//        GridBagConstraints tab_button_panel_constraints = new GridBagConstraints();
//        tab_button_panel_constraints.fill = GridBagConstraints.BOTH;
//        tab_button_panel_constraints.gridx = 0;
//        tab_button_panel_constraints.gridy = 0;
//        tab_button_panel_constraints.gridwidth = 3;
//        tab_button_panel_constraints.weighty = 1;
//        tab_button_panel_constraints.weightx = 0.0;
//        tab_button_panel_constraints.anchor = GridBagConstraints.LINE_START;
        add(tab_button_panel, BorderLayout.WEST);
        loadComponent();
        tab_button_panel.update_current_tab(text);
        setBackground(AppWindow.GLOBAL_BACKGROUND_COLOR);
    }

    private void loadComponent()
    {
        TabButton button1 = new TabButton(TabButtonPanel.tab_names[0], new Dimension(200, 200));
        tab_button_panel.addTabButton(button1);
        tab_button_panel.tab_panels.put(TabButtonPanel.tab_names[0], new Pair<>(button1,addTabContentPanel(new EmployeeManagerPanel(), TabButtonPanel.tab_names[0])));

        TabButton button2 = new TabButton(TabButtonPanel.tab_names[1], new Dimension(200, 200));
        tab_button_panel.addTabButton(button2);
        tab_button_panel.tab_panels.put(TabButtonPanel.tab_names[1], new Pair<>(button2, addTabContentPanel(new ProjectManagerPanel(), TabButtonPanel.tab_names[1])));

        TabButton button3 = new TabButton(TabButtonPanel.tab_names[2], new Dimension(200, 200));
        tab_button_panel.addTabButton(button3);
        tab_button_panel.tab_panels.put(TabButtonPanel.tab_names[2], new Pair<>(button3, addTabContentPanel(new NewWorkingPanel(), TabButtonPanel.tab_names[2])));

        TabButton button4 = new TabButton(TabButtonPanel.tab_names[3], new Dimension(200, 200));
        tab_button_panel.addTabButton(button4);
        tab_button_panel.tab_panels.put(TabButtonPanel.tab_names[3], new Pair<>(button4, addTabContentPanel(new SchedulePanel(), TabButtonPanel.tab_names[3])));

    }

    private JPanel addTabContentPanel(JPanel top_tab_panel, String tab_text)
    {
//        JPanel top_tab_panel = new JPanel();
//        JPanel top_tab_panel = new EmployeeManagerPanel();
//        top_tab_panel.setLayout(new FlowLayout());
        GridBagConstraints top_tab_panel_constraints = new GridBagConstraints();
        top_tab_panel_constraints.fill = GridBagConstraints.BOTH;
        top_tab_panel_constraints.gridx = 3;
        top_tab_panel_constraints.gridy = 0;
//        tab_button_panel_constraints.gridwidth = 3;
        top_tab_panel_constraints.weighty = 1;
        top_tab_panel_constraints.weightx = 1;
//        add(top_tab_panel, BorderLayout.CENTER);

        top_tab_panel.setPreferredSize(new Dimension(300, 800));
//        top_tab_panel.add(new JLabel(tab_text));
//        top_tab_panel.add(new EmployeeManagerPanel());
//        top_tab_panel.setVisible(false);
        return top_tab_panel;
    }
}
