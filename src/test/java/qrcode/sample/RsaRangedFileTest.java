package qrcode.sample;

import java.io.IOException;
import java.io.RandomAccessFile;
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
public class RsaRangedFileTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public RsaRangedFileTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RsaRangedFileTest.class);
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
			/* prepare key pair */
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // Ë½Ô¿
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // ¹«Ô¿

			/* prepare RsaRangedFile */
			RsaRangedFile encrypt = new RsaRangedFile();
			encrypt.setOriginRange(0, RsaUtils.RsaOrgBlockSize);
			encrypt.setCodedRange(0, RsaUtils.RsaSecBlockSize);
			encrypt.setPrivateKey(privateKey);
			encrypt.setPublicKey(publicKey);

			/* test it */
			String testOriginFile = "e:\\testfiles\\sampleAB.txt";
			String testCodedFile = "e:\\testfiles\\sampleAB.txt.sec";
			String testVerifyFile = "e:\\testfiles\\sampleAB.txt.ver";
			RandomAccessFile c = new RandomAccessFile(testCodedFile, "rw");
			RandomAccessFile o = new RandomAccessFile(testOriginFile, "r");
			RandomAccessFile v = new RandomAccessFile(testVerifyFile, "rw");

			c.setLength(0);
			v.setLength(0);
			encrypt.setOriginFile(o);
			encrypt.setCodedFile(c);
			encrypt.setOriginRange(0, RsaUtils.RsaOrgBlockSize * 2);
			encrypt.encode();
			encrypt.setOriginRange(RsaUtils.RsaOrgBlockSize * 2, o.length()
					- RsaUtils.RsaOrgBlockSize * 2);
			encrypt.encode();

			encrypt.setOriginFile(v);
			encrypt.decode();
			encrypt.setCodedRange(0, RsaUtils.RsaSecBlockSize * 2);
			encrypt.decode();
			o.close();
			c.close();
			v.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}