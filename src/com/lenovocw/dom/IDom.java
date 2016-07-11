package com.lenovocw.dom;

import java.util.List;

import org.dom4j.Element;

public interface IDom
{
	List<String> parse(String path) throws Exception;
	String[][] parse(String path,String keyEle,String valEle) throws Exception;
	String[][] parse(String path,String keyEle,String valEle,String pic) throws Exception;
	String[][] parse(String path,int i,String args[]);
	String parse(String path, String label);
	int parseInt(String path, String label);
	
	//转换成字符串表示
	String toString();
	
	//写当前XML文件
	void write(String filePath);
	
	//根据属性名获取List列表
	List getElements(String path);
	
	//根据属性名获取某个
	Element getElement(String path);
	
	//获取某个结点的所有子结点
	List getElements(Element e);
	
	//修改某个结点的某属性的值
	void setAttribute(Element e, String attributeName, String attributeValue);
	
	//修改某个结点的值
	void setElement(Element e, String name, String value);
	
	
}
