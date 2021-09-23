package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.util.ItemsSorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ShowByCriteriaAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Item> items;
		String sort = request.getParameter("sort");
		String category = request.getParameter("category");
		String leftPriceRange = request.getParameter("left");
		String rightPriceRange = request.getParameter("right");
		String color = request.getParameter("color");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (category != null && !category.toLowerCase().equals("none")) {
			items = itemService.getItemsByCategory(category);
		} else {
			items = itemService.getAllItems();
		}
		if (!"".equals(leftPriceRange) && !"".equals(rightPriceRange)) {
			BigDecimal bdLeft = new BigDecimal(leftPriceRange);
			BigDecimal bdRight = new BigDecimal(rightPriceRange);
			if (bdLeft.compareTo(bdRight) <= 0
					&& bdLeft.compareTo(BigDecimal.ZERO) >= 0
					&& bdRight.compareTo(BigDecimal.ZERO) >= 0 ) {
				itemService.getItemsByPriceRange(items, bdLeft, bdRight);
			} else {
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.wrong.price.range"));
				return new ActionResult(Constants.Pages.INFO_PAGE);
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
					request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.wrong.command"));
					return new ActionResult(Constants.Pages.INFO_PAGE);
			}
		}
		request.setAttribute("items", items);
		return new ActionResult(Constants.Pages.MAIN_PAGE);
	}
}
