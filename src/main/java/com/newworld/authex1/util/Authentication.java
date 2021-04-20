package com.newworld.authex1.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.newworld.authex1.domain.AuthToken;
import com.newworld.authex1.exception.InternalException;
import com.newworld.authex1.vo.AuthResp;

public class Authentication {
	private static final Logger LOGGER = LoggerFactory.getLogger(Authentication.class);

	
	private static final String JWT_SALT = "cG9vamE=";

	public static AuthResp encode() throws InternalException {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime exp = now.plusHours(1);

		Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		Date expDate = Date.from(exp.atZone(ZoneId.systemDefault()).toInstant());

		try {

			byte[] secret = Base64.decodeBase64(JWT_SALT);
			Algorithm algorithmT = Algorithm.HMAC256(secret);

			JWTCreator.Builder builder = JWT.create();
			builder.withIssuer("FoodStuff Ltd").withIssuedAt(nowDate).withExpiresAt(expDate).withClaim("module", "1234")
					.withClaim("company", "FoodStuff").withClaim("userId", 1234);

			String token = builder.sign(algorithmT);

			AuthResp resp = new AuthResp();
			resp.setExpireAt(exp);
			resp.setToken(token);

			return resp;
		} catch (JWTCreationException exception) {
			throw new InternalException(-5, "fail.issue.token", "fail.issue.token");
		}
	}

	public static AuthToken decode(String token) throws JWTVerificationException, JWTDecodeException {
		LOGGER.info("inside decode method");

		byte[] secret = Base64.decodeBase64(JWT_SALT);

		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(300).build();
		DecodedJWT jwt = verifier.verify(token);
		Map<String, Claim> claimMap = jwt.getClaims();

		LOGGER.info("moduleCode ********" + claimMap.get("moduleCode").asString());

		return new AuthToken(claimMap.get("module").asString(), claimMap.get("company").asString(),
				claimMap.get("userId").as(Long.class));
	}

}
