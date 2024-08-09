package com.javaweb.Util;

public class NumberUtil {
public static boolean checkNumber (String a) {
	try {
		Long numbe = Long.parseLong(a);
	} catch (Exception e) {
		return false;
	}
	return true;
}
}