package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getParameter("param1"));
		System.out.println(request.getParameter("param2"));
		return "/ajax.jsp";
	}
}
