package qrcode.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;

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
	 * @throws FormatException
	 * @throws ChecksumException
	 */
	public void testApp() throws IOException, ChecksumException,
			FormatException {
		String fName = "http://www.baidu.com/";
		QRCode q = QRCode.from(fName);
		// this is not really necessary. The default is PNG already.
		q.to(ImageType.PNG);
		File f = q.file();
		File p = new File("temp.png");
		p.createNewFile();
		copyFile(f, p);
		assertTrue(f != null);
		// com.google.zxing.qrcode.decoder.Decoder a=new
		// com.google.zxing.qrcode.decoder.Decoder();
		// boolean[][] bits = null;
		// //q.
		// a.decode(bits);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param in
	 *            源文件，包括整个文件实体
	 * @param out
	 *            目的文件，该文件对象只有文件路径与文件名
	 * @throws IOException
	 *             如果发生输入输出错误，如无源文件、路径错误等
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