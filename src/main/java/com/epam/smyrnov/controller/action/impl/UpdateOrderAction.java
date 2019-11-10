package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateOrderAction implements Action {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int[] quantities = null;
        int size;
        for (int i = 0; ; i++) {
            String check = request.getParameter("quantity" + i);
            if (check == null) {
                size = i;
                break;
            }
        }
        if (size != 0) {
            quantities = new int[size];
            for (int i = 0; i < size; i++) {
                try {
                    int val = Integer.parseInt(request.getParameter("quantity" + i));
                    if (val > 0) {
                        quantities[i] = val;
                    } else {
                        quantities[i] = 1;
                    }
                } catch (NumberFormatException e) {
                    quantities[i] = 1;
                }
            }
            size = 0;
        }
        if (quantities != null) {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            for (Item item : cart.getItemInCart().keySet()) {
                cart.getItemInCart().put(item, quantities[size++]);
            }
        }
        return "PRG" + Constants.Pages.CART_PAGE;
    }
}
