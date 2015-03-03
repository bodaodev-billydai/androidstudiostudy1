package wenxin.billyspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServer {
	public static void main(String[] args) throws Exception {
		{
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "/conf/spring.xml" }); 
			// //
			// System.out.print(Arrays.toString(context.getBeanDefinitionNames()));
			// // stop and restart server for debug purpose
			// Server server = (Server) context.getBean("Server");
			// // WebAppContext staticcontext = new WebAppContext();
			// // staticcontext.setContextPath("/ext");
			// // String ProPath = System.getProperty("user.dir");
			// // staticcontext.setDescriptor(ProPath
			// // + "/src/main/webapp/WEB-INF/web.xml");
			// // staticcontext.setResourceBase(ProPath + "/src/main/webapp");
			// // staticcontext.setParentLoaderPriority(true);
			// Handler[] handlers = server.getHandlers();
			// ResourceHandler resource_handler = new ResourceHandler();
			// resource_handler.setDirectoriesListed(false);
			// resource_handler.setWelcomeFiles(new String[] { "index.html" });
			//
			// resource_handler.setResourceBase("webroot");
			// server.stop();
			// // server.setHandler(staticcontext);
			// Vector<Handler> c = new Vector<Handler>(handlers.length + 1);
			// c.add(resource_handler);
			// for (Handler handler : handlers) {
			// c.add(handler);
			// }
			// Handler[] morehandlers = (Handler[]) c.toArray(new Handler[0]);
			//
			// HandlerList handlerlists = new HandlerList();
			// handlerlists.setHandlers(morehandlers);
			// server.setHandler(handlerlists);
			// //
			// System.out.print(Arrays.toString(context.getBeanDefinitionNames()));
			// server.start();
		}
	}
}
