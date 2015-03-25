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

public class QuickRsaTask {

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

	// private members
	private RSAPrivateKey privateKey;
	private RandomAccessFile readFile;
	private RandomAccessFile writeFile;
	LinkedList<Range> range;

	public static QuickRsaTask addTask(RSAPrivateKey privateKey, File secured,
			File verify) {
		if (!inited) {
			init();
		}
		String key = privateKey.toString();
		HashMap<String, WeakReference<QuickRsaTask>> keys = rsaTaskKeys
				.get(privateKey);
		if (keys == null) {
			keys = new HashMap<String, WeakReference<QuickRsaTask>>();
			rsaTaskKeys.put(privateKey, keys);
		}

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
		return null;
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
}
