package localisation;

import java.awt.Graphics;

import javax.swing.JButton;

import main.Settings;

@SuppressWarnings("serial")
public class LocalisedButton extends JButton {
	private String mapKey;
	private Runnable method;
	
	public LocalisedButton(String mapKey, Runnable method) {
		this.mapKey = mapKey;
		this.method = method;
		addActionListener(e -> this.method.run());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setText(Settings.lang.get(mapKey));
	}
}
