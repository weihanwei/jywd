package com.lenovocw.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JsonWriteUtil {
	
	public static void write(HttpServletResponse response,Object o) throws IOException{
		response.setContentType("text/html");
		response.setStatus(200);
		PrintWriter pw=response.getWriter();
		if(o==null)
			JsonWriteUtil.write(response, "");
		else 
			pw.write(write(o));
			pw.flush();
			pw.close();
	}
	
	
	
	/**
	 * 发送文本。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void writeJson(HttpServletResponse response, String text) throws IOException{
		render(response, "application/json;charset=UTF-8", text);
	}
	
	/**
	 * 发送内容。使用UTF-8编码。
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
			response.getWriter().flush();
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * 灏嗕竴鑸璞¤浆鎹负Json瀵硅薄
	 * 
	 * @param o 闇�涓撴崲鐨勫璞�
	 * @return  JSON瀵硅薄
	 * @throws IOException
	 * @author ZengZS
	 * @time 2012-2-12
	 */
	public static String write(Object o) throws IOException{
		if(o==null)
			return null;
		else if(o instanceof String || o instanceof Boolean || o instanceof Integer || o instanceof Double || o instanceof Double || o instanceof Float ){
			return String.valueOf(o);
		}
		else {
			return JsonUtils.toString(o);
		}
	}

	public static void writeStatusCode(HttpServletResponse response, StatusCode sc) {
		response.setStatus(sc.getCode(), sc.getMessage());
	}
	
	
	
	
}
