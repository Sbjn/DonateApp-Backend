package com.donate.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.donate.Exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;
	
	
	public String generateToken (Authentication authentication) {
		String username = authentication.getName();
		
		Date currentDate = new Date();
		
		Date expireDate = new Date(currentDate.getTime()+jwtExpirationDate);
		
		
		String token = Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(expireDate)
			.signWith(key())
			.compact();
		return token;
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret)
		);
	}
	
	
	//get Token
	
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parseClaimsJws(token)
			.getBody();
		
		String username = claims.getSubject();
		
		return username;
	}
	
	
	//validate
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
		return true;
		} catch (MalformedJwtException ex) {
			throw new BlogAPIException("Invalid JWT token",HttpStatus.BAD_REQUEST);
		}catch (ExpiredJwtException ex) {
			throw new BlogAPIException("Expired JWT token",HttpStatus.BAD_REQUEST);
		}catch (UnsupportedJwtException ex) {
			throw new BlogAPIException("Unsupported JWT token",HttpStatus.BAD_REQUEST);
		}catch (IllegalArgumentException ex) {
			throw new BlogAPIException("JWT claims string is empty",HttpStatus.BAD_REQUEST);
		}
	}
	
}
