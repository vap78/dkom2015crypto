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

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Arrays;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//keytool -genkey -alias test -keyalg RSA -keystore keystore.jks -keysize 2048
//http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Signature
public class RSASignatureExample {

  private static final String KEYSTORE_NAME = "keystore.jks";
  private static final String KEY_ALIAS = "test";
  private static final String ABCD1234 = "abcd1234";
  
    public static void main(String[] args) throws Exception {
      KeyStore ks  = KeyStore.getInstance("JKS");
      ks.load(new FileInputStream(KEYSTORE_NAME), "abcd1234".toCharArray());
      Key key = ks.getKey(KEY_ALIAS, ABCD1234.toCharArray());
      
      byte[] data = "Message".getBytes("UTF8");

      Signature sig = Signature.getInstance("SHA1withRSA");
      sig.initSign((PrivateKey) key);
      sig.update(data);
      byte[] signatureBytes = sig.sign();
      System.out.println("Singature:" + new BASE64Encoder().encode(signatureBytes));
    }
}
