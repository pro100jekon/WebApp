package com.epam.smyrnov.controller.action.impl.post;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (id != null) {
			Long identifier = Long.parseLong(id);
			if (itemService.removeItemById(identifier)) {
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("mes.item.successfully.deleted"));
			}
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.item.not.deleted"));
		}
		return new ActionResult(Constants.Pages.INFO_PAGE, ResponseType.REDIRECT);
	}
}
