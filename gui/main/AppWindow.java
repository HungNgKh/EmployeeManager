package gui.main;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame{

    public static Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension app_size = new Dimension((int)(screen_size.width * 0.8), (int)(screen_size.height * 0.8));
    public static Color GLOBAL_BACKGROUND_COLOR = new Color(5, 25, 19);

    public AppWindow()
    {
        super();
        setPreferredSize(app_size);
    }

    public static void main(String[] args)
    {
        JFrame frame = new AppWindow();
        frame.setContentPane(new EntryPanel());
        frame.setJMenuBar(new gui.widgets.AppMenuBar());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        frame.setResizable(false);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
