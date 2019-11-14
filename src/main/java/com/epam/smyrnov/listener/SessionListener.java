package com.epam.smyrnov.listener;

import com.epam.smyrnov.entity.Cart;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Sets the individual cart for each session.
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	private static final Logger logger = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("Session created");
		HttpSession session = se.getSession();
		session.setAttribute("cart", new Cart());
	}
}
