package com.json;

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

public class ReadJson {
	//定义json解析器
    private static JsonParser jsonParser=new JsonParser();
    //json测试文件
    private static File file=new File("json.txt");
    
    private static final Gson gson = new GsonBuilder().create();
    
	public static void main(String args[]) throws FileNotFoundException{
		
		FileReader input = new FileReader(file);
		
		JsonElement jsonElement=jsonParser.parse(input);
		JsonObject json=jsonElement.getAsJsonObject();
		
		//简单json的获取
		String reason=json.get("reason").getAsString();
		int resultcode=json.get("resultcode").getAsInt();
		System.out.println("reason:"+reason);
		System.out.println("resultcode:"+resultcode);
		
		//嵌套的json的获取
		JsonObject result=json.get("result").getAsJsonObject();
        JsonObject today=result.get("today").getAsJsonObject();
        String temperature=today.get("temperature").getAsString();
        System.out.println("temperature:"+temperature);
        
        //json数组的解析
        JsonArray array=result.get("future").getAsJsonArray();
        //方法一
//        for(int i=0;i<array.size();i++){
//        	JsonObject subject=array.get(i).getAsJsonObject();
//        	String subtemperature=subject.get("temperature").getAsString();
//        	System.out.println("subtemperature:"+subtemperature);
//        }
        //方法二
//        for(JsonElement element: array){
//        	JsonObject subject=element.getAsJsonObject();
//        	String subtemperature=subject.get("temperature").getAsString();
//        	System.out.println("subtemperature:"+subtemperature);
//        }
        //方法三
        List<JsonObject> subtemperatureList = gson.fromJson(result.get("future").getAsJsonArray(), new TypeToken<List<JsonObject>>() {
		}.getType());
        for(JsonObject subtemperature: subtemperatureList){
        	System.out.println("subtemperature:"+subtemperature.get("temperature").getAsString());
        }
        
		
	}
	
	
}
