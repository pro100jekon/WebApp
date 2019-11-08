package com.epam.smyrnov.controller.action;

import com.epam.smyrnov.controller.action.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActionFactory {

	private static Map<String, Action> container = new LinkedHashMap<>();

	public static void init() {
		container.put("GET/main", new MainAction());
		container.put("POST/login", new LoginAction());
		container.put("GET/account", new AccountAction());
		container.put("GET/cart", new CartAction());
		container.put("GET/exit", new ExitAction());
		container.put("GET/ajax", new AjaxAction());
		container.put("GET/editOrder", new EditOrderAction());
		container.put("GET/editOrderAjax", new EditOrderAjax());
		container.put("POST/saveOrder", new SaveOrderAction());
		container.put("GET/editUserAjax", new EditUserAjax());
		container.put("GET/editItem", new EditItemAction());
		container.put("GET/editItemAjax", new EditItemAjax());
		container.put("POST/saveItem", new SaveItemAction());
	}

	public static Action getAction(HttpServletRequest request) {
		return container.get(request.getMethod() + request.getServletPath());
	}
}
