package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {

    private int cornerRadius;
    private Color normalBorderColor = Color.WHITE;
    private Color hoverBorderColor = new Color(173, 216, 230); 
    private Color currentBorderColor = normalBorderColor;

    public RoundPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorderColor(hoverBorderColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorderColor(normalBorderColor);
            }
        });
    }

    public void setBorderColor(Color color) {
        currentBorderColor = color;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2d);
        g2d.setColor(currentBorderColor);
        g2d.draw(shape);

        g2d.dispose();
    }
}
