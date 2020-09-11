package gui.widgets;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorTransitionAnimator extends Timer {

    private int step = 0;
    private int elapsed_step;

    private double current_r;
    private double current_g;
    private double current_b;

    private double dst_r;
    private double dst_g;
    private double dst_b;

    public ColorTransitionAnimator(int time) {
        super(time, null);
    }

    public Color getColor() {
        return new Color((int) current_r, (int) current_g, (int) current_b);
    }

    void setTransition(Color src, Color dst, int frames) {
        for(ActionListener i : getActionListeners())
        {
            removeActionListener(i);
        }

        step = frames;
        elapsed_step = 0;

        int src_color = src.getRGB();
        int dst_color = dst.getRGB();

        current_r = (src_color & 0xFF0000) >> 16;
        current_g = (double)((src_color & 0xFF00) >> 8 );
        current_b = (double)(src_color & 0xFF);

        dst_r = (dst_color & 0xFF0000) >> 16;
        dst_g = (dst_color & 0xFF00) >> 8;
        dst_b = dst_color & 0xFF;

        double velocity_r = (dst_r - current_r) / step;
        double velocity_g = (dst_g - current_g) / step;
        double velocity_b = (dst_b - current_b) / step;

//        setInitialDelay(0);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elapsed_step++ >= step)
                {
                    stop();
                    current_r = dst_r;
                    current_g = dst_g;
                    current_b = dst_b;
                }
                else
                {
                    current_r += velocity_r ;
                    current_b += velocity_b;
                    current_g += velocity_g;
                }
            }
        });
    }


    public static void main(String[] args)
    {
        ColorTransitionAnimator animator = new ColorTransitionAnimator(Config.FRAME_REFRESH_TIME);
        animator.setTransition(new Color(0xFFFFF), new Color(0xDDDDDD), 20);
        animator.start();
        try {
            Thread.sleep(10000);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
