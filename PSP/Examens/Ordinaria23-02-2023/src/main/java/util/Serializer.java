package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author victor
 */
public abstract class Serializer {
	public static byte[] serialize(Object o, String algorithm) {
		byte[] objectBytes = o.toString().getBytes();

		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		return md.digest(objectBytes);
	}
}
