/**
 * Copyright (c) 2015 by SAP Labs Bulgaria,
 * url: http://www.sap.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of SAP AG, Walldorf. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with SAP.
 * 
 * Created on Mar 18, 2015 by I052264
 *   
 */
 
package crypto;

import static crypto.Utils.*;

import java.util.Arrays;

public class SHA1PasswordChecker implements PasswordChecker {

  @Override
  public void storePassword(String username, char[] password) {
    String sha1Hash = toSHA1(toBytes(password));
    Arrays.fill(password, '\u0000');
    writeProperties(username, sha1Hash);
  }

  @Override
  public boolean checkPassword(String username, char[] password) {
    String sha1Hash = toSHA1(toBytes(password));
    Arrays.fill(password, '\u0000');
    String storedPasswordHash = readProperties().getProperty("password");
    return storedPasswordHash.equals(sha1Hash);
  }

}
