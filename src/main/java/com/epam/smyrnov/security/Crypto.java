package com.epam.smyrnov.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class Crypto {

    private SecretKey generateSecretKey(String secret) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(secret.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBEWithMD5AndTripleDES");
        return secretKeyFactory.generateSecret(pbeKeySpec);
    }

    private byte[] encrypt(SecretKey secretKey, String password) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        byte[] pwdb = password.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pwdb);
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
        byteArrayOutputStream.write(salt);
        byte[] input = new byte[64];
        int bytesRead;
        while ((bytesRead = byteArrayInputStream.read(input)) != -1) {
            byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null)
                byteArrayOutputStream.write(output);
        }
        byte[] output = cipher.doFinal();
        if (output != null) {
            byteArrayOutputStream.write(output);
        }
        byteArrayInputStream.close();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        System.out.println(byteArrayOutputStream.size());
        return byteArrayOutputStream.toByteArray();
    }

    private String decrypt(SecretKey secretKey, byte[] encryptedPassword) throws Exception {
        System.out.println(encryptedPassword.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encryptedPassword);
        byte[] salt = new byte[8];
        byteArrayInputStream.read(salt);
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        byte[] in = new byte[64];
        int read;
        while ((read = byteArrayInputStream.read(in)) != -1) {
            byte[] output = cipher.update(in, 0, read);
            if (output != null)
                byteArrayOutputStream.write(output);
        }
        byte[] output = cipher.doFinal();
        if (output != null)
            byteArrayOutputStream.write(output);
        byteArrayInputStream.close();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();
    }

    public static void main(String[] args) throws Exception {
        Crypto crypto = new Crypto();
        SecretKey secretKey = crypto.generateSecretKey("webApp");
        byte[] e = crypto.encrypt(secretKey, "Kek");
        System.out.println(e.length);
        secretKey = crypto.generateSecretKey("webApp");
        //System.out.println(Arrays.toString(e));
        System.out.println(crypto.decrypt(secretKey, e));

    }
}
