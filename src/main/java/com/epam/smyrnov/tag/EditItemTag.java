package com.epam.smyrnov.tag;


import com.epam.smyrnov.entity.Entity;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.order.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class EditItemTag <T extends Entity> extends TagSupport {

	private Entity entity;

	public void setEntity(T entity) {
		this.entity = entity;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String url = null;
			if (entity.getClass().equals(Item.class)) {
				url = "editItem?itemId=";
			} else if (entity.getClass().equals(Order.class)) {
				url = "editOrder?orderId=";
			}
			out.print(url);
		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return SKIP_BODY;
	}
}
