package spark.sample;

import static spark.Spark.before;
import static spark.Spark.halt;

import java.util.HashMap;
import java.util.Map;

import spark.Session;

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
public class SecureEntranceExample {

	private static Map<String, String> usernamePasswords = new HashMap<String, String>();

	public static void main(String[] args) {
		dock("", "");
	}

	private static int dock(String root, String opt) {
		if (root == null) {
			root = "";
		}

		usernamePasswords.put("foo", "bar");
		usernamePasswords.put("admin", "admin");

		before((request, response) -> {
			new Throwable().printStackTrace();
			System.out.println("request: " + request.url() + " ? "
					+ request.params("user"));
			String userName = request.queryParams("user");
			String userPassword = request.queryParams("password");

			String dbPassword = usernamePasswords.get(userName);
			if (!(userPassword != null && userPassword.equals(dbPassword))) {
				if (request.servletPath().equals("/")) {
					// welcome to home
				} else {
					// check session
					Session session = request.session(true);
					SampleUser user = (SampleUser) session
							.attribute("authorized_112");
					if (user == null) {
						System.out.println("Unauthorized: " + session.id()
								+ " " + session.attribute("test") + " "
								+ session.attribute("user"));
						halt(401,
								"Developing!!! Unauthorized access should be allowed to visit some public information. :"
										+ session.id());
					}
				}
			} else {
				Session session = request.session(true);
				Object olduser = session.attribute("authorized_112");
				SampleUser user;
				if (olduser == null) {
					user = new SampleUser();
					System.out.println("New User: " + session.id());
				} else {
					user = (SampleUser) olduser;
				}
				user.setName(userName);
				user.setAuth("simple auth");
				System.out.println("User old: " + session.id() + " " + user
						+ " " + session.attribute("user"));
				session.attribute("user", user);
				session.attribute("test", "test");
				System.out.println("User new: " + session.id() + " " + user
						+ " " + session.attribute("user"));
			}
		});

		// before("/hello", (request, response) -> {
		// response.header("Foo", "Set by second before filter");
		// });
		//
		// get("/hello", (request, response) -> {
		// return "Hello World!";
		// });
		//
		// after("/hello", (request, response) -> {
		// response.header("spark", "added by after-filter");
		// });
		return 0;

	}
}
