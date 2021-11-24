package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

// �αױ�� ����� ����

public class LoggingTest {
	
	// LoggerŬ������ �ν��Ͻ��� �޾ƿ´�.
	static Logger logger = Logger.getLogger(LoggingTest.class);
	
	
	public static void main(String[] args) {
		// �� �������� �޽����� ����ϴ� ����� ����Ͽ� �޽����� ����Ѵ�.
		// ����1) logger.�����̸�("����� �޽���");
		// ����2) logger.log(Level.�����̸�, "����� �޽���");
		logger.trace("�̰��� log4j�� 'TRACE'������ ����Դϴ�.");
		logger.debug("�̰��� log4j�� 'DEBUG'������ ����Դϴ�.");
		logger.info("�̰��� log4j�� 'INFO'������ ����Դϴ�.");
		
		logger.log(Level.WARN, "�̰��� log4j�� [WARN]������ ����Դϴ�.");
		logger.log(Level.ERROR, "�̰��� log4j�� [ERROR]������ ����Դϴ�.");
		logger.log(Level.FATAL, "�̰��� log4j�� [FATAL]������ ����Դϴ�.");
		
	}
	
}
