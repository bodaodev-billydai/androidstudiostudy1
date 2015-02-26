package wenxin.billyspring;

import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServer {
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext(
				new String[] { "/wenxin/billyspring/spring.xml" });
	}
}
