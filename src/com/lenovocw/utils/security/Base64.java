package com.lenovocw.utils.security;

/**
 * This class is used to encode and decode data in Base64 format
 * as described in RFC 1521.
 *
 * @author Christian d'Heureuse
 *
 */

/**************************************************************************
 *
 * A Base64 Encoder/Decoder.
 *
 * This class is used to encode and decode data in Base64 format
 * as described in RFC 1521.
 *
 * <p>
 * Copyright 2003: Christian d'Heureuse, Inventec Informatik AG, Switzerland.<br>
 * License: This is "Open Source" software and released under the <a href="http://www.gnu.org/licenses/gpl.html" target="_top">GNU/GPL</a> license.
 * It is provided "as is" without warranty of any kind. Please contact the author for other licensing arrangements.<br>
 * Home page: <a href="http://www.source-code.biz" target="_top">www.source-code.biz</a><br>
 *
 * <p>
 * Version history:<br>
 * 2003-07-22 Christian d'Heureuse (chdh): Module created.
 *
 **************************************************************************/

public class Base64 {
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 64;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int SIXBIT = 6;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final byte PAD = 61;
    private static byte base64Alphabet[];
    private static byte lookUpBase64Alphabet[];

    static {
        base64Alphabet = new byte[255];
        lookUpBase64Alphabet = new byte[64];
        for (int i = 0; i < 255; i++) {
            base64Alphabet[i] = -1;
        }

        for (int j = 90; j >= 65; j--) {
            base64Alphabet[j] = (byte) (j - 65);
        }

        for (int k = 122; k >= 97; k--) {
            base64Alphabet[k] = (byte) ((k - 97) + 26);
        }

        for (int l = 57; l >= 48; l--) {
            base64Alphabet[l] = (byte) ((l - 48) + 52);
        }

        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (int i1 = 0; i1 <= 25; i1++) {
            lookUpBase64Alphabet[i1] = (byte) (65 + i1);
        }

        int j1 = 26;
        for (int k1 = 0; j1 <= 51; k1++) {
            lookUpBase64Alphabet[j1] = (byte) (97 + k1);
            j1++;
        }

        int l1 = 52;
        for (int i2 = 0; l1 <= 61; i2++) {
            lookUpBase64Alphabet[l1] = (byte) (48 + i2);
            l1++;
        }

        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }

// Mapping table from 6-bit nibbles to Base64 characters.
    private static char[] map1 = new char[64];
    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            map1[i++] = c;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            map1[i++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            map1[i++] = c;
        }
        map1[i++] = '+';
        map1[i++] = '/';
    }

