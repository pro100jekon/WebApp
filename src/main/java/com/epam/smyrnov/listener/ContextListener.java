package com.epam.smyrnov.listener;

import com.epam.smyrnov.controller.action.ActionFactory;
import com.epam.smyrnov.listener.loader.ContextBeanLoader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ActionFactory.init();
        ServletContext servletContext = sce.getServletContext();
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("properties", properties);
        ContextBeanLoader loader = new ContextBeanLoader(servletContext);
        loader.load("com.epam.smyrnov.repository", "com.epam.smyrnov.service");
        logger.debug("ContextListener was initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("ContextListener was destroyed.");
    }
}
