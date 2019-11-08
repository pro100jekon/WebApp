package com.epam.smyrnov.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {

    String exec(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
