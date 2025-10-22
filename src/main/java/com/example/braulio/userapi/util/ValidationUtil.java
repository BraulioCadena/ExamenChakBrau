package com.example.braulio.userapi.util;

import java.util.regex.Pattern;

public class ValidationUtil {

private static final Pattern RFC_PATTERN = Pattern.compile("^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$");
private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{1,3}?\\s?\\d{10}$");

    public static void validateTaxId(String taxId) {
    	if (!RFC_PATTERN.matcher(taxId).matches()) {
    		throw new IllegalArgumentException("Invalid RFC format");
    	}
    }
    
    public static void validatePhone(String phone) {
    	if(!PHONE_PATTERN.matcher(phone).matches()) {
    		throw new IllegalArgumentException("Invalid phone format (AdresFormat)");
    	}
    }
    
    }