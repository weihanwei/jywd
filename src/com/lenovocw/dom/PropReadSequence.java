package com.lenovocw.dom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class PropReadSequence {

	LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();   //属性键值对
	
	String fileName = "";   //文件名
	
	String filePathAndName = "";   //文件相对路径加文件名
	
	List<String> propList = new ArrayList<String>();   //属性列表
	
	public PropReadSequence() {}
	
	/**
	 *	读取文件初始化properties 
	 *	@param fileNameAndPath 文件和路径
	 **/
	public PropReadSequence(String fileName) {
		
		this.fileName = fileName;
		filePathAndName = getFilePath() + "/template/dataIoXml/dataConfig/" + fileName;
		BufferedReader bf;

		try {
	
			FileInputStream is = new FileInputStream(filePathAndName);
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			bf = new BufferedReader(isr);
			String lin = bf.readLine();
			while(lin != null)
			{
				dealLine(lin);
				lin = bf.readLine();
			}
			bf.close();
			isr.close();
			is.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
		
		propList = this.getKeys();
	}
	
	/**
	 *	读取文件初始化properties 
	 *	@param fileNameAndPath 文件和路径
	 **/
	public PropReadSequence(String filePath ,String fileName) {
		
		this.fileName = fileName;
		filePathAndName = getFilePath() + filePath + fileName;
		BufferedReader bf;

		try {
			FileInputStream is = new FileInputStream(filePathAndName);
			InputStreamReader isr = new InputStreamReader(is);
			bf = new BufferedReader(isr);
			String lin = bf.readLine();
			while(lin != null)
			{
				dealLine(lin);
				lin = bf.readLine();
			}
			bf.close();
			isr.close();
			is.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
		propList = this.getKeys();
	}
	
	//写文件
	public boolean writeMap()
	{
		
		OutputStreamWriter outputStreamWriter;
		try {
			outputStreamWriter = new OutputStreamWriter (new FileOutputStream (filePathAndName),"UTF-8");
			Writer writer = new BufferedWriter(outputStreamWriter);
		    PrintWriter printWriter = new PrintWriter(writer);
			
			//Set set = map.keySet();
		    //System.out.println("****************write************");
			Iterator it = propList.iterator();
			while(it.hasNext())
			{
				//keys.add((String)it.next());
				String key = (String)it.next();
				String value = map.get(key);
				String w = key + "=" + value + "\r\n";
				printWriter.write(w, 0, w.length());
			}
			printWriter.close();
			writer.close();
			outputStreamWriter.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//获取文件所在路径
	public String getFilePath()
	{
		String path = String.valueOf(PropReadSequence.class.getResource("/"));
		path = path.replaceAll("file:/", "");
		path = path.substring(0, path.indexOf("/WEB-INF"));
		//path = Func.decode(path);
		//Linux系统前面还得加 '/'
		return path;
		
	}
	
	/**
	 *	获取指定的props值
	 *	@param props 要获取的属性名
	 *	@return String
	 **/
	public String getProps(String props)
	{
		String value = map.get(props);
		if(value == null || "".equals(value))
			return "";
		return value.trim();
	}
	
	/**
	 *	设置指定的props值
	 *	@param props 要设置的属性名
	 *  @param value 值
	 *	@return String
	 **/
	public void setProps(String props, String value)
	{
		map.put(props, value);
	}
	
	/**
	 *	获取指定的props值, 转换中文
	 *	@param props 要获取的属性名
	 *	@return String
	 **/
	public String getPropsOfCn(String props)
	{
		String value = "";
		try {
			value = new String(map.get(props).getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(value == null || "".equals(value))
			return "";
		return value.trim();
	}
	
	public List<String> getKeys()
	{
		List<String> keys = new ArrayList<String>();
		Set set = map.keySet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			keys.add((String)it.next());
		}
		return keys;
	}
	
	public void dealLine(String str)
	{
		if(isEmpty(str)) return ;
		String keys[] = str.split("=");
		if(keys.length == 2)
		{
			if(!isEmpty(keys[0]) && !isEmpty(keys[1]))
			{
				map.put(keys[0], keys[1]);
			}
		}
	}
	
	public boolean isEmpty(String str)
	{
		if(str == null || "".equals(str)) return true;
		else
		{
			if(str.trim().equals("")) return true;
			else return false;
		}
	}
	
	public LinkedHashMap<String, String> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, String> map) {
		this.map = map;
	}

	public List<String> getPropList() {
		return propList;
	}

	public void setPropList(List<String> propList) {
		this.propList = propList;
	}
	
	public List<String> getValueList()
	{
		List<String> values = new ArrayList<String>();
		Iterator<String> it = propList.iterator();
		while(it.hasNext())
		{
			String value = map.get(it.next());
			values.add(value);
		}
		return values;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		PropReadSequence pr = new PropReadSequence("");
		
		System.out.println(pr.getFilePath());
		
		List<String> keys = pr.getKeys();
		for(int i = 0; i < keys.size(); i++)
		{
			System.out.println(keys.get(i));
		}
		System.out.println("***********************");
		pr.setProps("savePic", "0");
		pr.writeMap();
	}
	
}
