package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AnimatePanelY extends JPanel {
	private static final long serialVersionUID = 7629316931929942189L;
	private JPanel panel;
    private int startY;
    private int endY;
    private Timer timer;

    public AnimatePanelY(JPanel panel, int startY, int endY, int duration) {
        this.panel = panel;
        this.startY = startY;
        this.endY = endY;

        timer = new Timer(16, new ActionListener() { 
            private long startTime = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }

                long elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime < duration) {
                    double t = (double) elapsedTime / duration;
                    animatePanel(t);
                } else {
                    animatePanel(1.0); 
                    timer.stop();
                }
            }
        });
    }

    public void startAnimation() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    private void animatePanel(double t) {
        double interpolation = easeOutSine(t);
        int currentY = (int) (startY + interpolation * (endY - startY));
        panel.setLocation(panel.getX(), currentY);
        panel.repaint();
        panel.getParent().repaint();
    }

    private double easeOutSine(double x) {
    	return Math.sin((x * Math.PI) / 2);
    }
}


