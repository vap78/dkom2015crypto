package dkom2015.crypto.symmetric;

import javax.crypto.SecretKey;

public interface SecureCommunicator {
  /**
   * Encrypts the given message with the given key
   * 
   * @param key the secret key to encrypt the message with
   * @param message the message to encrypt
   * 
   * @return the encrypted data
   * 
   * @throws Exception
   */
  public byte[] encryptMessage(SecretKey key, String message) throws Exception;
  
  /**
   * Decrypts the provided data with the provided key
   *  
   * @param key the key to be used in the decryption 
   * @param encryptedMessage the message to be decrypted
   * 
   * @return the decrypted result
   * 
   * @throws Exception
   */
  public String decryptMessage(SecretKey key, byte[] encryptedMessage) throws Exception;
}
