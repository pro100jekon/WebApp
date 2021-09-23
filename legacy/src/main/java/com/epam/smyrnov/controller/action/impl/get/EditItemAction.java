package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditItemAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String itemId = request.getParameter("itemId");
		Long id = Long.parseLong(itemId);
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		Item item = itemService.getItemById(id);
		if (item != null) {
			request.setAttribute("item", item);
		}
		return new ActionResult(Constants.Pages.ITEM_EDITOR_PAGE);
	}
}
