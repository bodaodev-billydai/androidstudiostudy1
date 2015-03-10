package wenxin.splunkclient;

import com.splunk.Service;
import com.splunk.ServiceArgs;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	Service service;
	private String pwd;
	private String user;
	private int port;
	private String server;

	public void bind(String server, int port, String user, String pwd) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.pwd = pwd;

	}

	public String search(String search) {
		return "{}";
	}

	public String config(String config, String value) {
		return value;
	}

	public void log(String log) {
	}

	public void log(Byte[] log) {
	}

	public boolean connect() {
		// Create a map of arguments and add login parameters
		ServiceArgs loginArgs = new ServiceArgs();
		loginArgs = new ServiceArgs();
		loginArgs.setUsername(user);
		loginArgs.setPassword(pwd);
		loginArgs.setHost(server);
		loginArgs.setPort(port);
		// Create a Service instance and log in with the argument map
		service = Service.connect(loginArgs);
		return service != null;
	}

	public void disconnect() {
		// disconnect
		service.logout();
	}
}
