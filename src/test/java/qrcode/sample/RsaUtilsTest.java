package qrcode.sample;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;

/**
 * Unit test for simple App.
 */
public class RsaUtilsTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public RsaUtilsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RsaUtilsTest.class);
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testRamEncodDecode() {
		try {
			RsaUtils encrypt = new RsaUtils();
			String encryptText = "12345678";
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // Ë½Ô¿
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ¹«Ô¿
			byte[] e = encrypt.encrypt(publicKey, encryptText.getBytes());
			byte[] de = encrypt.decrypt(privateKey, e);
			System.out.println(encrypt.bytesToString(e));
			System.out.println();

			System.out.println(encrypt.bytesToString(de));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rigourous Test :-)
	 * @throws NoSuchAlgorithmException 
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void singleTestFileEncodDecode(RsaUtils encrypt, String name) throws NoSuchAlgorithmException {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // Ë½Ô¿
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ¹«Ô¿
			File origin = new File(name);
			File secured = new File(name + ".sec");
			File verify = new File(name + ".ver");
			encrypt.encrypt(publicKey, origin, secured);
			encrypt.decrypt(privateKey, secured, verify);
		} finally {
			System.out.println(name);
		}
	}

	/**
	 * Rigourous Test :-)
	 * @throws NoSuchAlgorithmException 
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testFileEncodDecode1() throws NoSuchAlgorithmException {
		singleTestFileEncodDecode(new QuickRsaUtils(),
				"E:\\testfiles\\sample.txt");
	}

	/**
	 * Rigourous Test :-)
	 * @throws NoSuchAlgorithmException 
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testFileEncodDecode2() throws NoSuchAlgorithmException {
		singleTestFileEncodDecode(new QuickRsaUtils(),
				"E:\\testfiles\\sample2.txt");
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testFileEncodDecode3() {
		// singleTestFileEncodDecode( new
		// RsaUtils(),"E:\\testfiles\\3d8692bae0da0f35178e60e2e3c03be0");
	}

}