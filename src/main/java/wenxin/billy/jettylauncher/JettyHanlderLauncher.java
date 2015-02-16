package wenxin.billy.jettylauncher;

import org.eclipse.jetty.server.Server;

import wenxin.billy.webappsample.HelloHandler;

public class JettyHanlderLauncher {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8769);

		server.setHandler(new HelloHandler("Hello. How are you!", "Contact me."));

		server.start();
		server.join();
	}

}
