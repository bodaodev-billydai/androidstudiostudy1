package qrcode.sample;

import java.io.File;
import java.security.interfaces.RSAPrivateKey;

public class QuickRsaUtils extends RsaUtils {

	/**
	 * Ω‚√‹∑Ω∑®
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	protected byte[] decrypt(RSAPrivateKey privateKey, File secured, File verify) {
		if (privateKey != null) {
			QuickRsaTask task = QuickRsaTask.addTask(privateKey, secured,
					verify);
			try {
				task.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
