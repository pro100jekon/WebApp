package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditItemAjax implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Item item = (Item) request.getSession().getAttribute("item");
		if (item == null) {
			request.setAttribute("message", "ERROR. There is nothing to change");
		} else {
			String ordinal = request.getParameter("ordinal");
			int o = Integer.parseInt(ordinal);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			item.getImageURLs().set(o, null);
		}
		return "ajaxOutput/To confirm changes, you must press Save button.";
	}
}
