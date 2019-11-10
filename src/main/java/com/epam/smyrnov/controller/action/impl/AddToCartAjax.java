package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToCartAjax implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String itemId = request.getParameter("itemId");
		if (itemId != null && itemId.contains("add")) {
			ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
			Long id = Long.parseLong(itemId.substring(3));
			Item item = itemService.getItemById(id);
			if (item != null) {
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("cart");
				cart.put(item);
				response.setCharacterEncoding("UTF-8");
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("ajaxOutput/").append(item.getName()).append(" was added to cart. Check it out to change quantity if you want!%")
						//<a href="cart.jsp" class="btn btn-secondary">
						//                        Cart: ${sessionScope.cart.totalPrice}
						//                    </a>
						.append("<a href=\"cart.jsp\" class=\"btn btn-secondary\">")
						.append("Cart: ").append(((Cart) session.getAttribute("cart")).getTotalPrice())
						.append(" UAH</a>");
				return stringBuilder.toString();
			} else {
				request.setAttribute("message", "ERROR. There is no such item");
			}
			request.setAttribute("error", "ERROR. Nothing to add in cart");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
