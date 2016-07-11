package com.lenovocw.dom;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class DomImpl implements IDom {
	
	private Document doc;
	private SAXReader saxread = new SAXReader();
	private XMLWriter writer;
	
	public DomImpl()
	{
		this.doc=DocumentHelper.createDocument();
	}
	
	public DomImpl(File file) throws Exception
	{
		String data = this.readData(file);
		this.init(data);
	}
	
	public DomImpl(String file) throws Exception
	{
		String data = this.readData(file);
		this.init(data);
	}
	
	public DomImpl(InputStream is) throws Exception
	{
		String data = this.readData(is);
		this.init(data);
	}
	
	public DomImpl(org.w3c.dom.Document is) throws Exception
	{
		DOMReader dr=new DOMReader();
		this.doc=dr.read(is);
	}
	
	public void init(String data) throws Exception
	{
		InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
		this.doc=saxread.read(is);
	}
	
	public String readData(String file) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
		String data = br.readLine();
		String content = "";
		while(data != null)
		{
			content = content + data;
			data = br.readLine();
		}
		return content;
	}
	public String readData(File file) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
		String data = br.readLine();
		String content = "";
		while(data != null)
		{
			content = content + data;
			data = br.readLine();
		}
		return content;
	}
	public String readData(InputStream is) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(is ,"utf-8"));
		String data = br.readLine();
		String content = "";
		while(data != null)
		{
			content = content + data;
			data = br.readLine();
		}
		return content;
	}
	
	public String parse(String path, String label)
	{
		List list=this.doc.selectNodes(path);
		try
		{
			List<String> ls=new ArrayList<String>();
			Iterator iter=list.iterator();
			while(iter.hasNext())
			{
				Element elem=(Element)iter.next();
				ls.add(elem.element(label).getText());
			}
			if(ls.size() > 0) return ls.get(0);
			return "";
		}
		catch(Exception e)
		{
			return "";
		}
		
	}
	
	public int parseInt(String path, String label)
	{
		List list=this.doc.selectNodes(path);
		try
		{
			String val = "";
			List<String> ls=new ArrayList<String>();
			Iterator iter=list.iterator();
			while(iter.hasNext())
			{
				Element elem=(Element)iter.next();
				ls.add(elem.element(label).getText());
			}
			if(ls.size() > 0) val = ls.get(0);
			if(val == "")
				return 0;
			return Integer.parseInt(val);
		}
		catch(Exception e)
		{
			return 0;
		}
		
	}
	
	public List<String> parse(String path) throws Exception
	{
		List list=this.doc.selectNodes(path);
		
		List<String> ls=new ArrayList<String>();
		Iterator iter=list.iterator();
		while(iter.hasNext())
		{
			Element elem=(Element)iter.next();
			ls.add(elem.getText());
		}
		return ls;
	}


	public String[][] parse(String path,String keyEle,String valEle) throws Exception 
	{
		Element elef=null;
		List list=this.doc.selectNodes(path);
		String m[][]=new String[list.size()][2];
		Iterator iter=list.iterator();
		int i=0;
		while(iter.hasNext())
		{
			elef=(Element)iter.next();
			m[i][0]=elef.element(keyEle).getText();
			m[i][1]=elef.element(valEle).getText();
			i++;
		}
		return m;
	}

	public String[][] parse(String path, String keyEle, String valEle,String pic) throws Exception 
	{
		Element elef=null;
		List list=this.doc.selectNodes(path);
		String m[][]=new String[list.size()][3];
		Iterator iter=list.iterator();
		int i=0;
		while(iter.hasNext())
		{
			elef=(Element)iter.next();
			m[i][0]=elef.element(keyEle).getText();
			m[i][1]=elef.element(valEle).getText();
			m[i][2]=elef.element(pic).getText();
			i++;
		}
		return m;
	}

	public String[][] parse(String path, int j, String[] args)
	{
		Element elef=null;
		List list=this.doc.selectNodes(path);
		String m[][]=new String[list.size()][j];
		Iterator iter=list.iterator();
		int i=0;
		int n=0;
		while(iter.hasNext())
		{
			elef=(Element)iter.next();
			for(n=0;n<j;n++)
			{
				String val = "";
				try{
					val = elef.element(args[n]).getText();
				}catch(Exception e)
				{
					val = "";
					e.printStackTrace();
				}
				m[i][n]= (val == null ? "" : val);
			}
			i++;
		}
		return m;
	}

	public void write(String filePath) {
		try {
//			writer = new XMLWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(filePath)), "utf-8"));
//			writer.write(this.doc);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			writer = new XMLWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(filePath)), "utf-8"),format);
			writer.write(this.doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
//		try {
//			StringWriter sw = new StringWriter();
//			writer = new XMLWriter(sw);
//			OutputFormat format = new OutputFormat(" ", true, "utf-8");;
//		    XMLWriter writer = new XMLWriter(sw, format);
//		    System.out.println(parse("/a", "b"));
//			writer.write(this.doc);
//			writer.flush();
//			writer.close();
//			String str = sw.getBuffer().toString();
//			sw.close();
//			return str;
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "d";
//		}
		
		return "";
		
	}

	public Element getElement(String path) {
		List list = this.doc.selectNodes(path);
		if(list != null && list.size() > 0)
			return (Element)list.get(0);
		return null;
	}

	public List getElements(String path) {
		return this.doc.selectNodes(path);
	}

	public List getElements(Element e) {
		if(e != null)
			return e.elements();
		else
			return new ArrayList();
	}
	
	public void setAttribute(Element e, String attributeName,
			String attributeValue) {
		if(e != null)
		{
			Attribute a = e.attribute(attributeName);
			if(a != null)
			{
				a.setValue(attributeValue);
			}
			else
			{
				e.addAttribute(attributeName, attributeValue);
			}
		}
	}
	
	public void setElement(Element e, String name, String value) 
	{
		if(e != null)
		{
			Element temp = e.element(name);
			if(temp != null)
				temp.setText(value == null ? "" : value);
			else
			{
				temp = e.addElement(name);
				temp.setText(value == null ? "" : value);
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		IDom dom = DomFactory.getIdom("e:/dataConfig.xml");
		
		String path = "/info";
		Element e = dom.getElement(path);
		dom.setElement(e, "projectPath", "e:/worksapce/up72web");
		dom.setElement(e, "auth", "e");
		
		dom.write("e:/dataConfig2.xml");
	}

	

	
}
