package com.libra.core.utils;

import javax.servlet.http.HttpServletRequest;

// trả về Url tuyệt đối để xác nhận trong email
public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}