package Wenxin.Billy.esclient;

import java.io.IOException;

import org.elasticsearch.client.transport.NoNodeAvailableException;

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
	public void testClient1() {
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		String hostName = "10.10.7.146";
		int port = 9300;
		client.getClient(clusterName, hostName, port, true);
		try {
			client.createIndex();
			client.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		client.releaseClient();
		assertTrue(true);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testClient2() {
		EsClient client = new EsClient();
		String clusterName = null;
		String hostName = "10.10.7.146";
		int port = 9300;
		client.getClient(clusterName, hostName, port, true);
		try {
			client.createIndex();
			client.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NoNodeAvailableException e) {
			assertTrue(true);
			return;
		}
		client.releaseClient();
		assertTrue(false);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testClient3() {
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		String hostName = null;
		int port = 9300;
		client.getClient(clusterName, hostName, port, true);
		try {
			client.createIndex();
			client.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NoNodeAvailableException e) {
			assertTrue(true);
			return;
		}
		client.releaseClient();
		assertTrue(false);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testClient4() {
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		int port = 9300;
		client.getClientByLocalHost(clusterName, port, true);
		try {
			client.createIndex();
			client.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		client.releaseClient();
		client.releaseNode();
		assertTrue(true);
	}
}
