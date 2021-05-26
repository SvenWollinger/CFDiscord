package io.wollinger.cfdiscord;

import org.apache.commons.lang.RandomStringUtils;

import java.math.BigInteger;
import java.util.UUID;

public class Utils {

    public static String generateToken(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static UUID stringToUUID(String uuid) {
        String uuidString = uuid.replace("-", "");
        return new UUID(new BigInteger(uuidString.substring(0, 16), 16).longValue(), new BigInteger(uuidString.substring(16), 16).longValue());
    }
}
