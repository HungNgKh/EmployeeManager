package gui.widgets;

import gui.main.ManagerPanel;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TabButtonPanel extends JPanel{

    public static String[] tab_names = new String[]{
            "NHÂN VIÊN",
            "DỰ ÁN",
            "PHÂN CÔNG",
            "LỊCH LÀM VIỆC"
    };

    ArrayList<TabButton> tab_button_list = new ArrayList<>();
    public HashMap<String, Pair<TabButton, JPanel>> tab_panels = new HashMap<>();
    String current_tab;

    public TabButtonPanel()
    {
        super();
//        setBorder(new LineBorder(new Color(0, 0, 255)));
        setLayout(new GridLayout(4, 0, 0, 0));
//        addTabButton(new TabButton("North", new Dimension(100, 200)));
//        addTabButton(new TabButton("West", new Dimension(100, 200)));
//        addTabButton(new TabButton("South", new Dimension(100, 200)));
//        addTabButton(new TabButton("East", new Dimension(100, 200)));
    }

    public void addTabButton(TabButton button){
        int pos = tab_button_list.size();
        tab_button_list.add(button);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.BOTH;
//        constraints.gridx = 0;
//        constraints.gridy = pos;
//        constraints.weightx = 1.0;
//        constraints.weighty = 1.0;
//        constraints.insets = new Insets(5, 5, 5, 5);

        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                update_current_tab(button.getText());
            }
        });
    }



    public void update_current_tab(String text)
    {
        if(text.equals(current_tab))
            return;

        ManagerPanel parent = (ManagerPanel)getParent();

        if(((BorderLayout)parent.getLayout()).getLayoutComponent(BorderLayout.CENTER) != null )
            parent.remove(((BorderLayout)parent.getLayout()).getLayoutComponent(BorderLayout.CENTER));

        if(current_tab != null)
            tab_panels.get(current_tab).getKey().offSelected();

        current_tab = text;
        tab_panels.get(current_tab).getKey().onSelected();
        parent.add(tab_panels.get(current_tab).getValue(), BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
    }


}
