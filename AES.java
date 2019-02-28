// Tools for encryption

package encryption;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public SecretKeySpec makeSecretKey(String secretKey) {	
		try {
			byte[] key = secretKey.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			SecretKeySpec secret = new SecretKeySpec(key, "AES");
			return secret;
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public String encrypt(String toEncrypt, String secretKey) {
		try {
			SecretKeySpec key = makeSecretKey(secretKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String decrypt(String toDecrypt, String secretKey) {
		try {
			SecretKeySpec key = makeSecretKey(secretKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public void encryptFile(String file, String secretKey) throws Exception {
		byte[] array = Files.readAllBytes(new File(file).toPath());
		SecretKeySpec key = makeSecretKey(secretKey);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		array = cipher.doFinal(array);
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(array);
		fos.close();
	}
	
	public void decryptFile(String file, String secretKey) throws Exception {
		byte[] array = Files.readAllBytes(new File(file).toPath());
		SecretKeySpec key = makeSecretKey(secretKey);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, key);
		array = cipher.doFinal(array);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(array);
		fos.close();
		
	}	
}
