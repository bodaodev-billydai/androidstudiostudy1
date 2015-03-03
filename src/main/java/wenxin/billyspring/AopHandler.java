package wenxin.billyspring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AopHandler {
	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	@SuppressWarnings("unused")
	private void checkSecurityBefore(JoinPoint jp) {
		System.out.println("-------checkSecurity Before-------");
	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	@SuppressWarnings("unused")
	private void logSecurityBefore() {
		System.out.println("-------logSecurity Before-------");
	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	@SuppressWarnings("unused")
	private void checkSecurityAfter(JoinPoint jp) {
		System.out.println("-------checkSecurity After-------");
	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	@SuppressWarnings("unused")
	private void logSecurityAfter() {
		System.out.println("-------logSecurity After-------");
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
		// pjp.proceed();
		System.out.println("-------after controlSecurity-------");
	}

}
