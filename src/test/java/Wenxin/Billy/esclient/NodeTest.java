package Wenxin.Billy.esclient;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class NodeTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public NodeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NodeTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testNode() {
		System.out.println("testNode");
		EsNode node = new EsNode();
		node.open("elasticsearchbilly", true);
		try {
			node.createIndex();
			node.searchIndex();
		} catch (IOException e) {
			fail();
		}
		try {
			node.close();
		} catch (Exception e) {
			fail();
		}
		System.out.println();
	}
}
