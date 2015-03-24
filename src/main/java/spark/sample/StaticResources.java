package spark.sample;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class StaticResources {

	public static void main(String[] args) {

		// Will serve all static file are under "/public" in classpath if the
		// route isn't consumed by others routes.
		// When using Maven, the "/public" folder is assumed to be in
		// "/main/resources"
		staticFileLocation("/public");

		get("/hello", (request, response) -> {
			return "Hello World!";
		});
	}
}
