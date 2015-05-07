package qrcode.sample;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
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
public class QuickRsaUtilsTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public QuickRsaUtilsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(QuickRsaUtilsTest.class);
	}

	// /**
	// * Rigourous Test :-)
	// *
	// * @throws IOException
	// * @throws FormatException
	// * @throws ChecksumException
	// */
	// public void testRefNormalEncodDecode() {
	// try {
	// /* prepare key pair */
	// KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	// keyPairGen.initialize(1024);
	// KeyPair keyPair = keyPairGen.generateKeyPair();
	// // Generate keys
	// RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // Ë½Ô¿
	// RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ¹«Ô¿
	//
	// /* test it */
	// String testOriginFile = "e:\\testfiles\\sampleAB.txt";
	// String testCodedFile = "e:\\testfiles\\sampleAB.txt.sec";
	// String testVerifyFile = "e:\\testfiles\\sampleAB.txt.ver";
	//
	// //QuickRsaUtils encoder = new QuickRsaUtils();
	// File orgin = new File(testOriginFile);
	// File secured = new File(testCodedFile);
	// File verify = new File(testVerifyFile);
	// RsaUtils.encrypt(publicKey, orgin, secured);
	// // encoder.encrypt(publicKey, orgin, secured);
	//
	// RsaUtils.decrypt(privateKey, secured, verify);
	//
	// // encoder.decrypt(privateKey, secured, verify);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	private void aQuickEncodDecode(String testOriginFile, String testCodedFile,
			String testVerifyFile) {
		try {
			/* prepare key pair */
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // Ë½Ô¿
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ¹«Ô¿

			/* test it */

			QuickRsaUtils encoder = new QuickRsaUtils();
			File orgin = new File(testOriginFile);
			File secured = new File(testCodedFile);
			File verify = new File(testVerifyFile);
			encoder.encrypt(publicKey, orgin, secured);

			// RsaUtils.decrypt(privateKey, secured, verify);

			encoder.decrypt(privateKey, secured, verify);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testQuickEncodDecode1() {
		aQuickEncodDecode("e:\\testfiles\\sampleAB1.txt",
				"e:\\testfiles\\sampleAB1.txt.sec",
				"e:\\testfiles\\sampleAB1.txt.ver");

	}

	public void testQuickEncodDecode2() {
		aQuickEncodDecode("e:\\testfiles\\sampleAB2.txt",
				"e:\\testfiles\\sampleAB2.txt.sec",
				"e:\\testfiles\\sampleAB2.txt.ver");

	}

	public void testQuickEncodDecode3() {
		aQuickEncodDecode("e:\\testfiles\\sampleAB3.txt",
				"e:\\testfiles\\sampleAB3.txt.sec",
				"e:\\testfiles\\sampleAB3.txt.ver");

	}

	public void testQuickEncodDecode4() {
		aQuickEncodDecode("e:\\testfiles\\sampleAB4.txt",
				"e:\\testfiles\\sampleAB4.txt.sec",
				"e:\\testfiles\\sampleAB4.txt.ver");

	}

}