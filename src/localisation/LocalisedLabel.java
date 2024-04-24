package localisation;

import java.awt.Graphics;

import javax.swing.JLabel;

import main.Settings;

@SuppressWarnings("serial")
public class LocalisedLabel extends JLabel {
	private String mapKey;
	
	public LocalisedLabel(String mapKey) {
		this.mapKey = mapKey;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setText(Settings.lang.get(mapKey));
	}
}
