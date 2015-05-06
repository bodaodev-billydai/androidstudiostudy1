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
	// RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // ˽Կ
	// RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ��Կ
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

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testQuickEncodDecode() {
		try {
			/* prepare key pair */
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // ˽Կ
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ��Կ

			/* test it */
			String testOriginFile = "e:\\testfiles\\sampleAB.txt";
			String testCodedFile = "e:\\testfiles\\sampleAB.txt.sec";
			String testVerifyFile = "e:\\testfiles\\sampleAB.txt.ver";

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

}