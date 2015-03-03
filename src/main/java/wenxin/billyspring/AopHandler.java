package wenxin.billyspring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;

@Aspect
public class AopHandler {

	/**
	 * 
	 * 定义Pointcut，Pointcut的名称为addAddMethod()，此方法没有返回值和参数
	 * 
	 * 该方法就是一个标识，不进行调用
	 */

	@Pointcut("execution(* add*(..))")
	private void addAddMethod() {
		System.out.println("-------addAddMethod-------");
	};

	@Pointcut("execution(* del*(..))")
	private void delAddMethod3() {
		System.out.println("-------delAddMethod-------");
	};

	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */

	@Before("addAddMethod()")
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}

	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */

	@After("delAddMethod3()")
	private void logSecurity() {
		System.out.println("-------logSecurity-------");
	}

	public void testApi() {
		((AopHandler) AopContext.currentProxy()).addAddMethod();
		delAddMethod3();
	}

}
