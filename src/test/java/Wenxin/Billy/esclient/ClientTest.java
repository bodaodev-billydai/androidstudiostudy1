package Wenxin.Billy.esclient;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ClientTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ClientTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ClientTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testClient() {
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		client.getClient(clusterName);
		try {
			client.createIndex();
			client.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
