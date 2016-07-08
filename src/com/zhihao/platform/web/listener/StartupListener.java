package com.zhihao.platform.web.listener;

import javax.servlet.ServletContext;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.zhihao.platform.web.controllers.MyController;

@Component
public class StartupListener extends MyController implements
		ApplicationContextAware, ServletContextAware, InitializingBean,
		ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		logger.debug("1 => StartupListener.setApplicationContext");
	}

	@Override
	public void setServletContext(ServletContext context) {
		logger.debug("2 => StartupListener.setServletContext");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("3 => StartupListener.afterPropertiesSet");
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {
		logger.debug("4.1 => MyApplicationListener.onApplicationEvent");
		if (evt.getApplicationContext().getParent() == null) {
			logger.debug("4.2 => MyApplicationListener.onApplicationEvent");
		}
	}

/*	private void shedule(){
		 try {
             // Grab the Scheduler instance from the Factory
             Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

             // and start it off
             scheduler.start();

             scheduler.shutdown();

         } catch (SchedulerException se) {
             se.printStackTrace();
         }
	}*/
	
}
