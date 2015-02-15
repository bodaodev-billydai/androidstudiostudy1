package Wenxin.Billy.esclient;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

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
		System.out.println("testClient1");
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		String hostName = "10.10.7.146";
		int port = 9300;
		String indexName = "productindex";
		String typeName = "productType";
		client.open(clusterName, hostName, port, true);
		try {
			XContentBuilder doc = XContentFactory.jsonBuilder().startObject()
					.field("title", "this is a title!")
					.field("description", "descript what?").field("price", 100)
					.field("onSale", true).field("type", 1)
					.field("createDate", new Date()).endObject();
			client.createIndex(indexName, typeName, doc);
			client.searchIndex(indexName);
		} catch (IOException e) {
			fail();
		}
		try {
			client.close();
		} catch (Exception e) {
			fail();
		}
		System.out.println();
	}

	/**
	 * fail case Test :-)
	 */
	public void testClient2() {
		System.out.println("testClient2");
		EsClient client = new EsClient();
		String clusterName = null;
		String hostName = "10.10.7.146";
		int port = 9300;
		String indexName = "productindex";
		String typeName = "productType";
		client.open(clusterName, hostName, port, true);
		try {
			XContentBuilder doc = XContentFactory.jsonBuilder().startObject()
					.field("title", "this is a title!")
					.field("description", "descript what?").field("price", 100)
					.field("onSale", true).field("type", 1)
					.field("createDate", new Date()).endObject();
			client.createIndex(indexName, typeName, doc);
			client.searchIndex(indexName);
		} catch (IOException e) {
			fail();
		} catch (NoNodeAvailableException e) {
		}
		try {
			client.close();
		} catch (Exception e) {
			fail();
		}
		System.out.println();
	}

	/**
	 * fail case Test :-)
	 */
	public void testClient3() {
		System.out.println("testClient3");
		EsClient client = new EsClient();
		String clusterName = "elasticsearchbilly";
		String hostName = null;
		int port = 9300;
		String indexName = "productindex";
		String typeName = "productType";
		client.open(clusterName, hostName, port, true);
		try {
			XContentBuilder doc = XContentFactory.jsonBuilder().startObject()
					.field("title", "this is a title!")
					.field("description", "descript what?").field("price", 100)
					.field("onSale", true).field("type", 1)
					.field("createDate", new Date()).endObject();
			client.createIndex(indexName, typeName, doc);
			client.searchIndex(indexName);
		} catch (IOException e) {
			fail();
		} catch (NoNodeAvailableException e) {
		}
		try {
			client.close();
		} catch (Exception e) {
			fail();
		}
		System.out.println();
	}
}
