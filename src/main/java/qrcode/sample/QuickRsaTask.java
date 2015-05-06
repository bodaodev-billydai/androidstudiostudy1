package qrcode.sample;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class QuickRsaTask implements Runnable {

	public class Range {
		public Range(long offset2, long length2) {
			offset = offset2;
			length = length2;
		}

		Long offset;
		Long length;
	}

	// shared members
	private static boolean inited;
	private static HashMap<RSAPrivateKey, HashMap<String, WeakReference<QuickRsaTask>>> rsaTaskKeys;
	private static HashMap<RSAPrivateKey, RandomAccessFile> readFiles;
	private static HashMap<RSAPrivateKey, RandomAccessFile> writeFiles;
	private static ExecutorService workers;

	private static Vector<QuickRsaTask> todos;
	private static Integer countWorker = 0;

	// private members
	private RSAPrivateKey privateKey;
	private RandomAccessFile readFile;
	private RandomAccessFile writeFile;
	LinkedList<Range> range;
	private boolean done;

	public static QuickRsaTask addTask(RSAPrivateKey privateKey, File secured,
			File verify) {
		if (!inited) {
			init();
		}
		debugPrivateKey(privateKey);

		QuickRsaTask worker = getWorker(privateKey, secured, verify);
		if (worker != null) {
			worker.setDone(false);
			todos.add(worker);
			tryWorkOn();
		}
		return worker;
	}

	private static synchronized void tryWorkOn() {
		QuickRsaTask worker = todos.firstElement();
		if (worker != null) {
			workers.submit(worker);
		}
	}

	private static QuickRsaTask getWorker(RSAPrivateKey privateKey,
			File secured, File verify) {
		HashMap<String, WeakReference<QuickRsaTask>> keys = getKeyMap(privateKey);
		String iname = secured.getAbsolutePath();
		String oname = verify.getAbsolutePath();

		WeakReference<QuickRsaTask> ref = keys.get(iname);
		QuickRsaTask worker = ((ref != null) ? ref.get() : null);
		if (worker == null) {
			worker = new QuickRsaTask();
			RandomAccessFile i = null;
			RandomAccessFile o = null;
			try {
				i = new RandomAccessFile(secured, "r");
				o = new RandomAccessFile(verify, "rwd");
				worker.setPrivateKey(privateKey);
				worker.setSource(i);
				worker.setDest(o);
				worker.setCake(0, i.length());

				keys.put(iname, new WeakReference<QuickRsaTask>(worker));

				todos.add(worker);
			} catch (IOException e) {
				worker = null;
				worker = null;
				if (i != null) {
					try {
						i.close();
					} catch (IOException e1) {
					}
				}
				if (o != null) {
					try {
						o.close();
					} catch (IOException e1) {
					}
				}
			}
		}
		return worker;
	}

	private static void debugPrivateKey(RSAPrivateKey privateKey) {
		String key = privateKey.toString();
		byte[] keyBytes = privateKey.getEncoded();
		System.out.println("key: " + key);
		System.out.println("key bytes: " + keyBytes);
	}

	private static HashMap<String, WeakReference<QuickRsaTask>> getKeyMap(
			RSAPrivateKey privateKey) {
		HashMap<String, WeakReference<QuickRsaTask>> keys = rsaTaskKeys
				.get(privateKey);
		if (keys == null) {
			keys = new HashMap<String, WeakReference<QuickRsaTask>>();
			rsaTaskKeys.put(privateKey, keys);
		}
		return keys;
	}

	private void setCake(int i, long length) {
		range = new LinkedList<Range>();
		range.add(new Range(i, length));
	}

	private Range cutCake() {
		// range;
		return null;
	}

	private void returnCake() {
		// range;
	}

	private void setDest(RandomAccessFile o) {
		writeFile = o;
	}

	private void setSource(RandomAccessFile i) {
		readFile = i;
	}

	private void setPrivateKey(RSAPrivateKey privateKey2) {
		privateKey = privateKey2;
	}

	private void eatCake(Thread eater) {

	}

	private static synchronized void init() {
		inited = true;
		rsaTaskKeys = new HashMap<RSAPrivateKey, HashMap<String, WeakReference<QuickRsaTask>>>();
		workers = Executors.newFixedThreadPool(100);
		todos = new Vector<QuickRsaTask>();
	}

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		setDone(true);
	}

	private synchronized void setDone(boolean newState) {
		done = newState;
	}

	public boolean waitDone() {
		for (; !done;) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
