package com.epam.smyrnov.listener;

import com.epam.smyrnov.controller.action.ActionFactory;
import com.epam.smyrnov.listener.loader.ContextBeanLoader;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ActionFactory.init();
        ServletContext servletContext = sce.getServletContext();
        ContextBeanLoader loader = new ContextBeanLoader(servletContext);
        loader.load("com.epam.smyrnov.repository", "com.epam.smyrnov.service");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
