package wenxin.billyspring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testBeanFactor() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "/conf/spring.xml" });
		Server server = (Server) context.getBean("Server");
		Handler[] handlers = server.getHandlers();
		assertTrue(true);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testEnv() {
		Properties ps = System.getProperties();
		Iterator<Entry<Object, Object>> it = ps.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			if (entry.getKey().toString().startsWith("java")) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}
		assertTrue(true);
	}

	public void testResource() throws IOException {
		InputStream is = getClass().getResourceAsStream(
				"/res/application.properties");
		int i = is.read();
		System.out.print("resource appliation.properties: " + i);
	}
}
