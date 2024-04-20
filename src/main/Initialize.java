package main;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import states.LoginWindow;

public class Initialize {
	// File Variables
	private static String fileSeparator = System.getProperty("file.separator");
	private static File persistenceLocation = new File(System.getProperty("user.home") + fileSeparator + ".pizzeriaSettings");
	private static File settings = new File(persistenceLocation + fileSeparator + "settings.json");
	
	public static void main(String[] args) {
		JSON.setupFiles(persistenceLocation, settings);
		Settings.currentSettings = JSON.jsonToHashMap(settings, "CURRENT SETTINGS");
		Settings.lang = JSON.jsonToHashMap(new File(System.getProperty("user.dir")+fileSeparator+"locales"+fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
		reloadLAF();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("[FRAME] Frame failed to load! Stack Trace: ");
					e.printStackTrace();
				}
			}
		});
	}
	private static void reloadLAF() {
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				try {
				    UIManager.setLookAndFeel(new FlatLightLaf());
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize LaF" );
				}
				break;
			case "dark":
				try {
				    UIManager.setLookAndFeel(new FlatDarkLaf());
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize LaF" );
				}
				break;			
		}
		try {
		    UIManager.put("TextComponent.arc", 30);
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
	}
}
