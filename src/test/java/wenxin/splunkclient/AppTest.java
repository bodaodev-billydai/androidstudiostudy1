package wenxin.splunkclient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	Portal app;

	public AppTest(String testName) {
		super(testName);
		app = new Portal();
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
		assertTrue(app.connect());
		app.search("");
		app.disconnect();
		assertTrue(true);
	}

	public void testUser() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.users();
		app.disconnect();
		assertTrue(true);
	}

	public void testConfig() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.configs();
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

	public void testCommand() {
		try {
			app.command(new String[] { "search * |  head 10",
					"--host=localhost", "--port=8089", "--scheme=https",
					"--username=admin", "--password=a123456" });
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(true);
	}

	public void testCreateEventType() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.createEventType("test event", "index=_internal *",
				"This is a test", 5);
		app.disconnect();
		assertTrue(true);
	}

	public void testModifyEventTypeDescription() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.updateEventType("test event", "this is a new test");
		app.disconnect();
		assertTrue(true);
	}

	public void testModifyEventTypePriority() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.updateEventType("test event", 2);
		app.disconnect();
		assertTrue(true);
	}

	public void testModifyEventType() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("description", "new desciption ");
		args.put("priority", new Integer(1));
		app.updateEventType("test event", args);
		app.disconnect();
		assertTrue(true);
	}

	public void testModifyEventType2() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("description", "new desciption 5 ");
		args.put("priority", "5");
		app.updateEventType("test event", args);
		app.disconnect();
		assertTrue(true);
	}

	public void testDestroyEventType() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		app.destroyEventType("test");
		app.destroyEventType("test event");
		app.disconnect();
		assertTrue(true);
	}

	public void testCreateApp() {
		app.bind("localhost", 8089, "admin", "a123456");
		assertTrue(app.connect());
		String a1 = app.createApp("Billy test App 1", "billy_t1", false,
				"this is a test app", "barebones");
		String a2 = app.createApp("Billy test App 2", "billy_t2", true,
				"this is a test app", "sample_app");
		app.uploadAsset(a1, new File(""));
		app.uploadAsset(a2, new File(""));
		app.disconnect();
		assertTrue(true);
	}
}
