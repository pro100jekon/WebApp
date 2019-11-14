package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteItemFromCartAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		String id = request.getParameter("id");
		if (id != null) {
			Long itemId = Long.parseLong(id);
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Item item = itemService.getItemById(itemId);
			cart.remove(item);
		}
		return new ActionResult(Constants.Pages.CART_PAGE);
	}
}
