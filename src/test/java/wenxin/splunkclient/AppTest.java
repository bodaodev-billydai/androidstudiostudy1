package wenxin.splunkclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	App app;

	public AppTest(String testName) {
		super(testName);
		app = new App();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testBind() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(true);
	}

	public void testSearch() {
		app.bind("localhost", 8089, "admin", "a123456");
		app.connect();
		app.search("");
		app.disconnect();
		assertTrue(true);
	}

	public void testConfigureation() {
		app.config("", "");
		assertTrue(true);
	}

	public void testLogText() {
		app.log("");
		assertTrue(true);
	}

	public void testLogBinary() {
		app.log(new Byte[1]);
		assertTrue(true);
	}
}
