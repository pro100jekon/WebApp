package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveItemAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Item item = (Item) session.getAttribute("item");
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		if (item != null) {
			String id = request.getParameter("itemIdentifier");
			Long identifier = Long.parseLong(id); //1
			Item originalItem = itemService.getItemById(identifier); //1
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
					String size =  size1 + " x " +  size2 + " x " + size3;
					originalItem.setSize(size);
				}
				String weight = request.getParameter("weight");
				if (!"".equals(weight)) {
					int w = Integer.parseInt(weight);
					originalItem.setWeight(w);
				}
				String strPrice = request.getParameter("price");
				if (!"".equals(strPrice)) {
					BigDecimal price = new BigDecimal(strPrice);
					originalItem.setPrice(price);
				}
				String date = request.getParameter("date");
				if (!"".equals(date)) {
					Date d = Date.valueOf(date);
					originalItem.setDate(d);
				}
				String paths = request.getParameter("imgPaths");
				if (!"".equals(paths)) {
					List<String> urls = new ArrayList<>(Arrays.asList(paths.split(";")));
					originalItem.getImageURLs().addAll(urls);
				}
				//////////////////////////////////////////////
				// above block of code checks non-empty fields and edits only them
				itemService.updateItem(originalItem);
				return Constants.Pages.ITEM_EDITOR_PAGE; // everything is ok
			}
		} else {
			request.setAttribute("message", "ERROR. Try editing item first, then save it.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
