package com.lenovocw.utils;


import sun.misc.BASE64Decoder;

public class Base64Utils {
 
	/**
	    * Base64加密
	    * @param str
	    * @return
	    */
	   public static String getBase64(String str){
			
			if (str==null){
				
				return null; 
				
			}else{
				
				return (new sun.misc.BASE64Encoder()).encode(str.getBytes()); 
			}

		}
	   
	    /**
	     * BASE64解密
	     * @param
	     * @return
	     */
		public static String getFromBASE64(String s) { 
			if (s == null) return null; 
			BASE64Decoder decoder = new BASE64Decoder(); 
			try { 
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,"UTF-8"); 
			} catch (Exception e) { 
			return null; 
			} 
		}
 
}

