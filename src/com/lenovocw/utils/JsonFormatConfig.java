package com.lenovocw.utils;

import net.sf.json.JsonConfig;

public class JsonFormatConfig extends JsonConfig{

	public JsonFormatConfig() {
		super();
		registerJsonValueProcessor(java.util.Date.class,new JsonDateValueProcessor());
	}
	
}
