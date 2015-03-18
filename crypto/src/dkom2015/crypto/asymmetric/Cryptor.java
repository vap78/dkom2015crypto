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
 
package dkom2015.crypto.asymmetric;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.cert.Certificate;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#impl
public class Cryptor {
  

  private static final String RSA_ECB_PKCS1PADDING = "RSA/ECB/PKCS1PADDING";

  public String encrypt(String rawData, Certificate certificate)
      throws IOException, GeneralSecurityException {

    Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING);
    cipher.init(Cipher.ENCRYPT_MODE, certificate);
    
  return new BASE64Encoder().encode(cipher.doFinal(rawData.getBytes()));
}

public String decrypt(Key key, String encryptedTest)
      throws IOException, GeneralSecurityException {

  Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING);
  cipher.init(Cipher.DECRYPT_MODE, key);
  
  return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptedTest)));
}

}
