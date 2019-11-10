package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.util.ItemsSorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SortAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<Item> items = (List<Item>) session.getAttribute("items");
		String mode = request.getParameter("mode");
		String desc = request.getParameter("desc");
		if (items == null) {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			items = itemService.getAllItems();
			session.setAttribute("items", items);
		}
		if (mode != null) {
			boolean isDesc;
			if (desc == null) {
				isDesc = false;
			} else {
				isDesc = true;
			}
			switch (mode.toLowerCase()) {
				case "name":
					ItemsSorter.sortByName(items, isDesc);
					break;
				case "date":
					ItemsSorter.sortByDate(items, isDesc);
					break;
				case "price":
					ItemsSorter.sortByPrice(items, isDesc);
					break;
				default:
					request.setAttribute("message", "ERROR. Wrong command.");
					return Constants.Pages.INFO_PAGE;
			}
		}
		return Constants.Pages.MAIN_PAGE;
	}
}
