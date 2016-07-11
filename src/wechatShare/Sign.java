package wechatShare;

import java.util.UUID;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  

import com.lenovocw.music.util.LogRecord;

public class Sign {    
	
    public static String sign(String jsapi_ticket,String url,String timestamp) throws Exception {
        String nonce_str = "82693e11-b9bc-448e-892f-f5289f46cd0f";
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        LogRecord.info("signature data:"+string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw e;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw e;
        }
        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    

}
