package gui.widgets;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorTransitionButton extends JButton {
    public static Font DEFAULT_FONT = new Font("Tahoma", Font.BOLD, 15);

    protected MouseListener mouseListener;
    protected ColorTransitionAnimator animator;


    public ColorTransitionButton(String text)
    {
        super(text);
//        setFont(new Font());
        setFont(DEFAULT_FONT);
        setFocusPainted(false);
        setForeground(new Color(0xFFFFFF));
        setBackground(TabButton.MOUSE_OUT);
        animator = new ColorTransitionAnimator(Config.FRAME_REFRESH_TIME);
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

//                setContentAreaFilled(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                color_change(TabButton.MOUSE_IN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                color_change(TabButton.MOUSE_OUT);
            }
        };
        addMouseListener(mouseListener);
    }


    protected void color_change(Color dst)
    {
        animator.stop();
        animator.setTransition(getBackground(), dst, 5);
        animator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackground(animator.getColor());
            }
        });
        animator.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        ButtonModel model = getModel();
        if(model.isPressed())
            g.setColor(TabButton.ON_SELECT);
        else
            g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


}
