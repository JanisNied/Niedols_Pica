package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import raven.toast.ToastClientProperties;
import states.WindowHandler;
import themes.DarkTheme;
import themes.LightTheme;

public class Global {
	public static WindowHandler frame;
	public static String fileSeparator = System.getProperty("file.separator");
	public static File persistenceLocation = new File(System.getProperty("user.home") + fileSeparator + ".pizzeria/SliceHaven");
	public static File settings = new File(persistenceLocation + fileSeparator + "settings.json");
	public static File database = new File(persistenceLocation + fileSeparator + "data.db");
	public static File locales = new File(persistenceLocation + fileSeparator + "/locales");
	public static File routing = new File(persistenceLocation + fileSeparator + "/routing");
	public static File routingcache = new File(routing + fileSeparator + "routingcache");
	public static File eng = new File(locales + fileSeparator + "eng.json");
	public static File lv = new File(locales + fileSeparator + "lv.json");
	public static File pbf = new File(routing + fileSeparator + "latvia.osm.pbf");
	public static HashMap <String, String> sounds;
	static {
		sounds = new HashMap<>();
		sounds.put("err", "/sounds/NewProfitQuota.wav");
		sounds.put("success", "/sounds/accountmade.wav");
    }
	
	public static String user = "John Doe";
	
	public static void setup() {
		JSON.setupFiles(Global.persistenceLocation, Global.settings, Global.database, Global.lv, Global.eng, Global.locales, Global.pbf, Global.routing, Global.routingcache);
		Settings.currentSettings = JSON.jsonToHashMap(Global.settings, "CURRENT SETTINGS");
		Settings.lang = JSON.jsonToHashMap(new File(persistenceLocation+Global.fileSeparator+"locales"+Global.fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
		reloadLAF();
		if (frame != null)
			centerFrameOnScreen(frame);
	}
	
	public static void reloadLAF() {
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				 EventQueue.invokeLater(() -> {
	                    FlatAnimatedLafChange.showSnapshot();
	                    LightTheme.setup();
	                    FlatLaf.updateUI();
	                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
	            });
				break;
			case "dark":
				EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    DarkTheme.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
				});
				break;			
		}
		try {
		    UIManager.put("TextComponent.arc", 30);
		    UIManager.put("Button.arc", 15);
		    UIManager.put("ComboBox.arc", 30);
		    UIManager.put( "TabbedPane.showTabSeparators", true );
		    UIManager.put("TabbedPane.minimumTabWidth", 100);
			UIManager.put("TabbedPane.tabAreaAlignment", "center");
			UIManager.put("Component.arc", 15);
			UIManager.put("ScrollBar.trackArc", 20);
			UIManager.put("ScrollBar.thumbArc", 20);
			UIManager.put("ScrollBar.thumb", new Color(84, 158, 232));
			UIManager.put("ScrollPane.arc", 20);
			UIManager.put("Button.focusedBorderColor", new Color(0,0,0,0));
			UIManager.put(ToastClientProperties.TOAST_ERROR_ICON, new FlatSVGIcon(Global.class.getResource("/img/download(1).svg")));
			UIManager.put("Toast.showCloseButton", false);
			switch (Settings.currentSettings.get("theme")) {
				case "dark":
					UIManager.put("Toast.background", new Color(90, 90, 90));
					break;
				case "light":
					UIManager.put("Toast.background", new Color(230, 230, 230));
					break;
			}
			UIManager.put("Toast.limit", 3);
			UIManager.put("Toast.outlineWidth", 1);
			UIManager.put("Toast.error.outlineColor", new Color(255,0,0));
		    if (frame != null)
		    	SwingUtilities.updateComponentTreeUI(frame);
		} catch(Exception ex) {
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
		Settings.lang = JSON.jsonToHashMap(new File(persistenceLocation+Global.fileSeparator+"locales"+Global.fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
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
	public static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
	
	public static void periodicUpdates() {
		// using later
		
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Task executed at: " + System.currentTimeMillis());
        }, 0, 5, TimeUnit.SECONDS);
	}
}
