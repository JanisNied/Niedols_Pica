package main;

import java.awt.EventQueue;
import java.io.File;
import states.LoginWindow;

public class Initialize {
	// File Variables
	private static String fileSeparator = System.getProperty("file.separator");
	private static File persistenceLocation = new File(System.getProperty("user.home") + fileSeparator + ".pizzeriaSettings");
	private static File settings = new File(persistenceLocation + fileSeparator + "settings.json");
	
	public static void main(String[] args) {
		JSON.setupFiles(persistenceLocation, settings);
		Settings.currentSettings = JSON.jsonToHashMap(settings, "CURRENT SETTINGS");
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
}