// Mapping table from Base64 characters to 6-bit nibbles.
    private static byte[] map2 = new byte[128];
    static {
        for (int i = 0; i < map2.length; i++) {
            map2[i] = -1;
        }
        for (int i = 0; i < 64; i++) {
            map2[map1[i]] = (byte) i;
        }
    }

    /**
     * Encodes a string into Base64 format.
     * No blanks or line breaks are inserted.
     * @param s  a String to be encoded.
     * @return   A String with the Base64 encoded data.
     */
    public static String encode(String s) {
        String outStr = new String(encode(s.getBytes()));
        return outStr;
    }

    public static String encodeUrl(String s) {
        String outStr = new String(encode(s.getBytes()));

        try {
            outStr = outStr.replace('+', '.');
            outStr = outStr.replace('/', '_');
            outStr = outStr.replace('=', '-');
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return outStr;
    }


    /**
     * Encodes a byte array into Base64 format.
     * No blanks or line breaks are inserted.
     * @param in  an array containing the data bytes to be encoded.
     * @return    A character array with the Base64 encoded data.
     */
    public static char[] encode(byte[] in) {
        int iLen = in.length;
        int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
        int oLen = ((iLen + 2) / 3) * 4; // output length including padding
        char[] out = new char[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++] & 0xff;
            int i1 = ip < iLen ? in[ip++] & 0xff : 0;
            int i2 = ip < iLen ? in[ip++] & 0xff : 0;
            int o0 = i0 >>> 2;
            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
            int o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            op++;
            out[op] = op < oDataLen ? map1[o3] : '=';
            op++;
        }
        return out;
    }

    /**
     * Decodes a Base64 string.
     * @param s  a Base64 String to be decoded.
     * @return   A String containing the decoded data.
     * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
     */
    public static String decode(String s) {
        String outStr = new String(decode(s.toCharArray()));

        return outStr;
    }

    public static String decodeUrl(String inStr) {
        String outStr = inStr;

        outStr = outStr.replace('.', '+');
        outStr = outStr.replace('_', '/');
        outStr = outStr.replace('-', '=');

        outStr = new String(decode(outStr.toCharArray()));

        return outStr;
    }

    /**
     * Decodes Base64 data.
     * No blanks or line breaks are allowed within the Base64 encoded data.
     * @param in  a character array containing the Base64 encoded data.
     * @return    An array containing the decoded data bytes.
     * @throws    IllegalArgumentException if the input is not valid Base64 encoded data.
     */
    public static byte[] decode(char[] in) {
        int iLen = in.length;
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException(
                    "Length of Base64 encoded input string is not a multiple of 4.");
        } while (iLen > 0 && in[iLen - 1] == '=') {
            iLen--;
        }
        int oLen = (iLen * 3) / 4;
        byte[] out = new byte[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++];
            int i1 = in[ip++];
            int i2 = ip < iLen ? in[ip++] : 'A';
            int i3 = ip < iLen ? in[ip++] : 'A';
            if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127) {
                throw new IllegalArgumentException(
                        "Illegal character in Base64 encoded data.");
            }
            int b0 = map2[i0];
            int b1 = map2[i1];
            int b2 = map2[i2];
            int b3 = map2[i3];
            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
                throw new IllegalArgumentException(
                        "Illegal character in Base64 encoded data.");
            }
            int o0 = (b0 << 2) | (b1 >>> 4);
            int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
            int o2 = ((b2 & 3) << 6) | b3;
            out[op++] = (byte) o0;
            if (op < oLen) {
                out[op++] = (byte) o1;
            }
            if (op < oLen) {
                out[op++] = (byte) o2;
            }
        }
        return out;
    }

    public static byte[] decode(byte abyte0[]) {
        if (abyte0.length == 0) {
            return new byte[0];
        }
        int i = abyte0.length / 4;
        byte abyte1[] = null;
        int j = 0;
        int l;
        for (l = abyte0.length; abyte0[l - 1] == 61; ) {
            if (--l == 0) {
                return new byte[0];
            }
        }

        abyte1 = new byte[l - i];
        for (int i1 = 0; i1 < i; i1++) {
            int k = i1 * 4;
            byte byte5 = abyte0[k + 2];
            byte byte6 = abyte0[k + 3];
            byte byte0 = base64Alphabet[abyte0[k]];
            byte byte1 = base64Alphabet[abyte0[k + 1]];
            if (byte5 != 61 && byte6 != 61) {
                byte byte2 = base64Alphabet[byte5];
                byte byte4 = base64Alphabet[byte6];
                abyte1[j] = (byte) (byte0 << 2 | byte1 >> 4);
                abyte1[j + 1] = (byte) ((byte1 & 0xf) << 4 | byte2 >> 2 & 0xf);
                abyte1[j + 2] = (byte) (byte2 << 6 | byte4);
            } else
            if (byte5 == 61) {
                abyte1[j] = (byte) (byte0 << 2 | byte1 >> 4);
            } else
            if (byte6 == 61) {
                byte byte3 = base64Alphabet[byte5];
                abyte1[j] = (byte) (byte0 << 2 | byte1 >> 4);
                abyte1[j + 1] = (byte) ((byte1 & 0xf) << 4 | byte3 >> 2 & 0xf);
            }
            j += 3;
        }

        return abyte1;
    }

    private static String byte2hex(byte abyte0[]) {
        String s = "";
        String s1 = "";
        for (int i = 0; i < abyte0.length; i++) {
            String s2 = Integer.toHexString(abyte0[i] & 0xff);
            if (s2.length() == 1) {
                s = s + "0" + s2;
            } else {
                s = s + s2;
            }
            if (i < abyte0.length - 1) {
                s = s + "";
            }
        }

        return s.toUpperCase();
    }

    public static void main(String args[]) {
        try {
            String test = "DEF51DFD857724C90FBBB48B3F2210A9";
 //           test = Base64.encode(test);
            test = Base64.decode(test);
            System.out.println(test);

       } catch (Exception ie) {
           ie.printStackTrace();
           //LogManager.getLogger().error( ie.getMessage() );
       }
    }
}
