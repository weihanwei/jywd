package com.lenovocw.utils.security.dsa;

import java.security.*;

/**
 * Generate Public/Private Key pairs and read/write them to files
 *
 * @author Steve Fowler
 * @version $Revision$
 */
public class KeyGenerator {
  private PublicKey publicKey;
  private PrivateKey privateKey;
  EncryptionUtil keygen;

  public KeyGenerator() {
    keygen = new EncryptionUtil();
  }

  /**
   * Create a public/private key pair and save the keys to the specified files
   *
   * @param publicURI file name to store public key in
   * @param privateURI file name to store private key in
   * @throws LicenseException
   */
  public void createKeys(String publicURI, String privateURI) throws
      LicenseException {
    try {
      keygen.generateKeys(publicURI, privateURI);
      publicKey = keygen.getPublic();
      privateKey = keygen.getPrivate();

      System.out.println(publicKey.toString()); // public key
      System.out.println(privateKey.toString()); // private key

    }
    catch (Exception e) {
      throw new LicenseException(e.getMessage());
    }
  }

  /**
   * Read public/private keys from specified files
   *
   * @param publicURI name of file containing the public key
   * @param privateURI name of file containing the private key
   * @throws LicenseException
   */
  public void readKeys(String publicURI, String privateURI) throws
      LicenseException {
    try {
      keygen.readKeys(publicURI, privateURI);
      publicKey = keygen.getPublic();
      privateKey = keygen.getPrivate();
    }
    catch (Exception e) {
      throw new LicenseException("Unable to read Key Files: " + e.getMessage());
    }
  }

  /**
   * read a public key from a file
   *
   * @param URI name of file containing the key
   * @throws LicenseException
   */
  public void readPublicKey(String URI) throws LicenseException {
    try {
      publicKey = keygen.readPublicKey(URI);
    }
    catch (Exception e) {
      throw new LicenseException("Unable to read Key File:" + e.getMessage());
    }
  }

  /**
   * read a private key from a file
   *
   * @param URI name of file containing the key
   * @throws LicenseException
   */
  public void readPrivateKey(String URI) throws LicenseException {
    try {
      privateKey = keygen.readPrivateKey(URI);
    }
    catch (Exception e) {
      throw new LicenseException("Unable to read Key File:" + e.getMessage());
    }
  }

  public PublicKey getPublic() {
    return publicKey;
  }

  public PrivateKey getPrivate() {
    return privateKey;
  }

  public String getPublicString() {
    return publicKey.toString();
  }

  public String getPrivateString() {
    return privateKey.toString();
  }

  public static void main(String args[]) {
    try {
      if (args.length < 3) {
        //	System.out.println("usage:  KeyGenerator publicFileName privateFileName");
        //		return;
      }
      KeyGenerator keygen = new KeyGenerator();
      keygen.createKeys("espublic.key", "esprivate.key");
    }
    catch (Exception e) {
      System.out.println("Error:" + e.getMessage());
      e.printStackTrace();
    }
  }
}
