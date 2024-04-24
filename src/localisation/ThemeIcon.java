package localisation;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Settings;

@SuppressWarnings("serial")
public class ThemeIcon extends JLabel {
	private ImageIcon pathL, pathD;
	
	
	
	public ThemeIcon(ImageIcon pathL, ImageIcon pathD) {
		this.pathL = pathL;
		this.pathD = pathD;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				setIcon(pathL);
				break;
			case "dark":
				setIcon(pathD);
				break;
			default:
				System.out.println("[THEMEICON] Invalid theme!");
		}
	}
}
