package spark.sample;

import static spark.Spark.after;
import static spark.Spark.get;

/**
 * Example showing the use of attributes
 */
public class FilterExampleAttributes {

	public static void main(String[] args) {
		dock("", "");
	}

	private static String asXml(String name, Object value) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><" + name + ">"
				+ value + "</" + name + ">";
	}

	private static boolean dock(String root, String opt) {
		if (root == null) {
			root = "";
		}
		get(root + "/hi", (request, response) -> {
			request.attribute("foo", "bar");
			return null;
		});

		after(root + "/hi", (request, response) -> {
			for (String attr : request.attributes()) {
				System.out.println("attr: " + attr);
			}
		});

		after(root + "/hi", (request, response) -> {
			Object foo = request.attribute("foo");
			response.body(asXml("foo", foo));
		});
		return true;
	}

}
