package dkom2015.crypto.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class SecureCommunicatorECB implements SecureCommunicator {

  @Override
  public byte[] encryptMessage(SecretKey key, String message) throws Exception {
    // encrypt
    Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
    return encrypted;
  }

  @Override
  public String decryptMessage(SecretKey key, byte[] encryptedMessage) throws Exception {
    Cipher cipherForDecryption = Cipher.getInstance(key.getAlgorithm() + "/ECB/PKCS5Padding");
    cipherForDecryption.init(Cipher.DECRYPT_MODE, key);
    byte[] decrypted = cipherForDecryption.doFinal(encryptedMessage);
    return new String(decrypted, "UTF-8");
  }

}
