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
	public void testSyncEncodDecode() {
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

			long length = o.length();
			long halflength = ((length + RsaUtils.RsaOrgBlockSize - 1)
					/ RsaUtils.RsaOrgBlockSize + 1)
					/ 2 * RsaUtils.RsaOrgBlockSize;

			encrypt.setOriginFile(o);
			encrypt.setCodedFile(c);
			encrypt.setOriginRange(0, halflength);
			encrypt.encode();
			encrypt.setOriginRange(halflength, length - halflength);
			encrypt.encode();

			encrypt.setOriginFile(v);
			encrypt.decode();
			encrypt.setCodedRange(0, halflength / RsaUtils.RsaOrgBlockSize
					* RsaUtils.RsaSecBlockSize);
			encrypt.decode();
			o.close();
			c.close();
			v.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testAsyncEncodDecode() {
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
			/* prepare RsaRangedFile */
			RsaRangedFile encrypt2 = new RsaRangedFile();
			encrypt2.setOriginRange(0, RsaUtils.RsaOrgBlockSize);
			encrypt2.setCodedRange(0, RsaUtils.RsaSecBlockSize);
			encrypt2.setPrivateKey(privateKey);
			encrypt2.setPublicKey(publicKey);

			/* test it */
			String testOriginFile = "e:\\testfiles\\sampleAB.txt";
			String testCodedFile = "e:\\testfiles\\sampleAB.txt.sec";
			String testVerifyFile = "e:\\testfiles\\sampleAB.txt.ver";
			RandomAccessFile c = new RandomAccessFile(testCodedFile, "rw");
			RandomAccessFile o = new RandomAccessFile(testOriginFile, "r");
			RandomAccessFile v = new RandomAccessFile(testVerifyFile, "rw");
			c.setLength(0);
			v.setLength(0);

			long length = o.length();
			long halflength = ((length + RsaUtils.RsaOrgBlockSize - 1)
					/ RsaUtils.RsaOrgBlockSize + 1)
					/ 2 * RsaUtils.RsaOrgBlockSize;

			encrypt.setOriginFile(o);
			encrypt.setCodedFile(c);
			encrypt.setOriginRange(0, halflength);
			final RsaRangedFile e1 = encrypt;
			Thread t1 = new Thread() {
				@Override
				public void run() {
					try {
						e1.encode();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t1.start();

			encrypt2.setOriginFile(o);
			encrypt2.setCodedFile(c);
			encrypt2.setOriginRange(halflength, length - halflength);
			final RsaRangedFile e2 = encrypt2;
			Thread t2 = new Thread() {
				@Override
				public void run() {
					try {
						e2.encode();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t2.start();
			t1.join();
			t2.join();

			encrypt.setOriginFile(v);
			Thread t3 = new Thread() {
				@Override
				public void run() {
					try {
						e1.decode();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t3.start();

			encrypt2.setOriginFile(v);
			Thread t4 = new Thread() {
				@Override
				public void run() {
					try {
						e2.decode();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t4.start();

			t3.join();
			t4.join();

			o.close();
			c.close();
			v.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}