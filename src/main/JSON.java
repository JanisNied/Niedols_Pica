package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import db.Database;

@SuppressWarnings("unchecked")
public class JSON {
	private static HashMap <String, String> defaultSettings;
	
	// Initialize HashMap
	static {
		defaultSettings = new HashMap<>();
		defaultSettings.put("lang", "lv");
		defaultSettings.put("theme", "light");
		System.out.println("[DEFAULT SETTINGS] "+defaultSettings);
    }
	
	// Initialize non-existent Files/Directories, set default settings for persistence
	public static void setupFiles(File location, File json, File db) {
		if (!location.exists() || !json.exists() || !db.exists()) {
			try {
				if (!location.exists())
					location.mkdirs();
				if (!json.exists())
					json.createNewFile();
				if (!db.exists()) {
					db.createNewFile();
					Database.initializeDB(db);
				}
				defaultData(json);
				System.out.println("[PERSISTENCE] File created!");
			} catch (IOException e) {
				System.out.println("[PERSISTENCE] File and/or directory failed to be created! Stack Trace: ");
				e.printStackTrace();
			}
		}
	}	
	private static void defaultData(File json) {
		JSONObject obj = new JSONObject();	
		try {
			for (String key : defaultSettings.keySet()) {
				obj.put(key, defaultSettings.get(key));
			}
			FileWriter file = new FileWriter(json);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read/Write to JSON
	public static void writeData(String key, String newValue, File json) {
		try {
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse(new FileReader(json));
	        JSONObject jsonObject = (JSONObject)obj;
	        jsonObject.put(key, newValue);
			FileWriter file = new FileWriter(json);
			file.write(jsonObject.toJSONString());
			file.flush();
			file.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	public static String readData(File json, String key) {
		JSONParser parser = new JSONParser();
		String value = "empty";
		try {
	         Object obj = parser.parse(new FileReader(json));
	         JSONObject jsonObject = (JSONObject)obj;
	         value = (String) jsonObject.get(key);
	         System.out.print("[JSON KEY: "+key+"] VALUE: "+value);
	       
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		return value;
	}
	
	// Conversion to the HashMap Data Structure
	public static HashMap<String, String> jsonToHashMap(File json, String what){
		HashMap<String, String> map = new HashMap<>();
		JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(json));
            for (Object key : jsonObject.keySet()) {
   	         	String keyStr = (String) key;
   	         	String keyValue = (String)jsonObject.get(keyStr);
   	         	map.put(keyStr, keyValue);
   	     	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("["+what+"] "+map);
	    return map;
	}
	
}
