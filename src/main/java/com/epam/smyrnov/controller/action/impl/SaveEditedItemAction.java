package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.util.ValidateItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveEditedItemAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Item item = (Item) session.getAttribute("item");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (item != null) {
			String id = request.getParameter("itemIdentifier");
			Long identifier = Long.parseLong(id);
			Item originalItem = itemService.getItemById(identifier);
			if (!identifier.equals(item.getId())) {
				request.setAttribute("message", "ERROR. Wrong item editing.");
			} else {
				//////////////////////////////////////////////
				String category = request.getParameter("category");
				if (!"".equals(category)) {
					originalItem.setCategory(category);
				}
				String name = request.getParameter("name");
				if (!"".equals(name)) {
					originalItem.setName(name);
				}
				String size1 = request.getParameter("size1");
				String size2 = request.getParameter("size2");
				String size3 = request.getParameter("size3");
				if (!"".equals(size1) && !"".equals(size2) && !"".equals(size3)) {
					String size = size1 + " x " + size2 + " x " + size3;
					originalItem.setSize(size);
				}
				String weight = request.getParameter("weight");
				if (!"".equals(weight)) {
					originalItem.setWeight(Integer.parseInt(weight));
				}
				String strPrice = request.getParameter("price");
				if (!"".equals(strPrice)) {
					try {
						originalItem.setPrice(new BigDecimal(strPrice));
					} catch (NumberFormatException ignored){
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
					originalItem.setDate(d);
				}
				String paths = request.getParameter("imgPaths");
				if (!"".equals(paths)) {
					List<String> urls = new ArrayList<>(Arrays.asList(paths.split(";")));
					originalItem.getImageURLs().addAll(urls);
				}
				//////////////////////////////////////////////
				// above block of code checks non-empty fields and edits only them
				if (ValidateItem.isValid(originalItem)) {
					itemService.updateItem(originalItem);
					request.setAttribute("message", "INFO. Item was successfully edited.");
				} else {
					request.setAttribute("message", "ERROR. Item is not valid.");
				}
			}
		} else {
			String category = request.getParameter("category");
			String name = request.getParameter("name");
			String weight = request.getParameter("weight");
			String size1 = request.getParameter("size1");
			String size2 = request.getParameter("size2");
			String size3 = request.getParameter("size3");
			String color = request.getParameter("color");
			String strPrice = request.getParameter("price");
			String date = request.getParameter("date");
			String imgPaths = request.getParameter("imgPaths");
			if ("".equals(category) || "".equals(name) || "".equals(weight)
					|| "".equals(size1) || "".equals(size2) || "".equals(size3)
					|| "".equals(color) || "".equals(strPrice) || "".equals(date)
					|| "".equals(imgPaths)) {
				request.setAttribute("message", "ERROR. Try editing item first, then save it.");
			} else {
				item = new Item();
				Date d;
				try {
					d = Date.valueOf(date);
				} catch (IllegalArgumentException e) {
					d = new Date(System.currentTimeMillis());
				}
				String size = size1 + " x " + size2 + " x " + size3;
				int w = Integer.parseInt(weight);
				BigDecimal price = new BigDecimal(strPrice);
				List<String> urls = new ArrayList<>(Arrays.asList(imgPaths.split(";")));
				item.setCategory(category);
				item.setName(name);
				item.setSize(size);
				item.setWeight(w);
				item.setColor(color);
				item.setPrice(price);
				item.setDate(d);
				item.setImageURLs(urls);
				if (ValidateItem.isValid(item)) {
					itemService.addItem(item);
					request.setAttribute("message", "INFO. Item was successfully added.");
				} else {
					request.setAttribute("message", "ERROR. Item is not valid.");
				}
			}
		}
		return "PRG" + Constants.Pages.INFO_PAGE;
	}
}
