package com.lenovocw.music.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.Security;
import java.util.Arrays;
import java.util.Random;

/**
 * 与数通信息接口加密算法
* @author X230I 
*
 */
public class ThreeDes {
	
	static{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}
	
	public static final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
		0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
		0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
		(byte) 0xE2 };
	
	public static int getSeqId(){		
		Random random = new Random();
		return Math.abs(random.nextInt());
	}

	private static final String Algorithm = "DESede"; // algorithm/mode/padding
	private static final String amp = "DESede/ECB/PKCS5Padding";


	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(amp);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(amp);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

		
	/** 16进制数字字符集  */ 
	private   static   String   hexString= "0123456789ABCDEF"; 
	
	/** 
	  *   将字符串编码成16进制数字,适用于所有字符（包括中文） 
	  */ 
	public static String byte2hex(byte[] bytes) { 
		StringBuffer   sb=new   StringBuffer(bytes.length*2); 
		for(int i=0;i <bytes.length;i++){ 
			sb.append(hexString.charAt((bytes[i]&0xf0)>> 4)); 
			sb.append(hexString.charAt((bytes[i]&0x0f)>> 0)); 
		} 
		return   sb.toString(); 
	} 
	
	/**将每2位16进制整数组装成一个字节
	 * 
	 * @param bytes
	 * @return
	 */ 
	public  static  byte[]  hex2byte(String hex) { 
		ByteArrayOutputStream baos=new ByteArrayOutputStream(hex.length()/2); 
		for(int   i=0;i <hex.length();i+=2) 
			baos.write((hexString.indexOf(hex.charAt(i)) <<4  |hexString.indexOf(hex.charAt(i+1)))); 
		return  baos.toByteArray(); 
	}
	
	/**
	 * 取得加密后数据
	 * @param szSrc
	 * @return
	 */
	public static String getDes(String szSrc) {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());		
		String hex = byte2hex(encoded);
		return hex;
	}


	/**
	 * 取得解密后数据
	 * @param hex
	 * @return
	 */
	public static String getUnDes(String hex) {
		byte [] ob = hex2byte(hex);
		
		byte[] srcBytes = decryptMode(keyBytes, ob);
		return new String(srcBytes);
	}
	
	/**
	 * 根据密钥取得加密后数据
	 * @param szSrc
	 * @return
	 */
	public static String getDes(String szSrc,String keys) {
		System.out.println("-----------------进入加密");
		// 添加新安全算法,如果用JCE就要把它添加进去
		//Security.addProvider(new com.sun.crypto.provider.SunJCE());
	    byte[]  tmpKeys= Arrays.copyOf(keys.getBytes(), 24);		
		byte[] encoded = encryptMode(tmpKeys, szSrc.getBytes());		
		String hex = byte2hex(encoded);
		System.out.println("-----------------hex："+hex);
		return hex;
	}
	
	/**
	 * 根据密钥取得解密后数据
	 * @param hex
	 * @return
	 */
	public static String getUnDes(String hex,String keys) {
		byte [] ob = hex2byte(hex);
		byte[]  tmpKeys= Arrays.copyOf(keys.getBytes(), 24);	
		byte[] srcBytes = decryptMode(tmpKeys, ob);
		return new String(srcBytes);
	}

	public static void main(String[] args) { 
		ThreeDes des = new ThreeDes();
		 String sKey = "MnYhdwAj8Gm3Vc0NsA9BeRtn";
		String strList = "channel1,order001,20121210231000,merchant01,13600000000,,0,10,";
		String s = des.getDes("BoHe137106089841419318857","ABC");
		System.out.println(s );
		System.out.println(des.getUnDes(s,"ABC"));
//		
//		System.out.println(des.getUnDes("56FEE922F66556B5A88A59169475726763C5FF7E01800A9601DA4F4432C131BED7145E2D5E75C7071FACC7DB5D9FC1681996C5601A17AB0EFD69D2E2500C5865B8B0BD3DDEFC56CD0E60D36FCC4D5514CBB96EAC3176B523D553713D40A3B216070398492C020D35BE6FD0A8A9A5A31FB2AE157EEC3B6A817466F555A9E514C0049323F092DA1CB07881AF393B923FC3C4D9584E511498605599EEBFAEE2B487CFEAD759ABA392E1AF50FF8A6D808757B1F054D72602DBD37331BCCD8901F16218C426ED167895252BC5F40FD5C78E97"));
//		System.out.println(des.getUnDes("E55BABF1AF67C9D2EB7953932DE7A8F8A4BC6A8C18B60AB1794FE57A2EF766EA6FB1C0193D8790BB7D18E879A84934A59871C3168C2033B27AC0A9BF5EF90F38FC6F231E543E16B1A95CA03CA3259D3D445F1124DDCE19578479A5E468B0383CF03EEBAD7A40183F280D1F5B88974B73267FD5DD1B537C506507EB059D5F79027661A81EAABC1D94FC6F231E543E16B155042F786D187E7AB813A06B985D2B7A2E06C9AC71C05544F03EEBAD7A40183F280D1F5B88974B73C96028290CDD7AA40445B5FA917D83B8E6E0BEA046BDF3B0FC6F231E543E16B144A16E0F1C00C3E0E88B385646C976A9DB593BAAAAB1EF5DF03EEBAD7A40183F280D1F5B88974B73EF7B703A6153E4E4F5A7E14CC7B77BB56413B95DBDE72E49FC6F231E543E16B1FE33DE2D5BE05E8E2F0D91167880A1BD177B86DB761109F5DD5C3D761B8925B1"));
//		System.out.println(des.getUnDes("C3FD5D35E7D84BE587F81B5D89CD6F7EF43C72B19A13E020CF73442A53BFC45BEC234C00EB5349F7D689565CAA8B08CAF7B7F908753856BEF7A9918FDE79AE9AAC13C9C03AB8A5C8345D14BF4C05A53791346E3C3206A96378FD7881FAEB70571C016FE80DBD386CF12909CBA883E61F93B91983D0FB2365CA62B622C4DE5C1E144959DD23F62F46"));
//		System.out.println(des.getUnDes("3F532B17B8AEDAA1B672DFE03A832EB13A4F56D1009AA6C07CF764E25D13382ABC0B86DA7E6A48DC96946EE1FFFD83D05EEBDC3B58A8363283674F5CB5CC811712D036AC1390788B61C60EB1D25D2A1C45B982027DB844000E4C78092FAF28DC48D96AEC9FB22201E3BB53A73E8D6B4A1846C56C5236CA88490BD4626F0E1C71DB6D441237BD7C137453201AAC27747CA20CA162F5F7AF8D29A5031669B03705B182A436843CEE33497F9EA55EE259FF96D29137F949603C25E9D68BBFCC737E114D1FD08D2DCD2BF191CE6D560B01E9195ACD0E276414F0B337BBABD3FED5B3CEC9EB258A0D3D5503CE6804A848335089AD545E861B359DB40FEAAC2158B219987D3D1452A84C9FCB07333C8504A1EC98E4149C3FBC905591E2D65297F00BDE213E61842A2DF4A6C0B53F3487311E92E3757121D3872B1E2FDE54ED5B917CE810FEF7E073F51EEB922E835BB50A386AD9F0BEA334FA4D3D"));

				
//		Date date = new Date();
//		String sendTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date); 
//		long timestamp = date.getTime();
//		System.out.println(timestamp);
//		String appid="CDC|";		
//		StringBuffer sb = new StringBuffer(appid).append("18665656342|").append(timestamp);
//		System.out.println(sb);
//		System.out.println(des.getDes(sb.toString()));
	}

}
