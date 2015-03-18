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
import java.security.KeyStore;


//keytool -genkey -alias test -keyalg RSA -keystore keystore.jks -keysize 2048
public class AsymmetricEncryptionExample {

    private static final String KEYSTORE_NAME = "keystore.jks";
    private static final String KEY_ALIAS = "test";
    private static final String ABCD1234 = "abcd1234";

    public static void main(String[] args) throws Exception {
      KeyStore ks  = KeyStore.getInstance("JKS");
      ks.load(new FileInputStream(KEYSTORE_NAME), ABCD1234.toCharArray());
      
      String data = "Message";

      Cryptor cryptor = new Cryptor();
      System.out.println(cryptor.encrypt(data, ks.getCertificate(KEY_ALIAS)));
    }
}
