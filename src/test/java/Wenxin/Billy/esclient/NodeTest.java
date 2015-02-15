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
		EsNode node = new EsNode();
		try {
			node.createIndex("elasticsearchbilly");
			node.searchIndex();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
}
