package wenxin.billyspring;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopHandler {
	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	@SuppressWarnings("unused")
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
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
