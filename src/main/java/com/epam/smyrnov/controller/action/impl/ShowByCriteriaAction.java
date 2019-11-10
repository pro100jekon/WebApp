package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ShowByCriteriaAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<Item> items = (List<Item>) session.getAttribute("items");
		if (items != null) {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			String color = request.getParameter("color");
			String left = request.getParameter("left");
			String right = request.getParameter("right");
			if (color != null) {
				itemService.getItemsByColor(items, color.toLowerCase());
			}
			if (left != null && right != null) {
				BigDecimal bdLeft = new BigDecimal(left);
				BigDecimal bdRight = new BigDecimal(right);
				if (bdLeft.compareTo(bdRight) <= 0) {
					itemService.getItemsByPriceRange(items, bdLeft, bdRight);
				} else {
					request.setAttribute("message", "ERROR. Wrong price.");
					return Constants.Pages.INFO_PAGE;
				}
			}
			return Constants.Pages.MAIN_PAGE;
		} else {
			request.setAttribute("message", "ERROR. Can't execute this command. Try again.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
