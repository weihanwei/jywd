package com.lenovocw.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class ExoCoder {

	/**
	 * 加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @param key
	 *            密钥
	 * @return
	 */
	public static String encrypt(String str, String key) {
		byte[] data;

		data = str.getBytes();

		byte[] keyData = key.getBytes();
		int keyIndex = 0;
		for (int x = 0; x < str.length(); x++) {
			data[x] = (byte) (data[x] ^ keyData[keyIndex]);
			if (++keyIndex == keyData.length) {
				keyIndex = 0;
			}
		}

		return Base64.encodeBase64String(data);

	}

	/**
	 * 解密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @param key
	 *            密钥
	 * @return
	 */
	public static String decrypt(String str, String key) {
		byte[] strByte = Base64.decodeBase64(str.getBytes());
		String newStr = new String(strByte);

		byte[] data = newStr.getBytes();
		byte[] keyData = key.getBytes();
		int keyIndex = 0;
		for (int x = 0; x < newStr.length(); x++) {
			data[x] = (byte) (data[x] ^ keyData[keyIndex]);
			if (++keyIndex == keyData.length) {
				keyIndex = 0;
			}
		}

		return new String(data);
	}

	public static void main(String[] args) {

		String s = encrypt("13631364522", "qweqweaacdasd");
		System.out.println("encrypted:" + s);
		System.out.println(decrypt(s, "qweqweaacdasd"));
		
		
		s = encrypt("pasdf@s>?!@#$%^^&*()-+dfdf", "qweqweaacdasd");
		System.out.println("encrypted:" + s);
		System.out.println(decrypt(s, "qweqweaacdasd"));

	}

}
