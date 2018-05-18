package io.cristaling.iss.reddrop.web.utils;

import java.util.UUID;

public class UUIDUtils {

	public static UUID getUUIDFromString(String uuid) {
		try {
			return UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

}
