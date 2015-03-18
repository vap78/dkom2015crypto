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

package dkom2015.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

public class Utils {
  public static String toSHA1(byte[] convertme) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return Utils.toHex(md.digest(convertme));
  }
  
  public static void writeProperties(String username, String password) {
    writeProperties(username, password, null);
  }
  
  public static void writeProperties(String username, String password, String salt) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {

      output = new FileOutputStream("passwords.properties");

      // set the properties value
      prop.setProperty("username", username);
      prop.setProperty("password", password);
      if (salt != null) {
        prop.setProperty("salt", salt);
      }

      // save properties to project root folder
      prop.store(output, null);

    } catch (Exception io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }
  }

  public static Properties readProperties() {
    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream("passwords.properties");

      // load a properties file
      prop.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return prop;
  }
  
  public static String toHex(byte[] byteStr) {
    StringBuilder res = new StringBuilder();
    for (byte currentByte : byteStr) {
      res.append(String.format("%02x", currentByte));
    }
    return res.toString().toLowerCase();
  }
  
  public static byte[] fromHex(String hexStr) {
    int byteSize = 2;
    byte[] result = new byte[hexStr.length() / byteSize];
    for (int i = 0; i < hexStr.length() / byteSize; i++) {
      String current = hexStr.substring(byteSize * i, byteSize * (i + 1));
      result[i] = (byte) Integer.parseInt(current, 16);
    }
    return result;
  }
  
  public static byte[] concatenate(byte[] arr1, byte[] arr2) {
    byte[] result = new byte[arr1.length + arr2.length];
    System.arraycopy(arr1, 0, result, 0, arr1.length);
    System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
    return result;
  }
  
  public static byte[] toBytes(char[] chars) {
    CharBuffer charBuffer = CharBuffer.wrap(chars);
    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
            byteBuffer.position(), byteBuffer.limit());
    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
    return bytes;
}
  
}
