package wenxin.billyspring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MyHandler extends AbstractHandler {
	wenxin.billyspring.ClassA a;

	public void setParam1(wenxin.billyspring.ClassA a) {
		this.a = a;
		System.out.println();
		System.out.println("execute");
		a.doExecute();
		System.out.println();
		System.out.println("addAlias");
		a.addAlias("ali");
		System.out.println();
		System.out.println("delAlias");
		a.delAlias();
		System.out.println();
		System.out.println("...");
	}
 
	public void addLog(String log) {
		System.out.println("MyHandler.addlog");
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
		response.getWriter().println("<li>Request url: " + target + "</li>");
		response.getWriter().println(
				"<li>Server port: " + request.getServerPort() + "</li>");
		addLog("test");
		a.addAlias("ali");
		a.delAlias();
	}
}