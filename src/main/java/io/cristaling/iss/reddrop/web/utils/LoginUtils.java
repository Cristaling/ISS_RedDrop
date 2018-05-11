package io.cristaling.iss.reddrop.web.utils;

import io.cristaling.iss.reddrop.web.responses.LoginResponse;

import java.util.UUID;

public class LoginUtils {

	public static LoginResponse generateLoginResponse(UUID token) {
		LoginResponse response = new LoginResponse();
		if (token == null) {
			response.setSuccesful(false);
			response.setToken(UUID.randomUUID().toString());
			return response;
		}
		response.setSuccesful(true);
		response.setToken(token.toString());
		return response;
	}

}
