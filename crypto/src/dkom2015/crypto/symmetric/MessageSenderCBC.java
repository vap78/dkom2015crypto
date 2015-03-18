package dkom2015.crypto.symmetric;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dkom2015.crypto.Utils;

public class MessageSenderCBC {
  public static void main(String[] args) throws Exception {
    SecureCommunicator cbc = new SecureCommunicatorCBC();
    
    String algorithm = "AES";
    
    KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
    SecretKey symmetricKey = keyGen.generateKey();
    System.out.println("Message to encrypt: ");
    System.out.println("\t" + Constants.TO_ENCRYPT);
    byte[] encrypted = cbc.encryptMessage(symmetricKey, Constants.TO_ENCRYPT);
    System.out.println("Encrypted message:");
    System.out.println("\t" + Utils.toHex(encrypted));
    
    
    String decrypted = cbc.decryptMessage(symmetricKey, encrypted);
    System.out.println("Decrypted message:");
    System.out.println("\t" + decrypted);
  }

}
