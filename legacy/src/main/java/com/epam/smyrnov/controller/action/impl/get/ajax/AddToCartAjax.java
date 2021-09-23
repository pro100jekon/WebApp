package com.epam.smyrnov.controller.action.impl.get.ajax;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToCartAjax implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String itemId = request.getParameter("itemId");
		if (itemId != null && itemId.contains("add")) {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Long id = Long.parseLong(itemId.substring(3));
			Item item = itemService.getItemById(id);
			if (item != null) {
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("cart");
				cart.put(item);
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(item.getName()).append(" ").append(Page.RESOURCE_BUNDLE.getString("mes.item.added.to.cart")).append("%")
						.append(((Cart) session.getAttribute("cart")).getTotalPrice());
				return new ActionResult(stringBuilder.toString(), ResponseType.AJAX);
			} else {
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.no.item"));
			}
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.nothing.to.add"));
		}
		return new ActionResult(Constants.Pages.INFO_PAGE);
	}
}
