package spark.sample;

import static spark.Spark.get;

public class TransformerExample {

	public static void main(String args[]) {
		dock("", "");
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}
		get("/hello", "application/json", (request, response) -> {
			return new MyMessage("Hello World");
		}, new JsonTransformer());
		return 0;
	}

}
