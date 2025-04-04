package br.com.teixeira.persistence.general.type;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serial;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public final class Password implements Serializable {

    @Serial
    private static final long serialVersionUID = 2282373776555954748L;

    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int MEMORY = 19456;
    private static final int PARALLELISM = 1;

    private final byte[] salt;
    private final byte[] hashedPassword;

    private Password(byte[] salt, byte[] hashedPassword) {
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }

    public static Password generate(char[] password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        Arrays.fill(password, ' ');
        return new Password(salt, hashedPassword);
    }

    public static Password fromHashed(byte[] salt, byte[] hashedPassword) {
        return new Password(salt, hashedPassword);
    }

    public boolean check(char[] password) {
        byte[] canditateHash = hashPassword(password, salt);
        Arrays.fill(password, ' ');
        return Arrays.equals(canditateHash, hashedPassword);
    }

    private static byte[] hashPassword(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("Argon2id");
            return keyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generate hash:" + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(this.hashedPassword);
    }
}
