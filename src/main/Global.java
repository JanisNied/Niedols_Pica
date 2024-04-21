package main;


import java.awt.Insets;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import states.WindowHandler;
import themes.DarkTheme;
import themes.LightTheme;

public class Global {
	public static WindowHandler frame;
	public static String fileSeparator = System.getProperty("file.separator");
	public static File persistenceLocation = new File(System.getProperty("user.home") + fileSeparator + ".pizzeria/SliceHaven");
	public static File settings = new File(persistenceLocation + fileSeparator + "settings.json");
	public static File database = new File(persistenceLocation + fileSeparator + "users.db");
	public static HashMap <String, String> sounds;
	static {
		sounds = new HashMap<>();
		sounds.put("err", "/sounds/NewProfitQuota.wav");
		sounds.put("success", "/sounds/accountmade.wav");
    }
	
	public static String user = "John Doe";
	
	public static void reloadLAF() {
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				LightTheme.setup();
				break;
			case "dark":
				DarkTheme.setup();
				break;			
		}
		try {
		    UIManager.put("TextComponent.arc", 30);
		    UIManager.put("Button.arc", 15);
		    UIManager.put("ComboBox.arc", 30);
		    UIManager.put( "TabbedPane.showTabSeparators", true );
		    UIManager.put("TabbedPane.minimumTabWidth", 100);
			UIManager.put("TabbedPane.tabAreaAlignment", "center");
		    if (frame != null)
		    	SwingUtilities.updateComponentTreeUI(frame);
		} catch( Exception ex ) {
			 System.err.println("[LAF] Failed to initialize LaF, Stack Trace: ");
			 ex.printStackTrace();
		}
	}
	public static void themeSwitcher() {
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				Settings.currentSettings.put("theme", "dark");
				JSON.writeData("theme", "dark", settings);
				break;	
			case "dark":
				Settings.currentSettings.put("theme", "light");
				JSON.writeData("theme", "light", settings);
				break;
		}
	}
	
	public static void changeLanguage(String lang) {
		Settings.currentSettings.put("lang", lang);
		JSON.writeData("lang", lang, settings);
		Settings.lang = JSON.jsonToHashMap(new File(System.getProperty("user.dir")+Global.fileSeparator+"locales"+Global.fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
	}
	public static String removeExtension(String s) {
	    String separator = System.getProperty("file.separator");
	    String filename;
	    int lastSeparatorIndex = s.lastIndexOf(separator);
	    if (lastSeparatorIndex == -1) {
	        filename = s;
	    } else {
	        filename = s.substring(lastSeparatorIndex + 1);
	    }
	    int extensionIndex = filename.lastIndexOf(".");
	    if (extensionIndex == -1)
	        return filename;
	    return filename.substring(0, extensionIndex);
	}
	public static void removeMLs(JComponent component) {
        for (MouseListener listener : component.getMouseListeners()) {
                component.removeMouseListener(listener);
        }
    }
}
