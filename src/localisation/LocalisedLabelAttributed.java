package localisation;

import java.awt.Graphics;

import javax.swing.JLabel;

import main.Settings;

@SuppressWarnings("serial")
public class LocalisedLabelAttributed extends JLabel {
	private String mapKey, attribute;
	
	public LocalisedLabelAttributed(String mapKey, String attribute) {
		this.mapKey = mapKey;
		this.attribute = attribute;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setText(Settings.lang.get(mapKey)+attribute);
	}
}
