package spark.sample;

import static spark.Spark.connect;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.head;
import static spark.Spark.options;
import static spark.Spark.patch;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.trace;

/**
 * Example showing a very simple (and stupid) autentication filter that is
 * executed before all other resources.
 * 
 * When requesting the resource with e.g.
 * http://localhost:4567/hello?user=some&password=guy the filter will stop the
 * execution and the client will get a 401 UNAUTHORIZED with the content 'You
 * are not welcome here'
 * 
 * When requesting the resource with e.g.
 * http://localhost:4567/hello?user=foo&password=bar the filter will accept the
 * request and the request will continue to the /hello route.
 * 
 * Note: There is a second "before filter" that adds a header to the response
 * Note: There is also an "after filter" that adds a header to the response
 */
public class RootExample {

	public static void main(String[] args) {
		dock("", "");
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}

		get("/", (request, response) -> {
			return "Hello GET something!";
		});
		options("/", (request, response) -> {
			return "Hello Options are available!";
		});
		connect("/", (request, response) -> {
			return "Hello Connect is allowed!";
		});
		trace("/", (request, response) -> {
			return "Hello Trace it!";
		});
		head("/", (request, response) -> {
			return "Hello Head up!";
		});
		patch("/", (request, response) -> {
			return "Hello Patch extention!";
		});
		put("/", (request, response) -> {
			return "Hello Put goods!";
		});
		post("/", (request, response) -> {
			return "Hello Post message!";
		});
		delete("/", (request, response) -> {
			return "Hello Delete something!";
		});

		return 0;

	}
}
