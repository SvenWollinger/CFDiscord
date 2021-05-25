package io.wollinger.cfdiscord;

import org.apache.commons.lang.RandomStringUtils;

public class Utils {

    public static String generateToken(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
