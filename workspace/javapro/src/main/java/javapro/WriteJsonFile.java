package javapro;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WriteJsonFile {
	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Json key, value 추가
		JsonObject jsonObject = new JsonObject();
		// manager
		JsonObject manager = getJsonObject("admin1", "admin1234", "관리자", "샤로수길점", 13.5, true, 21000);
		jsonObject.add("manager", manager);
		// JsonObject를 파일에 쓰기
		String filepath = "C:\\github\\ParkLee-unmanned-store\\javapro\\src\\main\\json\\manager.json";
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath),"UTF8"));
		gson.toJson(jsonObject, writer);
		writer.flush();
		writer.close();
	}

	public static JsonObject getJsonObject(String id, String pw, String name, String brand, double percent, boolean emp, int empsal) {
		JsonObject subjectJsonObject = new JsonObject();
		subjectJsonObject.addProperty("id", id);
		subjectJsonObject.addProperty("pw", pw);
		subjectJsonObject.addProperty("name", name);
		subjectJsonObject.addProperty("brand", brand);
		subjectJsonObject.addProperty("name", name);
		subjectJsonObject.addProperty("percent", percent);
		subjectJsonObject.addProperty("emp", emp);
		subjectJsonObject.addProperty("empsal", empsal);
		return subjectJsonObject;
	}
}