package wenxin.billyspring;

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
}
