package wenxin.billyspring;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopHandler {
	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */
	@SuppressWarnings("unused")
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}

	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */
	@SuppressWarnings("unused")
	private void logSecurity() {
		System.out.println("-------logSecurity-------");
	}

	@SuppressWarnings("unused")
	private void controlSecurity(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-------before controlSecurity-------");
		pjp.proceed();
		System.out.println("-------after controlSecurity-------");
	}

	@SuppressWarnings("unused")
	private void denineSecurity(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-------before controlSecurity-------");
		//pjp.proceed();
		System.out.println("-------after controlSecurity-------");
	}

}
