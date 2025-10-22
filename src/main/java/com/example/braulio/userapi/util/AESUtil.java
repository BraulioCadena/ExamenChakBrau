package com.example.braulio.userapi.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    // ✅ CLAVE EXACTA DE 32 CARACTERES - CÓPIALA COMPLETA
    private static final String SECRET_KEY = "Mysuperclave12345678901234567890"; 
    // Verificación: "Mysuperclave12345678901234567890" = 32 caracteres
    
    private static final String ALGORITHM = "AES";
    
    public static String encrypt(String plainText) {
    	  try {
    	        // ✅ DEBUG: Verificar la clave
    	        System.out.println("=== AESUtil Debug ===");
    	        System.out.println("Key: '" + SECRET_KEY + "'");
    	        System.out.println("Key length: " + SECRET_KEY.length());
    	        System.out.println("Key bytes: " + SECRET_KEY.getBytes().length);
    	        System.out.println("=====================");
    	        
    	        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
    	        Cipher cipher = Cipher.getInstance(ALGORITHM);
    	        cipher.init(Cipher.ENCRYPT_MODE, key);
    	        byte[] encrypted = cipher.doFinal(plainText.getBytes());
    	        return Base64.getEncoder().encodeToString(encrypted);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        throw new RuntimeException("Error encrypting password", e);
    	    }
    }
    
    public static String decrypt(String encryptedText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedText);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error decrypting password", e);
        }
    }
}