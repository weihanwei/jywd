package com.lenovocw.utils;


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class SendDataWebserviceUtils {
	
	private Logger logger=LogManager.getLogger(getClass());
	
	/**
	 * 调用 
	 * @param methodName
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String call(String namespace,String url,String methodName,String data){
			try{
				System.out.println("执行方法："+methodName);
				String soapActionURI = namespace+methodName;
				Service service = new Service();  
				Call call = (Call) service.createCall();  
				call.setTargetEndpointAddress(new java.net.URL(url));  
				call.setUseSOAPAction(true);
				call.setSOAPActionURI(soapActionURI);  
				call.setOperationName(new QName(namespace, methodName));
				 //call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN); 
				 
				  call.addParameter(new QName(namespace,"usr_nbr"), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
				   call.addParameter(new QName(namespace,"stat_mo"),XMLType.XSD_INT, javax.xml.rpc.ParameterMode.IN);				 
				call.setReturnType(XMLType.XSD_STRING);
				String msg="";
				try{
					msg = (String) call.invoke(new String[] {"13923388027","1"});
					System.out.println("调用webservuce返回数据："+msg);
				}catch(Exception ex){
					System.out.println("调用webservuce异常："+ex.toString());
				}
				return msg;
			}catch(Exception ex){
				System.out.println("SendDataWebserviceUtils发生异常："+ex.toString());
				return null;
			}
	}
	
	public static void main(String[] args){
		StringBuffer xml= new StringBuffer();
		xml.append("<USR_NBR>");
		xml.append("");
		xml.append("</USR_NBR>");
		xml.append("<STAT_MO>");
		xml.append("");
		xml.append("</STAT_MO>");
		System.out.println(call("Bingosoft.139InterfaceCenter/", "http://10.249.224.24:9000/Label10086.asmx", "GetNbrMoStatisticsInfo", xml.toString()));
	}
	
	
	
}
