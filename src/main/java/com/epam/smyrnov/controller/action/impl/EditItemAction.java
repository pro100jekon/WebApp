package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditItemAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String itemId = request.getParameter("itemId");
		Long id = Long.parseLong(itemId);
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		Item item = itemService.getItemById(id);
		if (item != null) {
			HttpSession session = request.getSession();
			session.setAttribute("item", item);
		}
		return Constants.Pages.ITEM_EDITOR_PAGE;
	}
}
