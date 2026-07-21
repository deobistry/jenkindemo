package com.example.demo.jwt;



import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

	private static final String SECRET = "ThisIsMyVerySecretKeyForJwtAuthentication123456";
	
	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public static String generateToken(int id, String name) {
		
		return Jwts.builder()
				.claim("id", id)
				.claim("name", name)
				.issuedAt(new Date())
				.expiration(new Date (System.currentTimeMillis()+ 100 *60*60))
				.signWith(KEY)
				.compact();
	}
}
