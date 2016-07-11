package com.lenovocw.utils.security;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DES {
  private static String ALGORITHM = "DES";
  private static Cipher c;
  private static Key deskey;
  private static AlgorithmParameters ap;
  private static DES instance;

  private DES() {
    c = null;
    deskey = null;
    ap = null;
    init();
  }

  private void init() {
    try {
      Security.addProvider(new com.sun.crypto.provider.SunJCE());
      c = Cipher.getInstance("DES/CBC/PKCS5Padding");
      ap = AlgorithmParameters.getInstance("DES");
      byte abyte0[] = {
          4, 8, 1, 2, 3, 4, 5, 6, 7, 8
      };
      ap.init(abyte0);
      deskey = getSecretKey();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DES getInstance() {
    if (instance == null) {
      instance = new DES();
    }
    return instance;
  }

  public synchronized String encrypt(String s) {
    String s1 = "";
    try {
      c.init(1, deskey, ap);
      byte abyte0[] = c.doFinal(s.getBytes());

      s1 = new String(Base64.encode(abyte0));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return s1;
  }

  public synchronized String decrypt(String s) {
    if(s==null){
      return s;
    }

    String s1 = "";
    try {
      c.init(2, deskey, ap);
      byte abyte0[] = c.doFinal(Base64.decode(s.getBytes()));

      s1 = new String(abyte0);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return s1;
  }

  private static void genKey() {
    Object obj = null;
    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    try {
      KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
      javax.crypto.SecretKey secretkey = keygenerator.generateKey();
      FileOutputStream fileoutputstream = new FileOutputStream(
          "E:\\Esolution\\esolution-all\\eslicense\\src\\generate\\com\\todayeasy\\license\\des.key");
      ObjectOutputStream objectoutputstream = new ObjectOutputStream(
          fileoutputstream);
      objectoutputstream.writeObject(secretkey);
      objectoutputstream.flush();
      fileoutputstream.close();
    }
    catch (NoSuchAlgorithmException ne) {
      //LogManager.getLogger().error( ne.getMessage() );
    }
    catch (IOException ie) {
      //LogManager.getLogger().error( ie.getMessage() );
    }
  }

  private Key getSecretKey() {
    Key key = null;
    try {
      ObjectInputStream objectinputstream = null;
      objectinputstream = new ObjectInputStream(this.getClass().getResourceAsStream("des.key"));
      key = null;
      key = (Key) objectinputstream.readObject();
    }
    catch (IOException ie) {
      //LogManager.getLogger().error( ie.getMessage() );
    }
    catch (ClassNotFoundException ce) {
      //LogManager.getLogger().error( ce.getMessage() );
    }
    return key;
  }

  public static void main(String args[]) {
    //DES.genKey();
    DES des = DES.getInstance();

  }

}
