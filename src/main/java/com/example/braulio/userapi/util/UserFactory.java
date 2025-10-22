package com.example.braulio.userapi.util;

import java.util.Arrays;
import java.util.UUID;

import com.example.braulio.userapi.model.Address;
import com.example.braulio.userapi.model.User;

public class UserFactory {

    public static User create(String email, String name, String phone, String taxId,
                              String street1, String country1, String street2, String country2) {

        Address addr1 = new Address();
        addr1.setName("workaddress");
        addr1.setStreet(street1);
        addr1.setCountryCode(country1);

        Address addr2 = new Address();
        addr2.setName("homeaddress");
        addr2.setStreet(street2);
        addr2.setCountryCode(country2);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        user.setTaxId(taxId);
        user.setPassword(AESUtil.encrypt("Anceoso1212"));
        user.setCreatedAt(DateUtil.getMadagascarTimestamp());
        user.setAddresses(Arrays.asList(addr1, addr2));

        return user;
    }

}
