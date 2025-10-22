package com.example.braulio.userapi.util;

import com.example.braulio.userapi.model.User;

public class FilterUtil {

    public static boolean matches(User user, String filter) {
        String[] parts = filter.split("\\+");
        if (parts.length != 3) return false;

        String field = parts[0];
        String operator = parts[1];
        String value = parts[2];

        String target = switch (field) {
            case "email" -> user.getEmail();
            case "id" -> user.getId().toString();
            case "name" -> user.getName();
            case "phone" -> user.getPhone();
            case "tax_id" -> user.getTaxId();
            case "created_at" -> user.getCreatedAt();
            default -> null;
        };

        if (target == null) return false;

        return switch (operator) {
            case "co" -> target.contains(value);
            case "eq" -> target.equals(value);
            case "sw" -> target.startsWith(value);
            case "ew" -> target.endsWith(value);
            default -> false;
        };
    }

}
