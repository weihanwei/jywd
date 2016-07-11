package com.lenovocw.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class CryptoTool {
	// 设置向量
	private static final byte[] DESIV = { (byte) 0x39, (byte) 0x39, (byte) 0x42, (byte) 0x32, (byte) 0x44, (byte) 0x45, (byte) 0x31, (byte) 0x37 };

	// 加密算法的参数接口
	static AlgorithmParameterSpec iv = null;

	// Key
	private Key key = null;

	private static CryptoTool instance = null;

	public static synchronized CryptoTool getInstance(String key) {
		if (instance == null)
			try {
				instance = new CryptoTool(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return instance;
	}

	private CryptoTool(String key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes());// 设置密钥参数
		iv = new IvParameterSpec(DESIV);// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		this.key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}

    //厦航解密方法 
    public static String decrypt(String message,String key) throws Exception {   
            
            byte[] bytesrc =convertHexString(message);      
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");       
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));      
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
                   
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);         
             
            byte[] retByte = cipher.doFinal(bytesrc);        
            return new String(retByte);    
    }   
   //厦航加密方法     
    public static String encrypt(String message, String key)   
            throws Exception {   
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
  
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
  
        return bytes2HexString(cipher.doFinal(message.getBytes("UTF-8")));   
    }   
       
    public static byte[] convertHexString(String ss)    
    {    
        byte digest[] = new byte[ss.length() / 2];    
        for(int i = 0; i < digest.length; i++)    
        {    
        String byteString = ss.substring(2 * i, 2 * i + 2);    
        int byteValue = Integer.parseInt(byteString, 16);    
        digest[i] = (byte)byteValue;    
        }    
  
        return digest;    
    }   
    public static String bytes2HexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
        	String hex = Integer.toHexString(b[i] & 0xFF);
          if (hex.length() == 1) {
            hex = '0' + hex;
          }
          result += hex;
        }
        return result.toUpperCase();
    }
    
    private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        int length = data.length;
        for(int i = 0; i < length; ++i) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            }
            while(++two_halfs < 1);
        } 
        return buf.toString();
    }
    /**
     * SHA1加密
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(String text) { 
    	String sha1;
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
//	        byte[] sha1hash = new byte[40];
	        md.update(text.getBytes("iso-8859-1"), 0, text.length());
	        sha1= new BigInteger(1, md.digest()).toString(16);
//	        sha1hash = md.digest();
	        return sha1;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return "";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}

    }
    /**
     * MD5加密
     * @param s
     * @return
     */
    public static String md5(String s) {
    	String md5;
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
//            byte messageDigest[] = digest.digest();
             md5= new BigInteger(1, digest.digest()).toString(16);
             return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
                
        return "";
    }


}