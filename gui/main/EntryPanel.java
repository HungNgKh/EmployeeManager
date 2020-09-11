package gui.main;

import gui.widgets.TabButton;
import gui.widgets.TabButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EntryPanel extends JPanel implements ActionListener{
    ArrayList<JButton> tab_button_list = new ArrayList<>();
    private JFrame app_window;

    public EntryPanel() {
        this.setLayout(new GridBagLayout());
//        setBackground(new Color());

        addTabButton(new gui.widgets.TabButton(TabButtonPanel.tab_names[0], new Dimension(200, 200)));
        addTabButton(new gui.widgets.TabButton(TabButtonPanel.tab_names[1], new Dimension(200, 200)));
        addTabButton(new gui.widgets.TabButton(TabButtonPanel.tab_names[2], new Dimension(200, 200)));
        addTabButton(new gui.widgets.TabButton(TabButtonPanel.tab_names[3], new Dimension(200, 200)));
        setBackground(AppWindow.GLOBAL_BACKGROUND_COLOR);
//        panel.setMinimumSize(new Dimension(800, 400));
//        panel.setPreferredSize(new Dimension(800, 400));
    }

    private void addTabButton(JButton button){
        int pos = tab_button_list.size();
        tab_button_list.add(button);
//        button.setBorderPainted(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = pos;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(20, 10, 20, 10);
        add(button, constraints);
        app_window = (JFrame) SwingUtilities.getWindowAncestor(this);
        button.addActionListener(this);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton obj = (JButton) e.getSource();

        JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
        parent.remove(this);
        parent.setContentPane(new ManagerPanel(obj.getText()));
        parent.pack();


//        parent.setSize(AppWindow.app_size);
    }
}
