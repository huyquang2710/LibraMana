package com.libra.securities.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.libra.securities.userprincal.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
	//set time song cua token
	
	//key dang ky: chi server moi biet
		private String jwtSecret = "huyquang";
		private int jwtExpriation = 86400; //time chet (s)
		
		//tao token
		public String creteToken(Authentication authentication) {
			UserPrinciple principle = (UserPrinciple) authentication.getPrincipal(); //lay ra user hien tai tren he thong
			
			return Jwts.builder() //build token
					.setSubject(principle.getUsername()) //set usename vao dayy	
					.setIssuedAt(new Date()) // set thoi diem
					.setExpiration(new Date(new Date().getTime() + jwtExpriation * 1000)) //set thoi gian song
					.signWith(SignatureAlgorithm.HS512, jwtSecret) //dang ky tieu chan ma hoa HS512	
					.compact();// dong lai
		}
		
		//check token co hop len ko
		public boolean validateTolen(String token) {
			try {
				Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
				return true;
			} catch (SignatureException e) {
				LOGGER.error("Invalid JWT signature -> Message: {}", e);
			}
			catch (MalformedJwtException e) {
				LOGGER.error("Invalid format Token -> Message: {}", e);
			}
			catch (ExpiredJwtException e) {
				LOGGER.error("Expired JWT token -> Message: {}", e);
			}
			catch (UnsupportedJwtException e) {
				LOGGER.error("Unsupported JWT token -> Message: {}", e);
			}
			catch (IllegalArgumentException e) {
				LOGGER.error("JWT claims string is empty -> Message: {}", e);
			}
			return false;
		}
		
		//tim username trong token
		public String getUsernameFromToken(String token) {
			String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
			return username;
		}
}
