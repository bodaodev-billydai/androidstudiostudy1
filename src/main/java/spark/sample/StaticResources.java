package spark.sample;

import static spark.Spark.get;
import static spark.Spark .externalStaticFileLocation;
import static spark.Spark .staticFileLocation;

public class StaticResources {

	public static void main(String[] args) {
		preDock("", "");
		dock("", "");
	}

	private static int preDock(String root, String opt) {
		// Will serve all static file are under "/public" in classpath if the
		// route isn't consumed by others routes.
		// When using Maven, the "/public" folder is assumed to be in
		// "/main/resources"
		externalStaticFileLocation("/public");
		staticFileLocation("/public");
		return 0;
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}

		get("/hello", (request, response) -> {
			return "Hello World!";
		});
		return 0;
	}
}
