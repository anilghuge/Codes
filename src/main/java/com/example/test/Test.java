package com.example.test;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {
	
	public static void main(String[] args) {
		//token generate
		String key="VinSec";
		String token=
		Jwts.builder()
		.setId("A5266")//id
		.setSubject("JWT")//subject
		.setIssuer("Vinsys")//provider
		.setIssuedAt(new Date(System.currentTimeMillis()))//token gen date
		.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(10)))//token exp date
		.signWith(SignatureAlgorithm.HS256,Base64.getEncoder().encode(key.getBytes()) )//secrete key
		.compact()//string
		;
		System.out.println(token);
		
		//Read and validate
		Claims c=
		Jwts.parser()
		.setSigningKey(Base64.getEncoder().encode(key.getBytes()))//secret key
		.parseClaimsJws(token)
		.getBody();
		
		System.out.println(c.getSubject());
		System.out.println(c.getId());
		System.out.println(c.getIssuer());
		System.out.println(c.getExpiration());
		
		
		
	}

}
