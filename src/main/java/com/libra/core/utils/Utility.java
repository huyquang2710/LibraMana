package com.libra.core.utils;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSiteURL(HttpServletRequest request) {
		// trả về Url tuyệt đối để xác nhận trong email
		String siteURL = request.getRequestURI().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
}
