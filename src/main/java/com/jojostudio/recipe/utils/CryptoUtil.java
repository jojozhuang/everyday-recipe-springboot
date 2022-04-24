package com.jojostudio.recipe.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class CryptoUtil {
  private static final int hashWidth = 256; // hash with in bits
  private static final int iterations = 200000; // number of hash iteration
  private static final int saltLength = 16;

  public static String salt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[saltLength];
    random.nextBytes(salt);
    return new String(salt, StandardCharsets.UTF_8);
  }

  public  static String hash(String password, String salt) {
    String pepper = salt; // secret key used by password encoding
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder =
        new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);
    return pbkdf2PasswordEncoder.encode(password);
  }
}
