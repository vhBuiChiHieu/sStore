package pro.vhbchieu.sStore.config.common;

import jakarta.servlet.http.HttpServletResponse;

public class DuplicateCode {

    private DuplicateCode() {
    }

    public static void response(HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }

    public static String checkRequestQuery(String query) {
        if (query == null || query.isEmpty()) {
            return "";
        } else {
            return "?" + query;
        }
    }

}
