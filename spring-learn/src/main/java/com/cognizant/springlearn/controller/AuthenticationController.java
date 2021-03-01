package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.SpringLearnApplication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
	private Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("Authorization Header={}", authHeader);
		String user = getUser(authHeader);
		LOGGER.debug(user);
		Map<String, String> map = new HashMap<String, String>();
		String token = generateJwt(user);
		LOGGER.debug(token);
		map.put("token", token);
		LOGGER.info("END");
		return map;
	}

	private String getUser(String authHeader) {
		LOGGER.info("START");
		String[] authHeaderSplit = authHeader.split(" ");
		LOGGER.debug("authHeaderSplit={}", authHeaderSplit.toString());
		String converted = new String(Base64.getDecoder().decode(authHeaderSplit[1]));
		LOGGER.debug("convertedCredentials={}", converted);
		LOGGER.info("END");
		return converted.split(":")[0];
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		return token;
	}
}
