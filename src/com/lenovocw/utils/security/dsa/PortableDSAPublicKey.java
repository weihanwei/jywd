package com.lenovocw.utils.security.dsa;

import java.math.*;
import java.security.interfaces.*;

public class PortableDSAPublicKey
    extends AbstractPortableDSAKey
    implements DSAPublicKey {

  public PortableDSAPublicKey(String algorithm, BigInteger p, BigInteger q,
                              BigInteger g, BigInteger y) {
    super(algorithm, p, q, g, y);
  }

  public BigInteger getY() {
    return super.extra;
  }
}
