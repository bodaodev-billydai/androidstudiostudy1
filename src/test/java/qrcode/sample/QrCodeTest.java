package qrcode.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * Unit test for simple App.
 */
public class QrCodeTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public QrCodeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(QrCodeTest.class);
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 */
	public void testApp() throws IOException {
		String fName = "http://www.baidu.com/";
		QRCode q = QRCode.from(fName);
		// this is not really necessary. The default is PNG already.
		q.to(ImageType.PNG);
		File f = q.file();
		File p = new File("temp.png");
		p.createNewFile();
		copyFile(f, p);
		assertTrue(f != null);
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