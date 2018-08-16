package com.hp.multidata.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringContext.applicationContext == null){
			SpringContext.applicationContext = applicationContext;
			System.out.println("初始化context");
		}
		
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
}
