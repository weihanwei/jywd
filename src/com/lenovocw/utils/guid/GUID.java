/***************************************
 *                                     *
 *  JBoss: The OpenSource J2EE WebOS   *
 *                                     *
 *  Distributable under LGPL license.  *
 *  See terms of license at gnu.org.   *
 *                                     *
 ***************************************/

package com.lenovocw.utils.guid;

import com.lenovocw.utils.security.MD5;


/**
 * A globally unique identifier (globally across a cluster of virtual
 * machines).
 *
 * <p>The identifier is composed of:
 * <ol>
 *    <li>The VMID for the virtual machine.</li>
 *    <li>A UID to provide uniqueness over a VMID.</li>
 * </ol>
 *
 * <pre>
 *    [ address ] - [ process id ] - [ time ] - [ counter ] - [ time ] - [ counter ]
 *                                   |------- UID --------|   |------- UID --------|
 *    |---------------------- VMID -----------------------|
 * </pre>
 *
 * @see VMID
 * @see UID
 *
 * @version <tt>$Revision: 1.1 $</tt>
 * @author  <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class GUID
    implements ID {
  /** The virtual machine identifier */
//  protected final VMID vmid;



  /**
   * Returns a GUID as a string.
   *
   * @return  GUID as a string.
   */
  public static String asString() {
    return new GUID().toString();
  }

  public static String generateGUID() {
      String guid= "g"+MD5.getDigest(new GUID().toString()).substring(8, 24);;

      return guid;
  }
  
  public static String generateGUID16() {
      String guid= "g"+MD5.getDigest(new GUID().toString()).substring(8, 24);

      return guid;
    }
}
