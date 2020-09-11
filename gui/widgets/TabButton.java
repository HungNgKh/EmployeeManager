package gui.widgets;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TabButton extends ColorTransitionButton {

    public static Color MOUSE_IN = new Color(78, 130, 123);
    public static Color MOUSE_OUT = new Color(18,41,53);
//    public static Color MOUSE_OUT = new Color(46, 72, 70);
//    public static Color MOUSE_IN = new Color(99, 146, 144);
    public static Color ON_SELECT = new Color(255, 165, 47);

    public TabButton(String text, Dimension size)
    {
        super(text);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
//        setFont(new Font());
        setPreferredSize(size);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(new Color(0xFFFFFF));
        setBackground(TabButton.MOUSE_OUT);
        Config.setIcon(this,"gui.assest/Employees.png");
    }

    public void onSelected()
    {
        animator.stop();
        setBackground(ON_SELECT);
        removeMouseListener(mouseListener);
    }

    public void offSelected()
    {
        addMouseListener(mouseListener);
        color_change(MOUSE_OUT);
    }


}
