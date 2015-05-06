package qrcode.sample;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaRangedFile {

	public class Range {
		public Range(long o, long l) {
			offset = o;
			length = l;
		}

		Long offset;
		Long length;
	}

	// private members
	private RSAPrivateKey privateKey;
	RSAPublicKey publicKey;
	private RandomAccessFile originFile;
	private RandomAccessFile codedFile;
	private Range originRange;
	private Range codedRange;

	public void setOriginRange(int o, long l) {
		assert (o % RsaUtils.RsaOrgBlockSize == 0);
		originRange = (new Range(o, l));
		codedRange = null;
	}

	public void setCodedRange(int o, long l) {
		assert (o % RsaUtils.RsaSecBlockSize == 0);
		codedRange = (new Range(o, l));
		originRange = null;
	}

	public void setOriginFile(RandomAccessFile o) {
		originFile = o;
	}

	public void setCodedFile(RandomAccessFile c) {
		codedFile = c;
	}

	public void setPrivateKey(RSAPrivateKey k) {
		privateKey = k;
	}

	public void setPublicKey(RSAPublicKey k) {
		publicKey = k;
	}

	Cipher cipher;
	private RandomAccessFile sourceFile;
	long sourcecursor;
	long sourcesize;
	private RandomAccessFile targetFile;
	long targetcusor;
	long targetlength;
	byte[] originBytes = new byte[RsaUtils.RsaOrgBlockSize];
	int oneblock = RsaUtils.RsaOrgBlockSize;

	private void oneblock() throws IOException, IllegalBlockSizeException,
			BadPaddingException {
		int length;
		if (sourcesize > oneblock) {
			length = oneblock;
		} else {
			length = (int) sourcesize;
		}
		synchronized (originFile) {
			// System.out.print("O: ");
			// System.out.print(sourcecursor);
			// System.out.print(", ");
			// System.out.println(sourcecursor + length);
			sourceFile.seek(sourcecursor);
			sourceFile.read(originBytes, 0, length);
			sourcecursor = sourceFile.getFilePointer();
		}
		byte[] securedBytes = cipher.doFinal(originBytes, 0, length);
		synchronized (codedFile) {
			// System.out.print("D: ");
			// System.out.print(targetcusor);
			// System.out.print(", ");
			// System.out.println(targetcusor + securedBytes.length);
			targetFile.seek(targetcusor);
			targetFile.write(securedBytes);
			targetcusor = targetFile.getFilePointer();
		}
		sourcesize -= length;
		targetlength += securedBytes.length;
	}

	private void allblock() throws IllegalBlockSizeException,
			BadPaddingException, IOException {
		for (; sourcesize > 0;) {
			oneblock();
		}

	}

	public void encode() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		/* init cipher */
		if (cipher == null) {
			cipher = Cipher.getInstance("RSA");
		}
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		/* set source */
		sourceFile = originFile;
		sourcecursor = originRange.offset;
		sourcesize = originRange.length;
		/* set target */
		targetFile = codedFile;
		long targetoffset = sourcecursor / RsaUtils.RsaOrgBlockSize
				* RsaUtils.RsaSecBlockSize;
		targetcusor = targetoffset;
		targetlength = 0;
		/* set working parameter */
		oneblock = RsaUtils.RsaOrgBlockSize;
		originBytes = new byte[oneblock];
		/* process */
		allblock();
		/* set target range */
		codedRange = new Range(targetoffset, targetlength);
		// System.out.println("Sec: " + targetoffset + ", " + targetlength);
		// System.out.println();
	}

	public void decode() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IOException,
			IllegalBlockSizeException, BadPaddingException {
		/* init cipher */
		if (cipher == null) {
			cipher = Cipher.getInstance("RSA");
		}
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		/* set source */sourceFile = codedFile;
		sourcecursor = codedRange.offset;
		sourcesize = codedRange.length;
		/* set target */
		targetFile = originFile;
		long targetoffset = sourcecursor / RsaUtils.RsaSecBlockSize
				* RsaUtils.RsaOrgBlockSize;
		targetcusor = targetoffset;
		targetlength = 0;
		/* set working parameter */
		oneblock = RsaUtils.RsaSecBlockSize;
		originBytes = new byte[oneblock];
		/* process */
		allblock();
		/* set target range */
		originRange = new Range(targetoffset, targetlength);
		// System.out.println("Sec: " + targetoffset + ", " + targetlength);
		// System.out.println();
	}
}
