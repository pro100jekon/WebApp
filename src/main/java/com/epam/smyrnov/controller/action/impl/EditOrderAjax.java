package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditOrderAjax implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<Item, Integer> itemMap = (Map<Item, Integer>) session.getAttribute("itemMap");
		if (itemMap == null) {
			itemMap = new LinkedHashMap<>();
			session.setAttribute("itemMap", itemMap);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String itemId = request.getParameter("itemId");
		String quantity = request.getParameter("quantity");
		if (itemId == null || quantity == null || Integer.parseInt(itemId) < 1 || Integer.parseInt(quantity) < 1) {
			request.setAttribute("message", "ERROR. Wrong info. Try again.");
		} else {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Item item = itemService.getItemById(Long.parseLong(itemId));
			if (item != null) {
				itemMap.put(item, Integer.parseInt(quantity));
			}
			StringBuilder itemsOutput = new StringBuilder("<td colspan=\"2\"><div style=\"text-align: center;\">");
			for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
				itemsOutput.append("<h5>").append(entry.getKey().getName()).append(" -> ").append(entry.getValue()).append("</h5><br>");
			}
			itemsOutput.append("</div></td>");
			return "ajaxOutput/" + itemsOutput;
		}
		return Constants.Pages.INFO_PAGE;
	}
}
