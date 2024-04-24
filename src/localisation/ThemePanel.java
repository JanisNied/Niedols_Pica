package localisation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Settings;

@SuppressWarnings("serial")
public class ThemePanel extends JPanel {
	
	private Color light, dark;
	
	public ThemePanel(Color light, Color dark) {
		this.light = light;
		this.dark = dark;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			setBackground(light);
			break;
		case "dark":
			setBackground(dark);
			break;		
		}
	}
}
