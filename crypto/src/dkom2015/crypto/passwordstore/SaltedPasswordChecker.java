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

package dkom2015.crypto.passwordstore;

import static dkom2015.crypto.Utils.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltedPasswordChecker implements PasswordChecker {

  public static final String SALT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
  public static final int SALT_SECURE_RANDOM_SEED_SIZE = 10;
  public static final int SALT_SECURE_RANDOM_BYTE_SIZE = 32;

  @Override
  public void storePassword(String username, char[] password) {
    byte[] salt = generateSalt();
    String sha1HashWithSalt = toSHA1(concatenate(salt, toBytes(password)));
    Arrays.fill(password, '\u0000');
    writeProperties(username, sha1HashWithSalt, toHex(salt));
  }

  @Override
  public boolean checkPassword(String username, char[] password) {
    String storedPasswordHash = readProperties().getProperty("password");
    String storedSalt = readProperties().getProperty("salt");
    String sha1HashWithSalt = toSHA1(concatenate(fromHex(storedSalt), toBytes(password)));
    Arrays.fill(password, '\u0000');
    return storedPasswordHash.equals(sha1HashWithSalt);
  }

  public static byte[] generateSalt() {
    SecureRandom random;
    try {
      random = SecureRandom.getInstance(SALT_SECURE_RANDOM_ALGORITHM);
      byte seed[] = random.generateSeed(SALT_SECURE_RANDOM_SEED_SIZE);
      random.setSeed(seed);
      byte salt[] = new byte[SALT_SECURE_RANDOM_BYTE_SIZE];
      random.nextBytes(salt);
      return salt;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

}
