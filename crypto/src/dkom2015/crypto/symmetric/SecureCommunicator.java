package dkom2015.crypto.symmetric;

import javax.crypto.SecretKey;

public interface SecureCommunicator {

  public byte[] encryptedMessage(SecretKey key, String message) throws Exception;
  
  public String decryptMessage(SecretKey key, byte[] encryptedMessage) throws Exception;
}
