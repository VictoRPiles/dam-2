import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author victor
 */
public class Hash {
    public static void main(String @NotNull [] args) {
        for (String message : args) {
            System.out.println("Original string -> " + message);

            byte[] md5 = null;
            byte[] sha1 = null;
            byte[] sha256 = null;
            byte[] sha512 = null;
            try {
                System.out.println("Generating hashes ...");
                md5 = hashString(message, "MD5");
                sha1 = hashString(message, "SHA-1");
                sha256 = hashString(message, "SHA-256");
                sha512 = hashString(message, "SHA-512");
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("... MD5     -> " + toHexString(md5));
            System.out.println("... SHA-1   -> " + toHexString(sha1));
            System.out.println("... SHA-256 -> " + toHexString(sha256));
            System.out.println("... SHA-512 -> " + toHexString(sha512));
        }
    }

    private static byte[] hashString(@NotNull String string, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(string.getBytes(StandardCharsets.UTF_8));
    }

    public static @NotNull String toHexString(byte[] hash) {
        /* Convert byte array into signum representation */
        BigInteger number = new BigInteger(1, hash);
        /* Convert message digest into hex value */
        return number.toString(16);
    }
}
