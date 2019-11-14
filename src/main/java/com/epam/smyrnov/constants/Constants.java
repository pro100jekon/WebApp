package com.epam.smyrnov.constants;

public class Constants {

    private Constants() {
    }

    public static class Pages {
        public static final String INFO_PAGE = "/info.jsp";
        public static final String ACCOUNT_PAGE = "/account.jsp";
        public static final String MAIN_PAGE = "/main";
        public static final String ORDER_EDITOR_PAGE = "/editOrder.jsp";
        public static final String ITEM_EDITOR_PAGE = "/editItem.jsp";
        public static final String REGISTRATION_PAGE = "/register.jsp";
        public static final String CART_PAGE = "/cart.jsp";
    }

    public static class Messages {
        public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain data source.";
        public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection.";
        public static final String ERR_CANNOT_CLOSE_RESULT_SET = "Cannot close result set.";
        public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close connection.";
        public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close statement.";
        public static final String ERR_CANNOT_ROLLBACK_CONNECTION = "Cannot rollback connection.";
    }
}
