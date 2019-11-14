package com.epam.smyrnov.controller.action.impl.get.ajax;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditOrderAjax implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.wrong.info"));
		} else {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Item item = itemService.getItemById(Long.parseLong(itemId));
			if (item != null) {
				itemMap.put(item, Integer.parseInt(quantity));
			}
			StringBuilder itemsOutput = new StringBuilder();
			for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
				itemsOutput.append(entry.getKey().getName()).append(" -> ").append(entry.getValue()).append("; ");
			}
			return new ActionResult(itemsOutput.toString(), ResponseType.AJAX);
		}
		return new ActionResult(Constants.Pages.INFO_PAGE);
	}
}
