package localisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import main.Settings;

@SuppressWarnings("serial")
public class ThemeRoundPanel extends JPanel {
    private int cornerRadius;
    private Color bglight, bgdark;
    private Color borderlight, borderdark;

    public ThemeRoundPanel(int cornerRadius, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
        this.cornerRadius = cornerRadius;
        this.bglight = bglight;
        this.bgdark = bgdark;
        this.borderdark = borderdark;
        this.borderlight = borderlight;
        setOpaque(false);
    }
	
    @Override
    protected void paintComponent(Graphics g) {
    	Color bg = bglight;
    	Color currentBorderColor = borderlight;
    	if (Settings.currentSettings.get("theme").equals("dark")) {
    		bg = bgdark;
    		currentBorderColor = borderdark;
    	}
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
