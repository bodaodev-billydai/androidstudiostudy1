package wenxin.billyspring;

public class AopHandler {

	/**
	 * 
	 * 定义Pointcut，Pointcut的名称为addAddMethod()，此方法没有返回值和参数
	 * 
	 * 该方法就是一个标识，不进行调用
	 */
	private void addAddMethod() {
		System.out.println("-------addAddMethod-------");
	};

	private void delAddMethod3() {
		System.out.println("-------delAddMethod-------");
	};

	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}

	/**
	 * 
	 * 定义Advice，表示我们的Advice应用到哪些Pointcut订阅的Joinpoint上
	 */
	private void logSecurity() {
		System.out.println("-------logSecurity-------");
	}
}
