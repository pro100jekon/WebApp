package com.epam.smyrnov.controller.action.impl.get.ajax;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditItemAjax implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("itemId");
		String output = "1";
		if (id == null) {
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.nothing.to.change"));
			return new ActionResult(Constants.Pages.INFO_PAGE);
		} else {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Item item = itemService.getItemById(Long.parseLong(id));
			Item itemCopy = itemService.getItemById(Long.parseLong(id));
			String url = request.getParameter("url");
			itemCopy.getImageURLs().remove(url);
			if (!itemCopy.equals(item)) {
				itemService.updateItem(itemCopy);
				output = "0";
			}
		}
		return new ActionResult(output, ResponseType.AJAX);
	}
}
