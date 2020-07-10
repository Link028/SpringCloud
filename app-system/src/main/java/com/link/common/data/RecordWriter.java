package com.link.common.data ;

import java.util.Iterator;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RecordWriter
{
	public static Gson gson = new Gson();
 
	public static String toJson(Record rec) 
	{
		JsonObject json = new JsonObject();
		
		json.addProperty(RecordConstant._TABLE_STR, rec.getTable());
		json.addProperty(RecordConstant._OPR_STR, rec.getOper());

		Iterator<Entry<String, String>> it = rec.iter();

		while (it.hasNext())
		{
			Entry<String, String> entry = it.next(); 
			json.addProperty(entry.getKey(), entry.getValue()) ;
		}

		return gson.toJson(json);
	}
}
