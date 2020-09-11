package gui.widgets;

import gui.main.AppWindow;

import javax.swing.*;
import java.awt.*;

public class AppMenuBar extends JMenuBar {

    public static Color BACKGROUND_COLOR = new Color(52, 51, 53);

    JMenu menu_file = new JMenu("File");
    JMenu menu_help = new JMenu("Help");

    public AppMenuBar()
    {
        super();
        add(menu_file);
        menu_file.add(new JMenuItem("New"));
        menu_file.add(new JSeparator());
        menu_file.add(new JMenuItem("Import"));
        menu_file.add(new JSeparator());
        menu_file.add(new JMenuItem("Export"));
        menu_file.add(new JSeparator());
        menu_file.add(new JMenuItem("Quit"));

        add(menu_help);
        menu_help.add(new JMenuItem("About"));

        graphic_setup();
    }

    private void graphic_setup()
    {
        setBorderPainted(false);
        setForeground(new Color(0xFFFFFF));
        setBackground(BACKGROUND_COLOR);
        for (int i = 0; i < getMenuCount(); i++)
        {
            JMenu menu = getMenu(i);
            menu.setFont(new Font("Segoe UI", 0, 20));
            menu.setContentAreaFilled(false);
            menu.setBorderPainted(false);
            menu.setForeground(new Color(0xFFFFFF));
            menu.setBackground(BACKGROUND_COLOR);

            for (int j = 0; j < menu.getItemCount(); j++)
            {
                JComponent comp = menu.getItem(j);
                if(! (comp instanceof JMenuItem))
                    continue;
                JMenuItem item = (JMenuItem)comp;
                item.setContentAreaFilled(false);
                item.setBorderPainted(false);
                item.setBorderPainted(false);
                item.setFont(new Font("Segoe UI", 0, 20));
                item.setForeground(new Color(0xFFFFFF));
                item.setBackground(BACKGROUND_COLOR);

            }

        }
    }

}
