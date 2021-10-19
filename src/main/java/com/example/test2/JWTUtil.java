package com.example.test2;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	
	//1.generate Token
	public String generateToken(
			String id,
			String subject,
			String key
			) {
		return Jwts.builder()
				.setId(id)//id
				.setSubject(subject)//subject
				.setIssuer("Vinsys")//provider
				.setIssuedAt(new Date(System.currentTimeMillis()))//token gen date
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(1)))//token exp date
				.signWith(SignatureAlgorithm.HS256,Base64.getEncoder().encode(key.getBytes()) )//secrete key
				.compact()//string
				;
	}
	
	//2.Read or Claims
	public Claims getClaims(String key,String token) {
		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(key.getBytes()))//secret key
				.parseClaimsJws(token)
				.getBody();
				
	}
	

	public String getSubject(String key,String token){
		return getClaims(key,token).getSubject();
	} 

	public boolean isValidToken(String key,String token){
	//expdate>Curr date
	return getClaims(key,token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
	
}
