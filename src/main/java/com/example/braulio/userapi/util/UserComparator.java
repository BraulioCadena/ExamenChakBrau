package com.example.braulio.userapi.util;

import java.util.Comparator;

import com.example.braulio.userapi.model.User;

public class UserComparator {

    public static Comparator<User> getComparator(String sortedBy) {
        if (sortedBy == null || sortedBy.isBlank()) {
            return Comparator.comparing(User::getId); // default sort
        }

        return switch (sortedBy) {
            case "email" -> Comparator.comparing(User::getEmail, String.CASE_INSENSITIVE_ORDER);
            case "id" -> Comparator.comparing(User::getId);
            case "name" -> Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER);
            case "phone" -> Comparator.comparing(User::getPhone, String.CASE_INSENSITIVE_ORDER);
            case "tax_id" -> Comparator.comparing(User::getTaxId, String.CASE_INSENSITIVE_ORDER);
            case "created_at" -> Comparator.comparing(User::getCreatedAt);
            default -> Comparator.comparing(User::getId); 
        };
    }

}