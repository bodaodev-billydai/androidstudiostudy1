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
	 * ����Pointcut��Pointcut������ΪaddAddMethod()���˷���û�з���ֵ�Ͳ���
	 * 
	 * �÷�������һ����ʶ�������е���
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

	@After("delAddMethod3()")
	private void logSecurity() {
		System.out.println("-------logSecurity-------");
	}

	public void testApi() {
		((AopHandler) AopContext.currentProxy()).addAddMethod();
		delAddMethod3();
	}

}
