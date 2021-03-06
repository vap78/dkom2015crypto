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
 * Created on Mar 17, 2015 by I052264
 *   
 */

package dkom2015.crypto.passwordstore;


public class CheckPasswordExample {

  // https://crackstation.net/
  public static void main(String[] params) {
    SHA1PasswordChecker checker = new SHA1PasswordChecker();
    checker.storePassword("Alice", "Abcd1234".toCharArray());
    System.out.println(checker.checkPassword("Alice", "Abcd1234".toCharArray()));
  }

}
