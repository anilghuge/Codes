package com.example.test2;

import io.jsonwebtoken.Claims;

public class Test {

	public static void main(String[] args) {
		JWTUtil util=new JWTUtil();
	String token=util.generateToken("AA123", "JWTAPI", "NTTTI");
		
	Claims c=util.getClaims("NTTTI", token);
	System.out.println(c.getId());
	System.out.println(c.getIssuer());
	System.out.println(c.getExpiration());
	System.out.println(c.getIssuedAt());
	}
}
