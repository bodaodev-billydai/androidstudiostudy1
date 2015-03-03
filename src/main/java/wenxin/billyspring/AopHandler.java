package wenxin.billyspring;

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
}
