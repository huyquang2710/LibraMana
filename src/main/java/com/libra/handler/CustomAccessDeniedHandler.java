package com.libra.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	// xử lý người dùng ko có cửa
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			LOGGER.info("Tài Khoản:  '" + authentication.getName() + "' đã cố gắng truy cập URL: " + request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath() + "/access-denied");
	}

}
