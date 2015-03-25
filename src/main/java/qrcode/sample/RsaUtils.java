package qrcode.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class RsaUtils {
	static final int RsaOrgBlockSize = 117;
	static final int RsaSecBlockSize = 128;

	/**
	 * byte数组转为string
	 * 
	 * @param encrytpByte
	 * @return
	 */
	protected String bytesToString(byte[] encrytpByte) {
		String result = "";
		for (Byte bytes : encrytpByte) {
			result += (char) bytes.intValue();
		}
		return result;
	}

	private void assureRead(InputStream i, byte[] originBytes, int offset,
			int remined) throws IOException {
		while (0 < remined) {
			int read = i.read(originBytes, offset, remined);
			if (read < 0) {
				throw new IOException("early EOF");
			}
			remined -= read;
			offset += read;
		}

	}

	/**
	 * 加密方法
	 * 
	 * @param publicKey
	 * @param obj
	 * @return
	 */
	protected byte[] encrypt(RSAPublicKey publicKey, byte[] obj) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解密方法
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	protected byte[] decrypt(RSAPrivateKey privateKey, byte[] obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解密方法
	 * 
	 * @param publicKey
	 * @param obj
	 * @return
	 */
	protected byte[] encrypt(RSAPublicKey publicKey, File orgin, File secured) {
		if (publicKey != null) {
			InputStream i = null;
			FileOutputStream o = null;
			try {
				i = new FileInputStream(orgin);
				o = new FileOutputStream(secured);
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				byte[] originBytes = new byte[RsaOrgBlockSize];
				for (;;) {
					int remined = i.available();
					if (remined <= 0) {
						break;
					}
					if (remined > RsaOrgBlockSize) {
						remined = RsaOrgBlockSize;
					}
					assureRead(i, originBytes, 0, remined);
					byte[] securedBytes = cipher.doFinal(originBytes, 0,
							remined);
					o.write(securedBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (o != null) {
					try {
						o.close();
					} catch (IOException e) {
					}
				}
				if (i != null) {
					try {
						i.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return null;
	}

	/**
	 * 解密方法
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	protected byte[] decrypt(RSAPrivateKey privateKey, File secured, File verify) {
		if (privateKey != null) {
			InputStream i = null;
			FileOutputStream o = null;
			try {
				i = new FileInputStream(secured);
				o = new FileOutputStream(verify);
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				byte[] originBytes = new byte[RsaSecBlockSize];
				for (;;) {
					int remined = i.available();
					if (remined <= 0) {
						break;
					}
					if (remined > RsaSecBlockSize) {
						remined = RsaSecBlockSize;
					}
					assureRead(i, originBytes, 0, remined);
					byte[] securedBytes = cipher.doFinal(originBytes, 0,
							remined);
					o.write(securedBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (o != null) {
					try {
						o.close();
					} catch (IOException e) {
					}
				}
				if (i != null) {
					try {
						i.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return null;
	}
}
