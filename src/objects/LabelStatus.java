package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

@SuppressWarnings("serial")
public class LabelStatus extends JLabel {
	private int type;
	private JLabel pwdsec;
	public LabelStatus(JLabel pwdsec) {
		this.pwdsec = pwdsec;
	}
	public void checkPassword(String password) {
        this.type = password.isEmpty() ? 0 : checkPasswordStrength(password);
        if (type == 0) {
        	pwdsec.setText("none");
        	pwdsec.setVisible(false);
        } else {
        	pwdsec.setVisible(true);
            if (type == 1) {
            	pwdsec.setText("Weak");
            } else if (type == 2) {
            	pwdsec.setText("Medium");
            } else {
            	pwdsec.setText("Strong");
            }
            pwdsec.setForeground(getStrengthColor(type));
        }
        repaint();
    }
	
	private Color getStrengthColor(int type) {
        if (type == 1) {
            return Color.decode("#FF4D4D");
        } else if (type == 2) {
            return Color.decode("#FFB04D");
        } else {
            return Color.decode("#58C359");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int size = (int) (height * 0.3f);
        Graphics2D g2 = (Graphics2D) g.create();
        FlatUIUtils.setRenderingHints(g2);
        int gap = UIScale.scale(5);
        int w = (width - gap * 2) / 3;
        int y = (height - size) / 2;
        Color disableColor = Color.decode(FlatLaf.isLafDark() ? "#404040" : "#CECECE");
        if (type >= 1) {
            g2.setColor(getStrengthColor(1));
        } else {
            g2.setColor(disableColor);
        }
        FlatUIUtils.paintComponentBackground(g2, 0, y, w, size, 0, 999);
        if (type >= 2) {
            g2.setColor(getStrengthColor(2));
        } else {
            g2.setColor(disableColor);
        }
        FlatUIUtils.paintComponentBackground(g2, w + gap, y, w, size, 0, 999);
        if (type >= 3) {
            g2.setColor(getStrengthColor(3));
        } else {
            g2.setColor(disableColor);
        }
        FlatUIUtils.paintComponentBackground(g2, (w + gap) * 2, y, w, size, 0, 999);
        g2.dispose();
    }
    public static int checkPasswordStrength(String password) {
        int score = 0;
        if (password.length() >= 8) {
            score++;
        }
        boolean hasUppercase = !password.equals(password.toLowerCase());
        if (hasUppercase) {
            score++;
        }
        boolean hasLowercase = !password.equals(password.toUpperCase());
        if (hasLowercase) {
            score++;
        }
        boolean hasDigit = password.matches(".*\\d.*");
        if (hasDigit) {
            score++;
        }
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9]*");
        if (hasSpecialChar) {
            score++;
        }
        if (score < 3) {
            return 1;
        } else if (score < 5) {
            return 2;
        } else {
            return 3;
        }
    }
}
