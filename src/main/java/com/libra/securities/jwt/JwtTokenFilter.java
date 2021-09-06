package com.libra.securities.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.libra.securities.userprincal.UserDetailServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter{
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
	
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserDetailServiceImpl  userDetailService;
	//tim token trong req

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getJwt(request);
			
			
			// neu token hop le. thi thi se get
			if(token != null && jwtProvider.validateTolen(token)) {
				//lay username tu token
				String username = jwtProvider.getUsernameFromToken(token);
				UserDetails userDetails = userDetailService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				//set user tren website
				authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenToken);
			}
		} catch (Exception e) {
			LOGGER.error("Không thể xác thực user -> Message: {}", e);
		}
		filterChain.doFilter(request, response);
	}
	//get token trong req
	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			return authHeader.replace("Bearer", "");
		}
		return null;
	}
}
