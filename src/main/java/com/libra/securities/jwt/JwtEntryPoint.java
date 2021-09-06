package com.libra.securities.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// check ngay tu dau
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
	

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOGGER.error("Unauthorized error Message {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_ACCEPTED, "Error -> Unauthorized	");
	}
	
}
