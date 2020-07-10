package com.link.common.data;

import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RecordReader
{
	private static final JsonParser parser = new JsonParser();

	public static Record fromJson(String json) 
	{
		JsonElement root = parser.parse(json);
		JsonObject rootObj = root.getAsJsonObject();

		Record rec = new Record();

		for (Iterator<Map.Entry<String, JsonElement>> iterator = rootObj.entrySet().iterator(); iterator.hasNext();)
		{
			Map.Entry<String, JsonElement> entry = iterator.next();

			String key = entry.getKey();
			String val = entry.getValue() != null && !entry.getValue().isJsonNull() ? entry.getValue().getAsString() : "";

			if (RecordConstant._OPR_STR.equals(key))
			{
				rec.setOper(val);
			}
			else if (RecordConstant._TABLE_STR.equals(key))
			{
				rec.setTable(val);
			}
			else
			{
				rec.set(key, val);
			}
		}

		return rec;
	}
}
