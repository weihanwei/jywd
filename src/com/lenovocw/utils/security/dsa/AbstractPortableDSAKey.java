package com.lenovocw.utils.security.dsa;

import java.math.*;
import java.security.interfaces.*;

public abstract class AbstractPortableDSAKey implements DSAKey, DSAParams {

  public AbstractPortableDSAKey(String algorithm, BigInteger p, BigInteger q,
                                BigInteger g, BigInteger extra) {
    this.algorithm = algorithm;
    this.p = p;
    this.q = q;
    this.g = g;
    this.extra = extra;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public String getFormat() {
    return null;
  }

  public byte[] getEncoded() {
    return null;
  }

  public DSAParams getParams() {
    return this;
  }

  public BigInteger getP() {
    return p;
  }

  public BigInteger getQ() {
    return q;
  }

  public BigInteger getG() {
    return g;
  }

  protected String algorithm;
  protected BigInteger p;
  protected BigInteger q;
  protected BigInteger g;
  protected BigInteger extra;
}
