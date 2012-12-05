package net.atos.activiti.servlet;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.activiti.engine.impl.util.LogUtil;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LoggerListener implements ServletContextListener {
	
	  static {
	    LogUtil.readJavaUtilLoggingConfigFromClasspath();
		java.util.logging.Logger rootLogger = LogManager.getLogManager().getLogger("");  
		Handler[] handlers = rootLogger.getHandlers();  
		for (int i = 0; i < handlers.length; i++) {  
			rootLogger.removeHandler(handlers[i]);  
		}
		SLF4JBridgeHandler.install();	    
	  }
	  
	  protected static final Logger logger = Logger.getLogger(LoggerListener.class.getName());

	  public void contextInitialized(ServletContextEvent event) {
	      logger.fine("Starting Logger!!");
	  }

	  public void contextDestroyed(ServletContextEvent event) {
	      logger.fine("Stopping Logger!!");
	  }	
}
