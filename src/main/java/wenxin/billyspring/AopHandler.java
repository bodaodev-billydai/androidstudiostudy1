package wenxin.billyspring;

import org.aspectj.lang.annotation.After;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;

import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopHandler {

	/**
	 * 
	 * ����Pointcut��Pointcut������ΪaddAddMethod()���˷���û�з���ֵ�Ͳ���
	 * 
	 * �÷�������һ����ʶ�������е���
	 */

	@Pointcut("execution(*add*(..))")
	private void addAddMethod() {
	};

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */

	@Before("addAddMethod()")
	private void checkSecurity() {

		System.out.println("-------checkSecurity-------");

	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */

	@After("addAddMethod()")
	private void logSecurity() {

		System.out.println("-------logSecurity-------");

	}

}
