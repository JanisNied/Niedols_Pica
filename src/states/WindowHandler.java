package states;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import main.Settings;

public class WindowHandler extends JFrame {
	private static final long serialVersionUID = -4846037374513777426L;
	public WindowHandler(JPanel contentPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setContentPane(contentPane);
		reloadLAF();
	}
	void reloadLAF() {
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				try {
				    UIManager.setLookAndFeel(new FlatLightLaf());			
				} catch( Exception ex ) {
				    System.err.println("[LAF] Failed to initialize Light LaF, Stack Trace: ");
				    ex.printStackTrace();
				}
				break;
			case "dark":
				try {
				    UIManager.setLookAndFeel(new FlatDarkLaf());
				    UIManager.put("Label.foreground", Color.WHITE);
				} catch( Exception ex ) {
					 System.err.println("[LAF] Failed to initialize Dark LaF, Stack Trace: ");
					 ex.printStackTrace();
				}
				break;			
		}
		try {
		    UIManager.put("TextComponent.arc", 30);
		    if (this != null)
		    	SwingUtilities.updateComponentTreeUI(this);
		} catch( Exception ex ) {
			 System.err.println("[LAF] Failed to initialize LaF, Stack Trace: ");
			 ex.printStackTrace();
		}
	}
}
