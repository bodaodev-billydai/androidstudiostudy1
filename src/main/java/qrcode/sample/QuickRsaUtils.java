package qrcode.sample;

import java.io.File;
import java.security.interfaces.RSAPrivateKey;

public class QuickRsaUtils extends RsaUtils {

	public class MyThread extends Thread {

		long offset;
		long length;

		public MyThread(RSAPrivateKey privateKey, File secured, File verify,
				long offset2, long length2) {
			offset = offset2;
			length = length2;
			System.out.println("part:" + offset + " " + length + " "
					+ (offset + length));
		}

		public void run() {
			System.out.println("done:" + offset);
		}

	}

	/**
	 * 解密方法
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	@Override
	protected byte[] decrypt(RSAPrivateKey privateKey, File secured, File verify) {
		long fileSize = secured.length();
		if (fileSize <= 117 * 3) {
			return super.decrypt(privateKey, secured, verify);
		}
		int blockCount = 4;
		long blockSize = ((fileSize - 1) / 117 / blockCount + 1) * 117;
		Thread thread[] = new Thread[blockCount];
		long last = fileSize;
		long offset = blockSize * (blockCount - 1);
		int index;
		for (index = blockCount; index-- > 0;) {
			thread[index] = new MyThread(privateKey, secured, verify, offset,
					last - offset);
			last = offset;
			offset -= blockSize;
			thread[index].start();
		}
		for (index = blockCount; index-- > 0;) {
			System.out.println("wait:" + index);
			try {
				thread[index].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ok:" + index);
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
	protected byte[] decrypt1(RSAPrivateKey privateKey, File secured,
			File verify) {
		if (privateKey != null) {
			System.out.println("decrypting.privateKey" + privateKey);
			QuickRsaTask task = QuickRsaTask.addTask(privateKey, secured,
					verify);
			System.out.println("task:" + task);
			if (task != null) {
				System.out.println("decrypting.");
				task.waitDone();
				System.out.println("decrypt done.");
			}
		}
		return null;
	}

}
