package wenxin.billyspring;

public class AopHandler {

	/**
	 * 
	 * ����Pointcut��Pointcut������ΪaddAddMethod()���˷���û�з���ֵ�Ͳ���
	 * 
	 * �÷�������һ����ʶ�������е���
	 */
	private void addAddMethod() {
		System.out.println("-------addAddMethod-------");
	};

	private void delAddMethod3() {
		System.out.println("-------delAddMethod-------");
	};

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	private void checkSecurity() {
		System.out.println("-------checkSecurity-------");
	}

	/**
	 * 
	 * ����Advice����ʾ���ǵ�AdviceӦ�õ���ЩPointcut���ĵ�Joinpoint��
	 */
	private void logSecurity() {
		System.out.println("-------logSecurity-------");
	}
}
