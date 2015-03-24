package spark.sample;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

/**
 * A simple example just showing some basic functionality
 */
public class SimpleExample {

	public static void main(String[] args) {
		dock("", "");
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}

		// setPort(5678); <- Uncomment this if you wan't spark to listen on a
		// port different than 4567.

		get("/hello", (request, response) -> {
			return "Hello World!";
		});

		post("/hello", (request, response) -> {
			return "Hello World: " + request.body();
		});

		get("/private", (request, response) -> {
			response.status(401);
			return "Go Away!!!";
		});

		get("/users/:name", (request, response) -> {
			return "Selected user: " + request.params(":name");
		});

		get("/news/:section",
				(request, response) -> {
					response.type("text/xml");
					return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>"
							+ request.params("section") + "</news>";
				});

		get("/protected", (request, response) -> {
			halt(403, "I don't think so!!!");
			return null;
		});

		get("/redirect", (request, response) -> {
			response.redirect("/news/world");
			return null;
		});

		get("/", (request, response) -> {
			return "root";
		});
		return 0;

	}
}
