package com.epam.smyrnov.constants;

public class Constants {

    private Constants() {

    }

    public static class Pages {
        public static final String ERROR_PAGE = "/error.jsp";
        public static final String ACCOUNT_PAGE = "/account.jsp";
        public static final String MAIN_PAGE = "/main";
        public static final String ORDER_EDITOR_PAGE = "/editOrder.jsp";
        public static final String ITEM_EDITOR_PAGE = "/editItem.jsp";
    }

    public static class Messages {
        public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain data source.";
        public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection.";
    }

    public static class Errors {
        public static final String ERROR = "error";
        public static final String INCORRECT_EMAIL_OR_PASSWORD = "Incorrect email or password!";
    }
}
