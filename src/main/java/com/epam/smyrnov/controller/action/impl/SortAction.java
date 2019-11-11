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
import java.math.BigDecimal;
import java.util.List;

public class SortAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<Item> items = (List<Item>) request.getAttribute("items");
		String sort = request.getParameter("sort");
		String category = request.getParameter("category");
		String left = request.getParameter("left");
		String right = request.getParameter("right");
		String color = request.getParameter("color");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (category != null && !category.toLowerCase().equals("none")) {
			items = itemService.getItemsByCategory(category);
		} else {
			items = itemService.getAllItems();
		}
		if (!"".equals(left) && !"".equals(right)) {
			System.out.println("not here1");
			BigDecimal dbLeft = new BigDecimal(left);
			BigDecimal dbRight = new BigDecimal(right);
			if (dbLeft.compareTo(dbRight) <= 0
					&& dbLeft.compareTo(BigDecimal.ZERO) >= 0
					&& dbRight.compareTo(BigDecimal.ZERO) >= 0 ) {
				itemService.getItemsByPriceRange(items, dbLeft, dbRight);
			} else {
				request.setAttribute("message", "ERROR. Wrong values of price range.");
				return Constants.Pages.INFO_PAGE;
			}
		}
		if (color != null && !"none".equals(color.toLowerCase())) {
			itemService.getItemsByColor(items, color);
		}
		if (sort != null) {
			switch (sort.toLowerCase()) {
				case "nameasc":
					ItemsSorter.sortByName(items, false);
					break;
				case "dateasc":
					ItemsSorter.sortByDate(items, false);
					break;
				case "priceasc":
					ItemsSorter.sortByPrice(items, false);
					break;
				case "weightasc":
					ItemsSorter.sortByWeight(items, false);
					break;
				case "namedesc":
					ItemsSorter.sortByName(items, true);
					break;
				case "datedesc":
					ItemsSorter.sortByDate(items, true);
					break;
				case "pricedesc":
					ItemsSorter.sortByPrice(items, true);
					break;
				case "weightdesc":
					ItemsSorter.sortByWeight(items, true);
					break;
				case "none":
					break;
				default:
					request.setAttribute("message", "ERROR. Wrong command.");
					return Constants.Pages.INFO_PAGE;
			}
		}
		request.setAttribute("items", items);
		return Constants.Pages.MAIN_PAGE;
	}
}
