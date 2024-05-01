package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	public static void setupFiles(File location, File json, File db, File lv, File eng, File locales, File pbf, File routing, File routingcache) {
		if (!location.exists() || !json.exists() || !db.exists() || !lv.exists() || !eng.exists() || !locales.exists() || !pbf.exists() || !routingcache.exists()) {
			try {
				if (!location.exists())
					location.mkdirs();
				if (!routing.exists())
					routing.mkdirs();
				if (!routingcache.exists()) {
					routingcache.mkdirs();
					List<String> fileNames = Arrays.asList("edgekv_keys", "edgekv_vals", "edges", "geometry","location_index","nodes","nodes_ch_car","properties","properties.txt","shortcuts_car");
					for (String file : fileNames) {
						InputStream inputStream1 = JSON.class.getResourceAsStream("/routingcache/routing-graph-cache/"+file);
						Path destinationFilePath1 = Global.routingcache.toPath().resolve(file);
						try {
				            Files.copy(inputStream1, destinationFilePath1, StandardCopyOption.REPLACE_EXISTING);
				            System.out.println("[CACHE] File copied successfully!");
				        } catch (IOException e) {
				            e.printStackTrace();
				        } finally {
				            if (inputStream1 != null) {
				                try {
				                    inputStream1.close();
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }
				            }
				        }
					}
				}
				if (!locales.exists())
					locales.mkdirs();
				if (!json.exists())
					json.createNewFile();
				if (!db.exists()) {
					db.createNewFile();
					Database.initializeDB(db);
				}
				if(!pbf.exists()) {
					InputStream inputStream = JSON.class.getResourceAsStream("/osm/latvia.osm.pbf");
					Path destinationFilePath = Global.routing.toPath().resolve("latvia.osm.pbf");
					try {
			            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			            System.out.println("[OSM] File copied successfully!");
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally {
			            if (inputStream != null) {
			                try {
			                    inputStream.close();
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
					
				}
				if(!eng.exists()) {
					InputStream inputStream = JSON.class.getResourceAsStream("/locales/eng.json");
					Path destinationFilePath = new File(Global.persistenceLocation+"/locales").toPath().resolve("eng.json");
					try {
			            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			            System.out.println("[ENG] File copied successfully!");
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally {
			            if (inputStream != null) {
			                try {
			                    inputStream.close();
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
					
				}
				if(!lv.exists()) {
					InputStream inputStream = JSON.class.getResourceAsStream("/locales/lv.json");
					Path destinationFilePath = new File(Global.persistenceLocation+"/locales").toPath().resolve("lv.json");
					try {
			            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			            System.out.println("[LV] File copied successfully!");
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally {
			            if (inputStream != null) {
			                try {
			                    inputStream.close();
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
			            }
			        }
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
	    } catch(IOException | ParseException e) {
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
	       
	      } catch(IOException | ParseException e) {
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
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println("["+what+"] "+map);
	    return map;
	}
	
}
