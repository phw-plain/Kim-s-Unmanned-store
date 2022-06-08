package javapro;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JsonFileEdit {
	private static String path = System.getProperty("user.dir") + "/src/main/json/setting.json"; 
	
	public static void writeJSonFile(String x1, String y1, String font1, String theme1) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		// Json key, value 
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("x", x1);
		jsonObject.addProperty("y", y1);
		jsonObject.addProperty("font", font1);
		jsonObject.addProperty("theme", theme1);
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF8"));
		gson.toJson(jsonObject, writer);
		
		writer.flush();
		writer.close();
	}
	
	public static void readJsonFile() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject =(JSONObject) obj;
		
			String x = (String) jsonObject.get("x");
			String y = (String) jsonObject.get("y"); 
			String font = (String) jsonObject.get("font");
			String theme = (String) jsonObject.get("theme");
			
			System.out.println("x:"+ x);
			System.out.println("y:"+ y);
			System.out.println("font:"+ font);
			System.out.println("theme:"+ theme);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		JSONParser parser = new JSONParser();
		String value = null;
		
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject =(JSONObject) obj;
		
			value = (String) jsonObject.get(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
}