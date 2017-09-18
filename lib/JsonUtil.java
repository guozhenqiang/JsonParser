import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
	//定义json解析器
	private static JsonParser jsonParser=new JsonParser();
	private static final Gson gson = new GsonBuilder().create();
	//简单json解析
	public static JsonObject parser(String json, String key){
		if(json==null || key==null )return null;
		JsonElement jsonElement=jsonParser.parse(json);
		JsonObject jsonObject=jsonElement.getAsJsonObject();
		return jsonElement.get(key).getAsJsonObject();
	}
	
	//json数组解析
	public static List<JsonObject> parserJsonArray(String json, String key){
		if(json==null || key==null )return null;
		JsonElement jsonElement=jsonParser.parse(json);
		JsonObject jsonObject=jsonElement.getAsJsonObject();
		 List<JsonObject> jsonObjectList = gson.fromJson(jsonObject.get("future").getAsJsonArray(), new TypeToken<List<JsonObject>>() {
			}.getType());
		 return jsonObjectList;
		
	}
	
}
