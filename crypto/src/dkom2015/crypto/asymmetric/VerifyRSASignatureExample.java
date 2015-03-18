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

//keytool -genkey -alias test -keyalg RSA -keystore keystore.jks -keysize 2048
//http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Signature
public class VerifyRSASignatureExample {

  private static final String KEYSTORE_NAME = "keystore.jks";
  private static final String KEY_ALIAS = "test";
  private static final String ABCD1234 = "abcd1234";
  
    public static void main(String[] args) throws Exception {
      String string = "Message";
      String signature = "m3Llr8oZEHryBA8ubi/pyqAtaNmGtRYqITO+FdD6RYuvBhqLsi+7k2X1uNrl8n8UMjBwMsT3RPh8\r\n" + 
          "A75IiRaLDE3gyaKXH4UmhWuvn50AGFNJByDCHOc0g+kUw4jo8QlUK8A39DmcBlSwUT1JgjRXtL/2\r\n" + 
          "sXSSyERG2OO6xu/WRPTHqekKrt2xt0W4nznmXViRgXf4rMWLTZ6fB2lmk7vvzsqFZF4fAWY6bezm\r\n" + 
          "e085/BPmqtri9qTnABJwIjyULEk32OzBQs2WdTXa6WNxs5a5g7Ex8ylv1ZPju2B4UoRFCGRYKq0P\r\n" + 
          "heBLmvBdRAbCYmrbDfrju1giOA4QPPQP4a4e/w==";
      byte[] signatureBytes = new BASE64Decoder().decodeBuffer(signature);
      
      KeyStore ks  = KeyStore.getInstance("JKS");
      ks.load(new FileInputStream(KEYSTORE_NAME), ABCD1234.toCharArray());
      
      byte[] data = string.getBytes("UTF8");

      Signature sig = Signature.getInstance("SHA1withRSA");
      
      sig.initVerify(ks.getCertificate(KEY_ALIAS));
      sig.update(data);

      System.out.println(sig.verify(signatureBytes));
    }
}