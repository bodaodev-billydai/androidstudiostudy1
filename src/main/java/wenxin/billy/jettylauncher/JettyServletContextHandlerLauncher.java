package wenxin.billy.jettylauncher;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import wenxin.billy.webappsample.HappyRobert;
import wenxin.billy.webappsample.HappyServlet;
import wenxin.billy.webappsample.HelloServlet;

public class JettyServletContextHandlerLauncher {

	public static void main(String[] args) throws Exception {
		// String resourceBase = System.getProperty("java.io.tmpdir");
		String resourceBase = "./files";
		Server server = new Server(8769);

		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.setResourceBase(resourceBase);
		server.setHandler(context);

		// Add servlets
		context.addServlet(HelloServlet.class, "/hello/*");
		context.addServlet(HappyServlet.class, "/happy/*");
		context.addServlet(HappyRobert.class, "/robot/*");
		// Add default servlet
		context.addServlet(DefaultServlet.class, "/");

		server.start();
		server.join();
	}
}
