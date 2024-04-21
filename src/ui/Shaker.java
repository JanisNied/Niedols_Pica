package ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Shaker {

    private final JLabel label;
    private final Timer shakeTimer;
    private final Point originalLocation;
    private static final int shakeDuration = 500;
    private static final int shakeMagnitude = 5;
    private int shakeCooldown;
    
    private static Map<JLabel, Long> lastShakeTimes = new HashMap<>();

    public Shaker(JLabel label, int shakeCooldown) {
        this.label = label;
        this.shakeCooldown = shakeCooldown;
        this.originalLocation = label.getLocation();
        this.shakeTimer = new Timer(10, new ActionListener() {
            private long startTime = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                } else {
                    long elapsedTime = System.currentTimeMillis() - startTime;

                    if (elapsedTime < shakeDuration) {
                        int offsetX = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;
                        int offsetY = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;

                        label.setLocation(originalLocation.x + offsetX, originalLocation.y + offsetY);                     
                    } else {
                        stopShaking();
                    }
                }
            }
        });
    }

    public void startShaking() {
        long currentTime = System.currentTimeMillis();
        if (!shakeTimer.isRunning() && canShake(currentTime)) {
            shakeTimer.start();
            lastShakeTimes.put(label, currentTime);
        }
    }

    private boolean canShake(long currentTime) {
        if (lastShakeTimes.containsKey(label)) {
            long lastShakeTime = lastShakeTimes.get(label);
            return (currentTime - lastShakeTime) >= shakeCooldown;
        }
        return true;
    }

    private void stopShaking() {
        if (shakeTimer.isRunning()) {
            shakeTimer.stop();
            label.setLocation(originalLocation);        
        }
    }
}
