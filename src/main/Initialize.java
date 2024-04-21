package main;
import java.awt.EventQueue;
import java.io.File;
import states.LoginWindow;
import states.WindowHandler;

public class Initialize {
	// File Variables
	public static void main(String[] args) {
		JSON.setupFiles(Global.persistenceLocation, Global.settings);
		Settings.currentSettings = JSON.jsonToHashMap(Global.settings, "CURRENT SETTINGS");
		Settings.lang = JSON.jsonToHashMap(new File(System.getProperty("user.dir")+Global.fileSeparator+"locales"+Global.fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
		Global.reloadLAF();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Global.frame = new WindowHandler(new LoginWindow());
					Global.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("[FRAME] Frame failed to load! Stack Trace: ");
					e.printStackTrace();
				}
			}
		});
	}
}
