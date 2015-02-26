package wenxin.billyspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServer {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "/wenxin/billyspring/spring.xml" });
		ClassA eas = (ClassA) context.getBean("ClassABean");
		// 执行功能代码
		eas.doExecute();
	}
}
