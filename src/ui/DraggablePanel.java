package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DraggablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int mouseX, mouseY;

    public DraggablePanel() {
        setPreferredSize(new Dimension(400, 300));
        setBackground(new Color(0,0,0,0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - mouseX;
                int deltaY = e.getY() - mouseY;

                Window window = SwingUtilities.getWindowAncestor(DraggablePanel.this);
                Point location = window.getLocation();
                window.setLocation(location.x + deltaX, location.y + deltaY);
            }
        });
    }
}