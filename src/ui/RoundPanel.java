package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {

    private int cornerRadius;
    private Color bg;
    private Color currentBorderColor;

    public RoundPanel(int cornerRadius, Color bg, Color border) {
        this.cornerRadius = cornerRadius;
        this.bg = bg;
        this.currentBorderColor = border;
        setOpaque(false);
    }

    public void setBorderColor(Color color) {
        currentBorderColor = color;
        repaint(); 
    }
    
    public void setBackground(Color color) {
    	bg = color;
    	repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(bg);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2d);
        g2d.setColor(currentBorderColor);
        g2d.draw(shape);

        g2d.dispose();
    }
}
