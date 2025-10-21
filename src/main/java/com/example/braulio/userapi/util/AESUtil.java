package com.example.braulio.userapi.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import jakarta.security.auth.message.callback.SecretKeyCallback;

public class AESUtil {
	private static final String SECRET_KEY="Mysuperclave12345678912346789"; //32 CARACTERES = 256 BITS
	public static String encrypt(String plainText) {
		try {
			SecretKeySpec key= new  SecretKeySpec(SECRET_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(plainText.getBytes());
			return Base64.getEncoder()
.encodeToString(encrypted)	;
			} catch (Exception e) {
				throw new RuntimeException("Error encrypting password", e);
			}
	}
}
