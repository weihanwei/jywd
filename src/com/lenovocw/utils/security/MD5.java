package com.lenovocw.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class MD5 {
    /*************************************************************************/
    /**
     *  Convert Message to Digest.
     *  JavaScript version see - http://pajhome.org.uk/crypt/md5/index.html
     *
     *  @param message message
     *  @return HexString of message (length = 32 characters)
     */
    public static String getDigest(String message) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            //	md = MessageDigest.getInstance("SHA-1";
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        //	Reset MessageDigest object
        md.reset();
        //	Convert String to array of bytes
        byte[] input = null;
	try {
	    input = message.getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
        //	feed this array of bytes to the MessageDigest object
        md.update(input);
        //	 Get the resulting bytes after the encryption process
        byte[] output = md.digest();
        md.reset();
        //
        return convertToHexString(output);
    }

    /**
     * 	Checks, if value is a valid digest
     *  @param value digest string
     *  @return true if valid digest
     */
    public static boolean isDigest(String value) {
        if (value == null || value.length() != 32) {
            return false;
        }
        //	needs to be a hex string, so try to convert it
        return (convertHexString(value) != null);
    } //	isDigest

    /**
     *  Convert Byte Array to Hex String
     *  @param bytes bytes
     *  @return HexString
     */
    static private String convertToHexString(byte[] bytes) {
        //	see also Util.toHex
        int size = bytes.length;
        StringBuffer buffer = new StringBuffer(size * 2);
        for (int i = 0; i < size; i++) {
            // convert byte to an int
            int x = (int) bytes[i];
            // account for int being a signed type and byte being unsigned
            if (x < 0) {
                x += 256;
            }
            String tmp = Integer.toHexString(x);
            // pad out "1" to "01" etc.
            if (tmp.length() == 1) {
                buffer.append("0");
            }
            buffer.append(tmp);
        }
        return buffer.toString();
    } //  convertToHexString

    /**
     *  Convert Hex String to Byte Array
     *  @param hexString hex string
     *  @return byte array
     */
    static private byte[] convertHexString(String hexString) {
        int size = hexString.length() / 2;
        byte[] retValue = new byte[size];
        String inString = hexString.toLowerCase();

        try {
            for (int i = 0; i < size; i++) {
                int index = i * 2;
                int ii = Integer.parseInt(inString.substring(index, index + 2), 16);
                retValue[i] = (byte) ii;
            }
            return retValue;
        } catch (Exception e) {
            System.err.println("Secure.convertHexString error - " + e.getMessage());
        }
        return null;
    } //  convertToHexString

    /*************************************************************************/

    /**
     * 	Test
     * 	@param args ignored
     */
    public static void main(String[] args) {
        //acf7ef943fdeb3cbfed8dd0d8f584731
        //System.out.println(getDigest("partner=10854&mobile=13316221505&value=100&type=0&zNcHFKUtGfifT39tw0rqQQi2Di9SZCrCZKrynqgsh60IgSY0Uep0UXwOaFhUVKjB"));
        System.out.println(getDigest("partner=10854&out_trade_id=83&zNcHFKUtGfifT39tw0rqQQi2Di9SZCrCZKrynqgsh60IgSY0Uep0UXwOaFhUVKjB"));
         //      System.out.println(getDigest(getDigest("baiye168168168")+"gf40ee2a706dad51cbe864ff38eb722a7"));
//        System.out.println(getDigest(getDigest("Esolution") + "OPERATOR_0000_0000_0000_000000000000"));
//    System.out.println(getDigest("67fe2a3240421b450dc492121481e11b"+"00000000_0000_0000_0000_000000000000"));
//    String[] testString = new String[] {
//        "This is a test!", "", "This is a verly long test string 1624$%"};
//    String[] digestResult = new String[] {
//        "702edca0b2181c15d457eacac39de39b", "d41d8cd98f00b204e9800998ecf8427e", "934e7c5c6f5508ff50bc425770a10f45"};
//
//    for (int i = 0; i < testString.length; i++) {
//      String digestString = getDigest(testString[i]);
//      if (digestResult[i].equals(digestString)) {
//        System.out.println("OK - digest";
//      }
//      else {
//        System.err.println("Digest=" + digestString + " <> " + digestResult[i]);
//      }
//    }
//
//    System.out.println("IsDigest true=" + isDigest(digestResult[0]));
//    System.out.println("IsDigest false=" + isDigest("702edca0b2181c15d457eacac39DE39J"));
//    System.out.println("IsDigest false=" + isDigest("702e"));
//
//    //	-----------------------------------------------------------------------
//
//    //	System.out.println(convertToHexString(new byte[]{Byte.MIN_VALUE, -1, 1, Byte.MAX_VALUE} ));
//    //
//    String in = "4115da655707807F00FF";
//    byte[] bb = convertHexString(in);
//    String out = convertToHexString(bb);
//    if (in.equalsIgnoreCase(out)) {
//      System.out.println("OK - conversion";
//    }
//    else {
//      System.err.println("Conversion Error " + in + " <> " + out);
//
//      //	-----------------------------------------------------------------------
//
//    }
//    String test = "This is a test!!";
//    String result = "28bd14203bcefba1c5eaef976e44f1746dc2facaa9e0623c";
//    //
//    String test_1 = decrypt(result);
//    if (test.equals(test_1)) {
//      System.out.println("OK - dec_1";
//    }
//    else {
//      System.out.println("TestDec=" + test_1 + " <> " + test);
//
//      //	-----------------------------------------------------------------------
//
//    }
//    String testEnc = encrypt(test);
//    if (result.equals(testEnc)) {
//      System.out.println("OK - enc";
//    }
//    else {
//      System.err.println("TestEnc=" + testEnc + " <> " + result);
//
//    }
//    String testDec = decrypt(testEnc);
//    if (test.equals(testDec)) {
//      System.out.println("OK - dec";
//    }
//    else {
//      System.out.println("TestDec=" + testDec + " <> " + test);
//
//    }

    } //	main

    public static String getMd5Script() {
        String xscript="<script>";

        /*
         * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
         * Digest Algorithm, as defined in RFC 1321.
         * Copyright (C) Paul Johnston 1999 - 2000.
         * Updated by Greg Holt 2000 - 2001.
         * See http://pajhome.org.uk/site/legal.html for details.
         */
        xscript+="var hex_chr = \"0123456789abcdef\";";
        xscript+="function rhex(num)";
        xscript+="{";
        xscript+=" str = \"\";";
        xscript+=" for(j = 0; j <= 3; j++)";
        xscript+=" str += hex_chr.charAt((num >> (j * 8 + 4)) & 0x0F) +";
        xscript+=" hex_chr.charAt((num >> (j * 8)) & 0x0F);";
        xscript+=" return str;";
        xscript+="}";

        xscript+="function str2blks_MD5(str)";
        xscript+="{";
        xscript+=" nblk = ((str.length + 8) >> 6) + 1;";
        xscript+=" blks = new Array(nblk * 16);";
        xscript+=" for(i = 0; i < nblk * 16; i++) blks[i] = 0;";
        xscript+=" for(i = 0; i < str.length; i++)";
        xscript+=" blks[i >> 2] |= str.charCodeAt(i) << ((i % 4) * 8);";
        xscript+=" blks[i >> 2] |= 0x80 << ((i % 4) * 8);";
        xscript+=" blks[nblk * 16 - 2] = str.length * 8;";
        xscript+=" return blks;";
        xscript+="}";

        xscript+="function add(x, y)";
        xscript+="{";
        xscript+=" var lsw = (x & 0xFFFF) + (y & 0xFFFF);";
        xscript+=" var msw = (x >> 16) + (y >> 16) + (lsw >> 16);";
        xscript+=" return (msw << 16) | (lsw & 0xFFFF);";
        xscript+="}";

        xscript+="function rol(num, cnt)";
        xscript+="{";
        xscript+=" return (num << cnt) | (num >>> (32 - cnt));";
        xscript+="}";

        xscript+="function cmn(q, a, b, x, s, t)";
        xscript+="{";
        xscript+=" return add(rol(add(add(a, q), add(x, t)), s), b);";
        xscript+="}";

        xscript+="function ff(a, b, c, d, x, s, t)";
        xscript+="{";
        xscript+=" return cmn((b & c) | ((~b) & d), a, b, x, s, t);";
        xscript+="}";

        xscript+="function gg(a, b, c, d, x, s, t)";
        xscript+="{";
        xscript+=" return cmn((b & d) | (c & (~d)), a, b, x, s, t);";
        xscript+="}";

        xscript+="function hh(a, b, c, d, x, s, t)";
        xscript+="{";
        xscript+=" return cmn(b ^ c ^ d, a, b, x, s, t);";
        xscript+="}";

        xscript+="function ii(a, b, c, d, x, s, t)";
        xscript+="{";
        xscript+=" return cmn(c ^ (b | (~d)), a, b, x, s, t);";
        xscript+="}";

        xscript+="function MD5(str)";
        xscript+="{";
        xscript+=" x = str2blks_MD5(str);";
        xscript+=" var a = 1732584193;";
        xscript+=" var b = -271733879;";
        xscript+=" var c = -1732584194;";
        xscript+=" var d = 271733878;";

        xscript+=" for(i = 0; i < x.length; i += 16)";
        xscript+=" {";
        xscript+=" var olda = a;";
        xscript+=" var oldb = b;";
        xscript+=" var oldc = c;";
        xscript+=" var oldd = d;";
        xscript+=" a = ff(a, b, c, d, x[i+ 0], 7 , -680876936);";
        xscript+=" d = ff(d, a, b, c, x[i+ 1], 12, -389564586);";
        xscript+=" c = ff(c, d, a, b, x[i+ 2], 17, 606105819);";
        xscript+=" b = ff(b, c, d, a, x[i+ 3], 22, -1044525330);";
        xscript+=" a = ff(a, b, c, d, x[i+ 4], 7 , -176418897);";
        xscript+=" d = ff(d, a, b, c, x[i+ 5], 12, 1200080426);";
        xscript+=" c = ff(c, d, a, b, x[i+ 6], 17, -1473231341);";
        xscript+=" b = ff(b, c, d, a, x[i+ 7], 22, -45705983);";
        xscript+=" a = ff(a, b, c, d, x[i+ 8], 7 , 1770035416);";
        xscript+=" d = ff(d, a, b, c, x[i+ 9], 12, -1958414417);";
        xscript+=" c = ff(c, d, a, b, x[i+10], 17, -42063);";
        xscript+=" b = ff(b, c, d, a, x[i+11], 22, -1990404162);";
        xscript+=" a = ff(a, b, c, d, x[i+12], 7 , 1804603682);";
        xscript+=" d = ff(d, a, b, c, x[i+13], 12, -40341101);";
        xscript+=" c = ff(c, d, a, b, x[i+14], 17, -1502002290);";
        xscript+=" b = ff(b, c, d, a, x[i+15], 22, 1236535329);";
        xscript+=" a = gg(a, b, c, d, x[i+ 1], 5 , -165796510);";
        xscript+=" d = gg(d, a, b, c, x[i+ 6], 9 , -1069501632);";
        xscript+=" c = gg(c, d, a, b, x[i+11], 14, 643717713);";
        xscript+=" b = gg(b, c, d, a, x[i+ 0], 20, -373897302);";
        xscript+=" a = gg(a, b, c, d, x[i+ 5], 5 , -701558691);";
        xscript+=" d = gg(d, a, b, c, x[i+10], 9 , 38016083);";
        xscript+=" c = gg(c, d, a, b, x[i+15], 14, -660478335);";
        xscript+=" b = gg(b, c, d, a, x[i+ 4], 20, -405537848);";
        xscript+=" a = gg(a, b, c, d, x[i+ 9], 5 , 568446438);";
        xscript+=" d = gg(d, a, b, c, x[i+14], 9 , -1019803690);";
        xscript+=" c = gg(c, d, a, b, x[i+ 3], 14, -187363961);";
        xscript+=" b = gg(b, c, d, a, x[i+ 8], 20, 1163531501);";
        xscript+=" a = gg(a, b, c, d, x[i+13], 5 , -1444681467);";
        xscript+=" d = gg(d, a, b, c, x[i+ 2], 9 , -51403784);";
        xscript+=" c = gg(c, d, a, b, x[i+ 7], 14, 1735328473);";
        xscript+=" b = gg(b, c, d, a, x[i+12], 20, -1926607734);";
        xscript+=" a = hh(a, b, c, d, x[i+ 5], 4 , -378558);";
        xscript+=" d = hh(d, a, b, c, x[i+ 8], 11, -2022574463);";
        xscript+=" c = hh(c, d, a, b, x[i+11], 16, 1839030562);";
        xscript+=" b = hh(b, c, d, a, x[i+14], 23, -35309556);";
        xscript+=" a = hh(a, b, c, d, x[i+ 1], 4 , -1530992060);";
        xscript+=" d = hh(d, a, b, c, x[i+ 4], 11, 1272893353);";
        xscript+=" c = hh(c, d, a, b, x[i+ 7], 16, -155497632);";
        xscript+=" b = hh(b, c, d, a, x[i+10], 23, -1094730640);";
        xscript+=" a = hh(a, b, c, d, x[i+13], 4 , 681279174);";
        xscript+=" d = hh(d, a, b, c, x[i+ 0], 11, -358537222);";
        xscript+=" c = hh(c, d, a, b, x[i+ 3], 16, -722521979);";
        xscript+=" b = hh(b, c, d, a, x[i+ 6], 23, 76029189);";
        xscript+=" a = hh(a, b, c, d, x[i+ 9], 4 , -640364487);";
        xscript+=" d = hh(d, a, b, c, x[i+12], 11, -421815835);";
        xscript+=" c = hh(c, d, a, b, x[i+15], 16, 530742520);";
        xscript+=" b = hh(b, c, d, a, x[i+ 2], 23, -995338651);";
        xscript+=" a = ii(a, b, c, d, x[i+ 0], 6 , -198630844);";
        xscript+=" d = ii(d, a, b, c, x[i+ 7], 10, 1126891415);";
        xscript+=" c = ii(c, d, a, b, x[i+14], 15, -1416354905);";
        xscript+=" b = ii(b, c, d, a, x[i+ 5], 21, -57434055);";
        xscript+=" a = ii(a, b, c, d, x[i+12], 6 , 1700485571);";
        xscript+=" d = ii(d, a, b, c, x[i+ 3], 10, -1894986606);";
        xscript+=" c = ii(c, d, a, b, x[i+10], 15, -1051523);";
        xscript+=" b = ii(b, c, d, a, x[i+ 1], 21, -2054922799);";
        xscript+=" a = ii(a, b, c, d, x[i+ 8], 6 , 1873313359);";
        xscript+=" d = ii(d, a, b, c, x[i+15], 10, -30611744);";
        xscript+=" c = ii(c, d, a, b, x[i+ 6], 15, -1560198380);";
        xscript+=" b = ii(b, c, d, a, x[i+13], 21, 1309151649);";
        xscript+=" a = ii(a, b, c, d, x[i+ 4], 6 , -145523070);";
        xscript+=" d = ii(d, a, b, c, x[i+11], 10, -1120210379);";
        xscript+=" c = ii(c, d, a, b, x[i+ 2], 15, 718787259);";
        xscript+=" b = ii(b, c, d, a, x[i+ 9], 21, -343485551);";
        xscript+=" a = add(a, olda);";
        xscript+=" b = add(b, oldb);";
        xscript+=" c = add(c, oldc);";
        xscript+=" d = add(d, oldd);";
        xscript+=" }";
        xscript+=" return rhex(a) + rhex(b) + rhex(c) + rhex(d);";
        xscript+="}";

//alert(MD5('Esolution'));

        xscript+="</script>";

        return xscript;

    }
} //  Secure
