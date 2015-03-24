package spark.sample;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class FreeMarkerExample {

	public static void main(String args[]) {
		dock("", "");
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}

		get("/framemarker", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("message", "Hello World!");
			//URL f = FreeMarkerExample.class.getClassLoader().getResource("hello.ftl");

			// The hello.ftl file is located in directory:
			// src/test/resources/spark/template/freemarker
				return new ModelAndView(attributes, "hello.ftl");
			}, new FreeMarkerEngine());
		return 0;
	}

}