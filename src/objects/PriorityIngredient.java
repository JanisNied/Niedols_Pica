package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Settings;

@SuppressWarnings("serial")
public class PriorityIngredient extends JPanel {
	private String name, classification, displayString = "";
	private ImageIcon displayIcon;
	private int bordersize;
	private Color bglight, bgdark, borderlight, borderdark;
	private boolean enabled;
	private Runnable disableOthers, info, infooff;
	
	/**
	 * @wbp.parser.constructor
	 */
	public PriorityIngredient(String name, String classification, String displayString, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers, Runnable info, Runnable infooff) {
		this.name = name;
		this.classification = classification;
		this.displayString = displayString;
		this.bglight = bglight;
		this.bgdark = bgdark;
		this.borderlight = borderlight;
		this.borderdark = borderdark;
		this.bordersize = bordersize;
		this.enabled = enabled;
		this.disableOthers = disableOthers;
		this.info = info;
		this.infooff = infooff;
		init();
	}
	
	public PriorityIngredient(String name, String classification, ImageIcon displayIcon, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers, Runnable info, Runnable infooff) {
		this.name = name;
		this.classification = classification;
		this.displayIcon = displayIcon;
		this.bglight = bglight;
		this.bgdark = bgdark;
		this.borderlight = borderlight;
		this.borderdark = borderdark;
		this.bordersize = bordersize;
		this.enabled = enabled;
		this.disableOthers = disableOthers;
		this.info = info;
		this.infooff = infooff;
		init();
	}
	private void init() {
		setLayout(null);
        setPreferredSize(new Dimension(67, 60));
        setOpaque(false);
        setName(name);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	enableIngredient(true);
            	disableOthers.run();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	info.run();
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	infooff.run();
            }
        });
        
        JLabel lblNewLabel = new JLabel(displayString);
        if (displayIcon != null)
        	lblNewLabel.setIcon(new ImageIcon(displayIcon.getImage().getScaledInstance(54, 54, java.awt.Image.SCALE_SMOOTH)));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 67, 60);
        add(lblNewLabel);
	}
	
	public void enableIngredient(boolean enabled) {
		this.enabled = enabled;
		repaint();
	}
	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayString() {
		return displayString;
	}

	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}

	public ImageIcon getDisplayIcon() {
		return displayIcon;
	}

	public void setDisplayIcon(ImageIcon displayIcon) {
		this.displayIcon = displayIcon;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		Color[] theme = theme();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(theme[0]);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, bordersize, bordersize);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, bordersize, bordersize);
        super.paintComponent(g2d);
        if (enabled)
        	g2d.setColor(new Color(87, 245, 39));
        else
        	g2d.setColor(theme[1]);
        g2d.draw(shape);

        g2d.dispose();
    }	
	private Color[] theme() {
		Color[] array = new Color[2];
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				array[0] = bglight;
				array[1] = borderlight;
				break;
			case "dark":
				array[0] = bgdark;
				array[1] = borderdark;
				break;
		}
		return array;
	}
}
