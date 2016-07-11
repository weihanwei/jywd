package com.lenovocw.dom;

import java.io.File;
import java.io.InputStream;

public class DomFactory {
	
	public DomFactory(){}
	
	public static IDom getIdom()throws Exception
	{
		return (IDom) new DomImpl();
	}
	
	public static IDom getIdom(String file)throws Exception
	{
		return (IDom) new DomImpl(file);
	}

	public static IDom getIdom(InputStream is) throws Exception
	{
		return (IDom) new DomImpl(is);
	}

	public static IDom getIdom(File file)throws Exception
	{
		return (IDom) new DomImpl(file);
	}
	

}
