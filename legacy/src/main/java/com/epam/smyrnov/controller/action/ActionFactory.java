package com.epam.smyrnov.controller.action;

import com.epam.smyrnov.controller.action.impl.ErrorAction;
import com.epam.smyrnov.controller.action.impl.get.*;
import com.epam.smyrnov.controller.action.impl.get.ajax.*;
import com.epam.smyrnov.controller.action.impl.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActionFactory {

	private static Map<String, Action> container = new LinkedHashMap<>();

	public static void init() {
		container.put("GET/main",               new MainPageAction());
		container.put("POST/login",             new SignInAction());
		container.put("GET/register",           new SignUpAction());
		container.put("POST/registerAccount",   new RegisterUserAction());
		container.put("GET/account",            new AccountAction());
		container.put("GET/exit",               new ExitAction());
		container.put("GET/editOrder",          new EditOrderAction());
		container.put("GET/editOrderAjax",      new EditOrderAjax());
		container.put("POST/saveOrder",         new SaveEditedOrderAction());
		container.put("GET/editUserAjax",       new EditUserAjax());
		container.put("GET/editItem",           new EditItemAction());
		container.put("GET/editItemAjax",       new EditItemAjax());
		container.put("POST/saveItem",          new SaveItemAction());
		container.put("POST/updateOrder",       new UpdateOrderAction());
		container.put("GET/addToCartAjax",      new AddToCartAjax());
		container.put("POST/registerOrder",     new RegisterOrderAction());
		container.put("GET/showByCriteria",     new ShowByCriteriaAction());
		container.put("GET/deleteItemFromCart", new DeleteItemFromCartAction());
		container.put("GET/deleteItem",         new DeleteItemAction());
		container.put("GET/verify",             new VerifyAction());
		container.put("GET/changeLocale",             new ChangeLocaleAction());
	}

	public static Action getAction(HttpServletRequest request) {
		Action action = container.get(request.getMethod() + request.getServletPath());
		System.out.println(request.getMethod() + request.getServletPath());
		if (action == null) {
			return new ErrorAction();
		}
		return action;
	}
}
