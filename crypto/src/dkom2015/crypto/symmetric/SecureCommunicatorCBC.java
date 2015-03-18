package dkom2015.crypto.symmetric;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SecureCommunicatorCBC implements SecureCommunicator {

  private static final int SEED_SIZE = 16;

  @Override
  public byte[] encryptMessage(SecretKey key, String message) throws Exception {
    SecureRandom secureRandom = new SecureRandom();
    byte [] ivSeed = secureRandom.generateSeed(16);

    Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
    IvParameterSpec iv = new IvParameterSpec(ivSeed);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
    byte[] encrypted = cipher.doFinal(message.getBytes());
    byte[] toReturn = new byte[ivSeed.length + encrypted.length];
    System.arraycopy(ivSeed, 0, toReturn, 0, SEED_SIZE);
    System.arraycopy(encrypted, 0, toReturn, SEED_SIZE, encrypted.length);
    return toReturn;
  }

  @Override
  public String decryptMessage(SecretKey key, byte[] encryptedMessage) throws Exception {
    byte[] ivSeed = new byte[SEED_SIZE];
    System.arraycopy(encryptedMessage, 0, ivSeed, 0, SEED_SIZE);
    byte[] encrypted = new byte[encryptedMessage.length - SEED_SIZE];
    
    System.arraycopy(encryptedMessage, SEED_SIZE, encrypted, 0, encrypted.length);
    IvParameterSpec receivedIv = new IvParameterSpec(ivSeed);
    Cipher cipherForDecryption = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
    cipherForDecryption.init(Cipher.DECRYPT_MODE, key, receivedIv);
    byte[] decrypted = cipherForDecryption.doFinal(encrypted);
    return new String(decrypted, "UTF-8");
  }

}
