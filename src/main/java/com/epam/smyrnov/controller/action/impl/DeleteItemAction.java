package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (id != null) {
			Long identifier = Long.parseLong(id);
			if (itemService.removeItemById(identifier)) {
				request.setAttribute("message", "INFO. Item was successfully deleted.");
			} else {
				request.setAttribute("message", "ERROR. Item was not deleted. Try again.");
			}
		}
		return "PRG" + Constants.Pages.INFO_PAGE;
	}
}
