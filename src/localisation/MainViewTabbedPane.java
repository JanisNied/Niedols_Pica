package localisation;

import java.awt.Graphics;

import javax.swing.JTabbedPane;

import com.formdev.flatlaf.FlatLightLaf;

import main.Settings;

@SuppressWarnings("serial")
public class MainViewTabbedPane extends JTabbedPane {
	private int tabamount;
	private String[] stringKeys;
	
	public MainViewTabbedPane(int tabamount, String[] stringKeys) {
		this.tabamount = tabamount;
		this.stringKeys = stringKeys;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateStrings();
	}
	
	private void updateStrings() {
		for (int i = 0; i < tabamount; i++)
			setTitleAt(i, Settings.lang.get(stringKeys[i]));
	}
}
