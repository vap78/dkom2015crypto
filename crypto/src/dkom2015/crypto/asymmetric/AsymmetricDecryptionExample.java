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

public class AsymmetricDecryptionExample {

    private static final String KEYSTORE_NAME = "keystore.jks";
    private static final String KEY_ALIAS = "test";
    private static final String ABCD1234 = "abcd1234";

    public static void main(String[] args) throws Exception {
      KeyStore ks  = KeyStore.getInstance("JKS");
      ks.load(new FileInputStream(KEYSTORE_NAME), ABCD1234.toCharArray());
      Key key = ks.getKey(KEY_ALIAS, ABCD1234.toCharArray());
      
      Cryptor cryptor = new Cryptor();
      System.out.println(cryptor.decrypt(key, "pMyD+u0Ex01g7U0DGyNZ9WQpyJLaQCyJK16L6ditbSDpfw5ycRO1I4qxNWiEwmKjWyFM5p8CtIm2\r\n" + 
          "kNXwynwVjqKiAz5Fs9ZJlrFRVtkgCBBh6WA+Lmy7JaXy6h66Ob4U7CQrHtnjv2MiZ3dcqH7EBVLc\r\n" + 
          "Oj1C1Wbys3KPVjyv32/oHfQZFU6r+BgLD/bgFB+FG9Urdp8ZTpCxSAaOtBFrIe2r3kYh0eIBMEBy\r\n" + 
          "283E23D6QfOKhsZEZ1nb89izOFoWWShOKwPIyhu2jaUQSvu8VmaBEYHMAplgYydYr7/C9Efn2Gf0\r\n" + 
          "zIL7Fvl/u2WckMEPvuP97zTJGix+vhHXHjhhvA=="));
    }

}
