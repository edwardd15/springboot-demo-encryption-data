package com.kelaskoding.converter;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

public class StringAttributeConverter implements AttributeConverter<String, String> {

    private static final String AES = "AES";
    private static final byte[] encryptionKey = "this-is-test-key".getBytes();

    private final Cipher encryptCipher;
    private final Cipher decriptCypher;

    public StringAttributeConverter() throws Exception {
        Key key = new SecretKeySpec(encryptionKey, AES);
        encryptCipher = Cipher.getInstance(AES);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decriptCypher = Cipher.getInstance(AES);
        decriptCypher.init(Cipher.DECRYPT_MODE, key);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(attribute.getBytes()));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return new String(decriptCypher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
