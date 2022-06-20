package notice;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ex01 {
	public static void main(String[] args) {
		
		
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		
		object1.put("name","김철수");		// {"name":"김철수"}
		object1.put("age",23);
		object1.put("height",170.3);
		
		object2.put("kor",56);
		object2.put("eng",77);
		object2.put("mat",43);
		
		
		object1.put("scores",object2);	// {"scores":{"kor":56,"eng":77,"mat":43}}
		
		System.out.println(object1);
		
		JSONArray array = new JSONArray();	// JSON타입 배열 
		array.put(object1);
		
		System.out.println(array);
		/*
		JSONObject object = new JSONObject();
		// {"name":"value",...} 
		
		object.put("name1", 1);
		object.put("name2", 1.0);
		object.put("name3", "하이");
		// object는 toString() 바로 호출
//		{"name1":1,"name2":1.0,"name3","하이"} 와 같은 뜻 
		
		System.out.println(object);
	
		JSONArray array = new JSONArray();
		
		array.put(1);
		array.put(3.14);
		array.put("문자열");
		
		System.out.println(array);
		*/
	}
}
