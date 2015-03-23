package qrcode.sample;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;

/**
 * Unit test for simple App.
 */
public class ClipBoardTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ClipBoardTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ClipBoardTest.class);
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws UnsupportedFlavorException
	 * 
	 * @throws IOException
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testApp() throws UnsupportedFlavorException, IOException {
		QrCodeView frame = new QrCodeView();
		Clipboard clipboard = frame.getToolkit().getSystemClipboard();// ��ȡϵͳ������
		String str = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.printf("%s", str);

		QRCode q = QRCode.from(str);
		// this is not really necessary. The default is PNG already.
		q.to(ImageType.PNG);
		File f = q.file();
		File p = new File("temp.png");
		p.createNewFile();
		copyFile(f, p);
		assertTrue(f != null);
		frame.setText(str);
		frame.setSize(800, 600);
		frame.importBitmap(f);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// }
		// verified by online tool http://tool.oschina.net/qr?type=2
	}

	/**
	 * �����ļ�
	 * 
	 * @param in
	 *            Դ�ļ������������ļ�ʵ��
	 * @param out
	 *            Ŀ���ļ������ļ�����ֻ���ļ�·�����ļ���
	 * @throws IOException
	 *             ����������������������Դ�ļ���·�������
	 */
	public static void copyFile(File in, File out) throws IOException {
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		byte[] buf = new byte[1024];
		int i = 0;
		while ((i = fis.read(buf)) != -1) {
			fos.write(buf, 0, i);
		}
		fis.close();
		fos.close();
	}

}