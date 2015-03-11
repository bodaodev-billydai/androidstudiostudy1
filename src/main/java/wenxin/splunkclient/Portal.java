package wenxin.splunkclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.splunk.Application;
import com.splunk.Args;
import com.splunk.CollectionArgs;
import com.splunk.Command;
import com.splunk.ConfCollection;
import com.splunk.Entity;
import com.splunk.EntityCollection;
import com.splunk.EventType;
import com.splunk.EventTypeCollection;
import com.splunk.HttpException;
import com.splunk.Service;
import com.splunk.ServiceArgs;
import com.splunk.User;

/**
 * Hello world!
 *
 */
public class Portal {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	Service service;
	private String pwd;
	private String user;
	private int port;
	private String server;
	Integer connection_count;

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
		try {
			service = Service.connect(loginArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return service != null;
	}

	public void disconnect() {
		// disconnect
		service.logout();
	}

	public void users() {
		for (User user : service.getUsers().values())
			System.out.println(user.getName() + " " + user.getPassword() + " "
					+ user.getDefaultApp());
	}

	public void configs() {
		int count = 30;
		int offset = 0;
		ConfCollection confs;
		CollectionArgs collargs = new CollectionArgs();
		collargs.setCount(count);
		collargs.setOffset(offset);

		confs = service.getConfs(collargs);
		// Got the first 30 elements
		for (EntityCollection<Entity> conf : confs.values()) {
			System.out.println(conf.getName());
			// for (Entry<String, Entity> entity : conf.entrySet()) {
			// System.out.println(entity.getKey() + " "
			// + entity.getValue().toString());
			// }
		}
		System.out.println();

		// offset = offset + 30;
		// collargs.setOffset(offset);
		// confs = service.getConfs(collargs);
		// // Got the next 30 elements
		// for (EntityCollection<Entity> conf : confs.values()) {
		// System.out.println(conf.getName());
		// // for (Entry<String, Entity> entity : conf.entrySet()) {
		// // System.out.println(entity.getKey() + " "
		// // + entity.getValue().toString());
		// // }
		// }
		// System.out.println();
		//
		// offset = offset + 30;
		// collargs.setOffset(offset);
		// confs = service.getConfs(collargs);
		// // Got the next 30 elements
		// for (EntityCollection<Entity> conf : confs.values()) {
		// System.out.println(conf.getName());
		// // for (Entry<String, Entity> entity : conf.entrySet()) {
		// // System.out.println(entity.getKey() + " "
		// // + entity.getValue().toString());
		// // }
		// }
		// System.out.println();
	}

	void command(String[] args) throws IOException {
		Command command = Command.splunk("search");
		command.parse(args);
		if (command.args.length != 1)
			Command.error("Search expression required");
		String query = command.args[0];

		Service service = Service.connect(command.opts);

		// Check the syntax of the query.
		try {
			Args parseArgs = new Args("parse_only", true);
			service.parse(query, parseArgs);
		} catch (HttpException e) {
			String detail = e.getDetail();
			Command.error("query '%s' is invalid: %s", query, detail);
		}

		// This is the simplest form of searching splunk. Note that additional
		// arguments are allowed, but they are not shown in this example.
		InputStream stream = service.oneshotSearch(query);

		InputStreamReader reader = new InputStreamReader(stream, "UTF8");
		try {
			OutputStreamWriter writer = new OutputStreamWriter(System.out);
			try {
				int size = 1024;
				char[] buffer = new char[size];
				while (true) {
					int count = reader.read(buffer);
					if (count == -1)
						break;
					writer.write(buffer, 0, count);
				}

				writer.write("\n");
			} finally {
				writer.close();
			}
		} finally {
			reader.close();
		}
	}

	public String createApp(String name, String folder, boolean visible,
			String description, String template) {
		// Application app;
		// EntityCollection<Application> apps = service.getApplications();
		return name;
	}

	public void uploadAsset(String a2, File file) {
	}

	public String createEventType(String name, String filter,
			String description, int priority) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		EventType eventType = eventTypeCollection.create(name, filter);
		eventType.setDescription(description);
		eventType.setPriority(priority);
		eventType.update();
		return name;
	}

	public void updateEventType(String name, String description) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		EventType eventType = eventTypeCollection.get(name);
		eventType.setDescription(description);
		eventType.update();
	}

	public void updateEventType(String name, int priority) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		EventType eventType = eventTypeCollection.get(name);
		eventType.setPriority(priority);
		eventType.update();

	}

	public void updateEventType(String name, String description, int priority) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		EventType eventType = eventTypeCollection.get(name);
		eventType.setDescription(description);
		eventType.setPriority(priority);
		eventType.update();
	}

	public String destroyEventType(String name) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		eventTypeCollection.remove(name);
		eventTypeCollection.refresh();
		return name;
	}

	public void updateEventType(String name, Map<String, Object> argPairs) {
		EventTypeCollection eventTypeCollection = service.getEventTypes();
		EventType eventType = eventTypeCollection.get(name);
		Args args = new Args();
		for (Object o : argPairs.entrySet()) {
			if (o instanceof Entry) {
				@SuppressWarnings("unchecked")
				Entry<String, Object> e = (Entry<String, Object>) o;
				Object v = e.getValue();
				if (v instanceof Integer) {
					args.put((String) e.getKey(), ((Integer) v).intValue());
				} else {
					args.put((String) e.getKey(), e.getValue());
				}
			}
		}
		eventType.update(args);
	}
}
