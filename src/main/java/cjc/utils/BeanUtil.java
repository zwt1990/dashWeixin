package cjc.utils;

import org.springframework.context.ApplicationContext;

public class BeanUtil {
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public  static Object getBean(Object obj) {
		return applicationContext.getBean(Object.class);
	}
}
