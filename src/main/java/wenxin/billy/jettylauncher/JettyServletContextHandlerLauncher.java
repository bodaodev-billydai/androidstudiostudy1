package wenxin.billy.jettylauncher;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import wenxin.billy.webappsample.HappyRobert;
import wenxin.billy.webappsample.HappyServlet;
import wenxin.billy.webappsample.HelloServlet;
import wenxin.billy.webappsample.UserManagement;

public class JettyServletContextHandlerLauncher {

	public static void main(String[] args) throws Exception {
		// String resourceBase = System.getProperty("java.io.tmpdir");
		String resourceBase = "./files";
		Server server = new Server(8769);

		// Setup JMX
		MBeanContainer mbContainer = new MBeanContainer(
				ManagementFactory.getPlatformMBeanServer());
		server.addBean(mbContainer);
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.setResourceBase(resourceBase);
		server.setHandler(context);

		// Add servlets
		context.addServlet(HelloServlet.class, "/hello/*");
		context.addServlet(HappyServlet.class, "/happy/*");
		context.addServlet(HappyRobert.class, "/robot/*");
		context.addServlet(UserManagement.class, "/login/*");
		// Add default servlet
		context.addServlet(DefaultServlet.class, "/");

		// Configure a LoginService
		// Since this example is for our test webapp, we need to setup a
		// LoginService so this shows how to create a very simple hashmap based
		// one. The name of the LoginService needs to correspond to what is
		// configured in the webapp's web.xml and since it has a lifecycle of
		// its own we register it as a bean with the Jetty server object so it
		// can be started and stopped according to the lifecycle of the server
		// itself.
		HashLoginService loginService = new HashLoginService();
		loginService.setName("Test Realm");
		loginService.setConfig("src/test/resources/realm.properties");
		server.addBean(loginService);
		
		ConcurrentMap<String, UserIdentity> users = loginService.getUsers();
		UserIdentity billy=new UserIdentity();
		users.put("billy",billy);
		loginService.setUsers(users);
		server.start();
		server.join();
	}
}
