package com.epam.smyrnov.listener.loader;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Repository;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.exception.StartupException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads autowired repositories into the correct classes using java.lang.reflect.
 */
public class ContextBeanLoader extends AbstractContextBeanLoader {

    private Map<String, Object> beans = new HashMap<>();
    private Map<String, Object> services = new HashMap<>();

    private ServletContext servletContext;


    private static Logger logger = Logger.getLogger(ContextBeanLoader.class);

    public ContextBeanLoader(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void load(String... packages) {
        try {
            loadBeans(packages);
            beans.putAll(services);
            for (Object o : beans.values()) {
                for (Field field : o.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Autowired.class)) {
                        String[] fieldName = field.getType().getName().split("[.]");
                        String fieldType = fieldName[fieldName.length - 1];
                        Object o1 = beans.get(fieldType);
                        if (o1 != null) {
                            field.set(o, o1);
                        }
                    }
                }
            }
            for (Map.Entry<String, Object> entry : services.entrySet()) {
                Object o = entry.getValue();
                servletContext.setAttribute(entry.getKey(), o);
            }
        } catch (ReflectiveOperationException | IOException | URISyntaxException e) {
            logger.error(e.getMessage(), e);
            throw new StartupException(e.getMessage(), e);
        }
    }

    @Override
    protected void loadBean(Class<?> c) throws ReflectiveOperationException {
        if (c.isAnnotationPresent(Repository.class)) {
            Object o = c.newInstance();
            String interfaceName = null;
            for (Object implementedInterface : o.getClass().getInterfaces()) {
                Matcher m = Pattern.compile("Repository$", Pattern.MULTILINE).matcher(implementedInterface.toString());
                if (m.find()) {
                    String[] fqn = implementedInterface.toString().replace("interface ", "").split("[.]");
                    interfaceName = fqn[fqn.length - 1];
                }
            }
            if (interfaceName != null) {
                beans.put(interfaceName, o);
            }
        } else if (c.isAnnotationPresent(Service.class)) {
            Object o = c.newInstance();
            String interfaceName = null;
            for (Object implementedInterface : o.getClass().getInterfaces()) {
                Matcher m = Pattern.compile("Service$", Pattern.MULTILINE).matcher(implementedInterface.toString());
                if (m.find()) {
                    String[] fqn = implementedInterface.toString().replace("interface ", "").split("[.]");
                    interfaceName = fqn[fqn.length - 1];
                }
            }
            if (interfaceName != null) {
                services.put(interfaceName, o);
            }
        }
    }
}
