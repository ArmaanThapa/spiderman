package com.xworkzcm.main.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGeneration {

	public static String generatePassword(int len) {

		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return RandomStringUtils.random(len, chars);

	}

}
