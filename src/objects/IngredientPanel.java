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
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Settings;

@SuppressWarnings("serial")
public class IngredientPanel extends JPanel {
	private JLabel lblNewLabel;
	private String name, classification, displayString = "";
	private ImageIcon displayIcon;
	private int bordersize;
	private Color bglight, bgdark, borderlight, borderdark;
	private boolean enabled;
	private Runnable disableOthers, info, infooff, add, remove;
	private boolean action = false, selected = false, toggle = false;
	
	/**
	 * @wbp.parser.constructor
	 */
	public IngredientPanel(String name, String classification, String displayString, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers, Runnable info, Runnable infooff) {
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
	public IngredientPanel(String name, String classification, ImageIcon displayIcon, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers, Runnable info, Runnable infooff) {
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
	public IngredientPanel(String name, String classification, String displayString, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers) {
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
		action = true;
		init();
	}
	public IngredientPanel(String name, String classification, ImageIcon displayIcon, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable disableOthers) {
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
		action = true;
		init();
	}
	public IngredientPanel(String name, String classification, ImageIcon displayIcon, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, boolean enabled, Runnable info, Runnable infoOff, Runnable add, Runnable remove) {
		this.name = name;
		this.classification = classification;
		this.displayIcon = displayIcon;
		this.bglight = bglight;
		this.bgdark = bgdark;
		this.borderlight = borderlight;
		this.borderdark = borderdark;
		this.bordersize = bordersize;
		this.enabled = enabled;
		this.info = info;
		this.infooff = infoOff;
		this.add = add;
		this.remove = remove;
		action = false;
		toggle = true;
		init();
	}
	private void init() {
		DecimalFormat df = new DecimalFormat("0.00");
		setLayout(null);
        setPreferredSize(new Dimension(67, 60));
        setOpaque(false);
        setName(name);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {          
            	if (toggle) {
            		enableIngredient(!enabled);
            		if (enabled)
            			add.run();
            		else
            			remove.run();
            	} else {	
            		enableIngredient(true);
            		disableOthers.run();
            	}
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	selected = true;
            	repaint();
            	if (info != null)
            		info.run();
            	if (action) {
            		lblNewLabel.setIcon(null);
            		if (classification.equalsIgnoreCase("dough"))
            			lblNewLabel.setText("€"+df.format(Settings.prices.get(name)));
            		else	
            			lblNewLabel.setText("€"+df.format(Settings.prices.get(name.subSequence(0, 2))));
            	}
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	selected = false;
            	repaint();
            	if (infooff != null)
            		infooff.run();
            	if (action) {
            		if (displayIcon != null)
            			lblNewLabel.setIcon(new ImageIcon(displayIcon.getImage().getScaledInstance(54, 54, java.awt.Image.SCALE_SMOOTH)));
            		lblNewLabel.setText(displayString);
            	}
            }
        });
        
        lblNewLabel = new JLabel(displayString);
        if (displayIcon != null)
        	lblNewLabel.setIcon(new ImageIcon(displayIcon.getImage().getScaledInstance(54, 54, java.awt.Image.SCALE_SMOOTH)));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 67, 60);
        add(lblNewLabel);
        if (classification == "veggies") {
        	setPreferredSize(new Dimension(67, 58));
        	lblNewLabel.setBounds(0, 0, 65, 58);
        }
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
        if (selected) {
        	g2d.setColor(new Color(13, 162, 255));
        	if (toggle && enabled) {
        		g2d.setColor(new Color(252, 11, 3));
        	}
        } else if (enabled)
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
	public void setAction(boolean action) {
		this.action = action;
	}
}
