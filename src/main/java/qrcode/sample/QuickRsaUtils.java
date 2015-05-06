package qrcode.sample;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickRsaUtils {
	private static final int MAX_BLOCK_SIZE = 10;
	private static final int MIN_THREAD = 1;
	private static final int MAX_THREAD = 20;
	private static final long KEEP_ALIVE_TIME = 3000;
	private static final long WAIT_SHUTDOWN_TIME = 300;

	private static abstract class Worker extends RsaRangedFile implements
			Runnable {
	}

	private static class EncodeWorker extends Worker {
		public void run() {
			try {
				super.encode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static class DecodeWorker extends Worker {
		public void run() {
			try {
				super.decode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解密方法
	 * 
	 * @param publicKey
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	protected void encrypt(RSAPublicKey publicKey, File orgin, File secured)
			throws IOException {
		/* prepare files */
		RandomAccessFile o = new RandomAccessFile(orgin, "r");
		RandomAccessFile c = new RandomAccessFile(secured, "rw");
		c.setLength(0);

		/* strategy */
		long length = o.length();
		long blockcount;
		long blocksize = (length + RsaUtils.RsaOrgBlockSize - 1)
				/ RsaUtils.RsaOrgBlockSize;
		if (blocksize >= MAX_BLOCK_SIZE * MAX_THREAD) {
			blockcount = (blocksize + MAX_BLOCK_SIZE - 1) / MAX_BLOCK_SIZE;
			blocksize = RsaUtils.RsaOrgBlockSize * MAX_BLOCK_SIZE;
		} else if (blocksize <= MAX_THREAD) {
			blockcount = blocksize;
			blocksize = RsaUtils.RsaOrgBlockSize;
		} else {
			blocksize = RsaUtils.RsaOrgBlockSize
					* ((blocksize + MAX_THREAD - 1) / MAX_THREAD);
			blockcount = MAX_THREAD;
		}
		// System.out.print("l:" + length + " b:" + blocksize + " s:" +
		// blockcount
		// + " v:" + (blocksize * blockcount));
		/* prepare execution */
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(MIN_THREAD,
				MAX_THREAD, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, queue);
		// Worker worker[] = new Worker[blockcount];
		long offset = (blockcount - 1) * blocksize;
		long size = length - offset;
		// System.out.println(" f:" + offset + " s:" + size + " v:"
		// + (offset + size));
		for (int i = 0; i < blockcount; i++) {
			Worker w = new EncodeWorker();
			w.setPublicKey(publicKey);
			w.setOriginFile(o);
			w.setCodedFile(c);
			w.setOriginRange(offset, size);
			offset -= blocksize;
			size = blocksize;
			pool.execute(w);
		}
		pool.shutdown();
		try {
			pool.awaitTermination(WAIT_SHUTDOWN_TIME, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.close();
		o.close();
	}

	/**
	 * 解密方法
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	protected void decrypt(RSAPrivateKey publicKey, File orgin, File secured)
			throws IOException {
		/* prepare files */
		RandomAccessFile o = new RandomAccessFile(orgin, "r");
		RandomAccessFile c = new RandomAccessFile(secured, "rw");
		c.setLength(0);

		/* strategy */
		long length = o.length();
		long blockcount;
		long blocksize = (length + RsaUtils.RsaSecBlockSize - 1)
				/ RsaUtils.RsaSecBlockSize;
		if (blocksize >= MAX_BLOCK_SIZE * MAX_THREAD) {
			blockcount = (blocksize + MAX_BLOCK_SIZE - 1) / MAX_BLOCK_SIZE;
			blocksize = RsaUtils.RsaSecBlockSize * MAX_BLOCK_SIZE;
		} else if (blocksize <= MAX_THREAD) {
			blockcount = blocksize;
			blocksize = RsaUtils.RsaSecBlockSize;
		} else {
			blocksize = RsaUtils.RsaSecBlockSize
					* ((blocksize + MAX_THREAD - 1) / MAX_THREAD);
			blockcount = MAX_THREAD;
		}
		/* prepare execution */
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(MIN_THREAD,
				MAX_THREAD, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, queue);
		// Worker worker[] = new Worker[blockcount];
		long offset = (blockcount - 1) * blocksize;
		long size = length - offset;
		for (int i = 0; i < blockcount; i++) {
			Worker w = new DecodeWorker();
			w.setPrivateKey(publicKey);
			w.setCodedFile(o);
			w.setOriginFile(c);
			w.setCodedRange(offset, size);
			offset -= blocksize;
			size = blocksize;
			pool.execute(w);
		}
		pool.shutdown();
		try {
			pool.awaitTermination(WAIT_SHUTDOWN_TIME, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.close();
		o.close();
	}

}
