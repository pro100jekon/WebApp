package com.epam.smyrnov.controller.action.impl.post;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.util.ValidateItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveItemAction implements Action {

	private void editItemFields(Item item, HttpServletRequest request) {
		String category = request.getParameter("category");
		if (!"".equals(category)) {
			item.setCategory(category);
		}
		String name = request.getParameter("name");
		if (!"".equals(name)) {
			item.setName(name);
		}
		// size has XX x YY x ZZ format, and it is passed by 3 parameters
		String size1 = request.getParameter("size1");
		String size2 = request.getParameter("size2");
		String size3 = request.getParameter("size3");
		if (!"".equals(size1) && !"".equals(size2) && !"".equals(size3)) {
			String size = size1 + " x " + size2 + " x " + size3;
			item.setSize(size);
		}
		String weight = request.getParameter("weight");
		if (!"".equals(weight)) {
			item.setWeight(Integer.parseInt(weight));
		}
		String strPrice = request.getParameter("price");
		if (!"".equals(strPrice)) {
			try {
				item.setPrice(new BigDecimal(strPrice));
			} catch (NumberFormatException ignored) {
			}
		}
		String date = request.getParameter("date");
		if (!"".equals(date)) {
			Date d;
			try {
				d = Date.valueOf(date);
			} catch (IllegalArgumentException e) {
				d = new Date(System.currentTimeMillis());
			}
			item.setDate(d);
		}
		String paths = request.getParameter("imgPaths");
		if (!"".equals(paths)) {
			List<String> urls = new ArrayList<>(Arrays.asList(paths.split(";")));
			item.getImageURLs().addAll(urls);
		}
	}

	private void setNewItem(Item item, HttpServletRequest request) {
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String strWeight = request.getParameter("weight");
		String size1 = request.getParameter("size1");
		String size2 = request.getParameter("size2");
		String size3 = request.getParameter("size3");
		String color = request.getParameter("color");
		String strPrice = request.getParameter("price");
		String strDate = request.getParameter("date");
		String imgPaths = request.getParameter("imgPaths");

		// If all request parameters are not empty
		if (!("".equals(category) || "".equals(name) || "".equals(strWeight)
				|| "".equals(size1) || "".equals(size2) || "".equals(size3)
				|| "".equals(color) || "".equals(strPrice) || "".equals(strDate)
				|| "".equals(imgPaths))) {
			Date date;
			try {
				date = Date.valueOf(strDate);
			} catch (IllegalArgumentException e) {
				date = new Date(System.currentTimeMillis());
			}
			String size = size1 + " x " + size2 + " x " + size3;
			int weight = Integer.parseInt(strWeight);
			BigDecimal price = new BigDecimal(strPrice);
			List<String> urls = new ArrayList<>(Arrays.asList(imgPaths.split(";")));
			item.setCategory(category);
			item.setName(name);
			item.setSize(size);
			item.setWeight(weight);
			item.setColor(color);
			item.setPrice(price);
			item.setDate(date);
			item.setImageURLs(urls);
		}
	}

	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		String id = request.getParameter("itemId");
		if (id != null) {
			Long itemId = Long.parseLong(id);
			Item itemForEditing = itemService.getItemById(itemId);
			editItemFields(itemForEditing, request);
			if (ValidateItem.isValid(itemForEditing)) {
				itemService.updateItem(itemForEditing);
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("mes.item.successfully.edited"));
			} else {
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.item.not.valid"));
			}
		} else { // id equals to null only if a new item is added
			Item item = new Item();
			setNewItem(item, request);
			if (ValidateItem.isValid(item)) {
				itemService.addItem(item);
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("mes.item.successfully.added"));
			} else {
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.item.not.valid"));
			}
		}
		return new ActionResult(Constants.Pages.INFO_PAGE, ResponseType.REDIRECT);
	}
}
